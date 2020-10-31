package com.kyc.payments.util;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAdapter {

    public static Date parseDate(String v) {
        return DatatypeConverter.parseDate(v).getTime();
    }

    public static String printDate(Date v)  {
        Calendar cal = new GregorianCalendar();
        cal.setTime(v);
        return DatatypeConverter.printDate(cal);
    }
}
