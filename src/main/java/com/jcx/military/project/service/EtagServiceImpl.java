package com.jcx.military.project.service;

import com.jcx.military.project.data.Etag;
import com.jcx.military.project.service.config.NanJingConfig;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Conditional(NanJingConfig.class)
@Order(2)
public class EtagServiceImpl implements EtagService{


    @Transactional
    public List<Etag> getEtagValue(String appCode,String md5, long fileSize) {

        return new ArrayList<>();
    }
}
