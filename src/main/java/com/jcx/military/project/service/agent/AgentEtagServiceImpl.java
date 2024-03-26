package com.jcx.military.project.service.agent;

import com.jcx.military.project.data.Etag;
import com.jcx.military.project.service.EtagService;
import com.jcx.military.project.service.config.SourceAfricaConfig;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Conditional(SourceAfricaConfig.class)
public class AgentEtagServiceImpl implements EtagService {

//    @Autowired
//    EtagFeignClient etagFeignClient;
    @Override
    public List<Etag> getEtagValue(String appCode, String md5, long fileSize) {
//        return etagFeignClient.getEtagValue(appCode, md5, fileSize);
        return new ArrayList<>();
    }
}
