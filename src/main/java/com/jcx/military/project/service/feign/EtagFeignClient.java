package com.jcx.military.project.service.feign;



import com.jcx.military.project.data.Etag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "etagFeignClient", path="agent/file/",
             url="http://127.0.0.1:8080/",fallback = EtagFeignClient.EtagFeignClientFallBack.class)
public interface EtagFeignClient {

    @GetMapping("get_etag")
    List<Etag> getEtagValue(@PathVariable("appCode") String appCode, @PathVariable("md5") String md5,
                            @PathVariable("fileSize") long fileSize);


    class EtagFeignClientFallBack implements EtagFeignClient{

        @Override
        public List<Etag> getEtagValue(String appCode, String md5, long fileSize) {
            return null;
        }
    }
}
