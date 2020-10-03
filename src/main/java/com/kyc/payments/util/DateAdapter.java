package com.kyc.payments.util;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        return DatatypeConverter.parseDate(v).getTime();
    }

    @Override
    public String marshal(Date v) throws Exception {
        Calendar cal = new GregorianCalendar();
        cal.setTime(v);
        return DatatypeConverter.printDate(cal);
    }
}
