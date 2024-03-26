package com.jcx.military.project.service.agent.controller;


import com.jcx.military.project.data.Etag;
import com.jcx.military.project.service.EtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("agent/file/")
public class AgentFileController {

    @Autowired
    EtagService etagService;

    @GetMapping("get_etag")
    public List<Etag> getEtag(String appCode, String md5, long fileSize) {
        return etagService.getEtagValue(appCode,md5,fileSize);
    }





}
