package com.project.ecommerce.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String generateOrderId() {
        return "ORD-" + System.currentTimeMillis();
    }

    public static String generatePaymentId() {
        return "PAY-" + System.currentTimeMillis();
    }
}