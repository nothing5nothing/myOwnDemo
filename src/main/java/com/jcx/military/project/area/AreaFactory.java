package com.jcx.military.project.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaFactory {

    public static Area defaultArea;

    // 文件存储编码
    public static Map<String, Area> areaCodeToArea = new HashMap<>();

    // 国家区域
    public static List<Area> areaToArea = new ArrayList<>();

    public static List<Area> getAreaToArea() {
        return areaToArea;
    }

    public static void setAreaToArea(List<Area> areaToArea) {
        AreaFactory.areaToArea.addAll(areaToArea);
    }

    public static Area getDefaultArea() {
        return defaultArea;
    }

    public static void setDefaultArea(Area defaultArea) {
        AreaFactory.defaultArea = defaultArea;
    }

    public static Area getArea(String areaCode) {
        return areaCodeToArea.get(areaCode);
    }

    public static void putAreaCodeToArea(Map<String, Area> map) {
        areaCodeToArea.putAll(map);
    }
}
