package com.jcx.military.project.area;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AreaInit {

    @PostConstruct
    public void init() {


        try {

            // 获取当前区域列表存在本地缓存
            List<Area> area = new ArrayList<>();
            Area area1 = new Area();
            area1.setDefaultUpload(true);
            area.add(area1);
            Area defaultArea = area.stream().filter(Area::isDefaultUpload)
                    .filter((value)-> value.getArea().equals(AreaConfig.area))
                    .findFirst().get();

            AreaFactory.setDefaultArea(defaultArea);

            AreaFactory.putAreaCodeToArea(area.stream()
                    .filter((value)-> value.getArea().equals(AreaConfig.area))
                    .collect(Collectors.toMap(Area::getStorageCode, t->t)));

            AreaFactory.setAreaToArea(area.stream()
                    .filter((value)-> value.getArea().equals(AreaConfig.area))
                    .collect(Collectors.toList()));
        } catch (Exception ignored) {

        }
    }



}
