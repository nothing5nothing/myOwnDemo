package com.jcx.military.project.nas;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NasUtil {

    private static final List<String> NAS_ROOT = new ArrayList<>();

    public static void setRoot(String value) {
        NAS_ROOT.add(value);
    }

    public static String getValue() {
        List<String> copy = new ArrayList<>(NAS_ROOT);
        Random random = new Random(copy.size());
        return copy.get(random.nextInt());
    }

    public static void rmValue(String value) {
        if(NAS_ROOT.size() == 0) {
            return;
        }
        NAS_ROOT.remove(value);
    }

    public static boolean isAvilable(String value) {
        return NAS_ROOT.contains(value);
    }







}
