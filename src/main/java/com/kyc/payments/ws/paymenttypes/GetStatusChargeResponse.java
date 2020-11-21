package com.kyc.payments.ws.paymenttypes;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kyc.payments.ws.coretypes.StatusCharge;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "statusCharge"
})
@XmlRootElement(name = "GetStatusChargeResponse")
public class GetStatusChargeResponse
        implements Serializable
{

    @XmlElement(required = true)
    protected StatusCharge statusCharge;

    public StatusCharge getStatusCharge() {
        return statusCharge;
    }

    public void setStatusCharge(StatusCharge statusCharge) {
        this.statusCharge = statusCharge;
    }
}
