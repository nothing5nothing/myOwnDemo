package com.jcx.military.project.util;

public class ThreadLocalUtil {

    private ThreadLocalUtil(){}

    private static final ThreadLocal<String> APP_CODE_INFO = new ThreadLocal<>();

    private static final ThreadLocal<String> USER_INFO = new ThreadLocal<>();

    private static final ThreadLocal<Boolean> ENCRYPT_INFO = new ThreadLocal<>();

    public static void putValue(String appcode,String user,boolean en){
        APP_CODE_INFO.set(appcode);
        USER_INFO.set(user);
        ENCRYPT_INFO.set(en);
    }

    public static String getAppcode(){
        return APP_CODE_INFO.get();
    }

    public static Boolean getEncrypt(){
        return ENCRYPT_INFO.get();
    }

    public static String getUser(){
        return USER_INFO.get();
    }

    public static void remove(){
        APP_CODE_INFO.remove();
        USER_INFO.remove();
    }
}
