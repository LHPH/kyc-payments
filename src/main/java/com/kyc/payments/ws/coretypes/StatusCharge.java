package com.kyc.payments.ws.coretypes;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.kyc.payments.ws.adapters.Adapter1;
import com.kyc.payments.ws.adapters.Adapter2;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusCharge", propOrder = {
        "reference",
        "status",
        "amount",
        "dateCharge",
        "payments"
})
public class StatusCharge
        implements Serializable
{

    @XmlElement(required = true)
    protected String reference;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String amount;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected Date dateCharge;
    @XmlElement(required = true)
    protected List<StatusPayment> payments;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDateCharge() {
        return dateCharge;
    }

    public void setDateCharge(Date dateCharge) {
        this.dateCharge = dateCharge;
    }

    public List<StatusPayment> getPayments() {

        if(payments==null){
            payments = new ArrayList<>();
        }
        return payments;
    }

    public void setPayments(List<StatusPayment> payments) {
        this.payments = payments;
    }
}
