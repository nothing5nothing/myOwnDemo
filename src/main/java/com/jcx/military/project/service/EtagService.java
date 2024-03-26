package com.jcx.military.project.service;


import com.jcx.military.project.data.Etag;

import java.util.List;


public interface EtagService {

    List<Etag> getEtagValue(String appCode, String md5, long fileSize);
}
