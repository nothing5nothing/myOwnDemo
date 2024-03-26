package com.jcx.military.project.area;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
@Order(3)
@Configuration
public class AreaConfig {

    public static String area;

    public String getArea() {
        return area;
    }

    @Value("${disk.area:NJ}")
    public void setArea(String area) {
        AreaConfig.area = area;
    }
}
