package com.jcx.military.project.upload.util;


import com.jcx.military.project.upload.AbsUpload;
import com.jcx.military.project.upload.DirectUpload;
import com.jcx.military.project.upload.LocalUpload;
import com.jcx.military.project.util.StringUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

// 创建直传方式
@Service
public class UploadFactory {

    @Autowired
    LocalUpload localUpload;

    @Autowired
    DirectUpload directUpload;

    public static final Map<UploadType, AbsUpload> map  = new HashMap<UploadType, AbsUpload>(){};


    @PostConstruct
    public void init(){
        map.put(UploadType.Direct, directUpload);
        map.put(UploadType.Local, localUpload);
    }


    public static AbsUpload getUploadUtil(UploadType uploadType) {
        if(UploadType.Direct.equals(uploadType)) {
            return map.get(UploadType.Direct);
        }
        return map.get(UploadType.Local);
    }

    public static AbsUpload getUploadUtil(Boolean encrypt) {
        if(encrypt == null || encrypt) return map.get(UploadType.Local);
        return map.get(UploadType.Direct);
    }





}
