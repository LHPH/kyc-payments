package com.kyc.payments.configuration;

import com.kyc.core.util.IntegrationUtil;
import com.kyc.payments.configuration.properties.TcpServerData;
import com.kyc.payments.configuration.properties.TcpServerProperties;
import com.kyc.payments.tcp.gateway.CreditCardGateway;
import com.kyc.payments.tcp.transformers.CardPaymentLayoutTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;

import static com.kyc.payments.constants.AppConstants.KYC_CARD_PAYMENT_REQ_CHANNEL;

@Configuration
@EnableIntegration
public class TcpClientConfig {

    @Bean
    public TcpNetClientConnectionFactory clientConnectionFactory(TcpServerProperties tcpServerProperties) {

        TcpServerData  tcpServerData = tcpServerProperties.getCardPaymentProcessor();
        TcpNetClientConnectionFactory factory = new TcpNetClientConnectionFactory(tcpServerData.getHost(),tcpServerData.getPort());
        factory.setSerializer(TcpCodecs.crlf());
        factory.setDeserializer(TcpCodecs.crlf());
        factory.setConnectTimeout(tcpServerData.getTimeout());
        factory.setSingleUse(false);
        return factory;
    }

    @Bean
    public IntegrationFlow creditCardFlow(
            AbstractClientConnectionFactory clientConnectionFactory,
            CardPaymentLayoutTransformer cardPaymentLayoutTransformer) {

        return IntegrationFlow.from(KYC_CARD_PAYMENT_REQ_CHANNEL)
                .transform(cardPaymentLayoutTransformer)
                .handle(Tcp.outboundGateway(clientConnectionFactory))
                .transform(Transformers.objectToString())
                .transform((value) -> IntegrationUtil.createMessageDataFromTcpResponse(value.toString()))
                .get();
    }
}
