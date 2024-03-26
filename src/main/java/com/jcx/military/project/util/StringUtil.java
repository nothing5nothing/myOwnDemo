package com.jcx.military.project.util;

import java.util.UUID;

public class StringUtil {

    public static boolean isNull(String str) {
        return str == null;
    }


    public static boolean isNull(Object str) {
        return str == null;
    }

    public static String getUUid( ) {
        return UUID.randomUUID().toString();
    }
}
