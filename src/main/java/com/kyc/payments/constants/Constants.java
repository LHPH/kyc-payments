package com.kyc.payments.constants;

public interface Constants {

    String NAME_SPACE_WSDL_PAYMENTS_URI = "http://kyc-payments/KYCPayments.wsdl";
    String NAME_SPACE_PAYMENTS_URI = "http://kyc-payments.com/PaymentTypes";
    String NAME_SPACE_HEADER_URI = "http://kyc-payments.com/HeaderTypes";

    String ERROR_CODE_01 ="E01";
    String ERROR_DESC_01 = "Ocurrio un error inesperado en la aplicacion.";

    String ERROR_CODE_02 ="E02";
    String ERROR_DESC_02 = "No se pudo autenticar al usuario.";

    String ERROR_CODE_03 ="E03";
    String ERROR_DESC_03 = "No se pudo efectuar el pago.";

    String ERROR_CODE_04 ="E04";
    String ERROR_DESC_04 = "No se pudo realizar la busqueda deseada.";



}
