package com.jcx.military.project.controller;


import com.jcx.military.project.conpoment.FileComponent;
import com.jcx.military.project.data.QueryFileStatus;
//import com.jcx.military.project.service.feign.EtagFeignClient;
import com.jcx.military.project.kafka.entiry.UploadInfo;
import com.jcx.military.project.kafka.producer.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.RandomAccessFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    FileComponent fileComponent;


    @RequestMapping(value = "/queryFileStatus",method = RequestMethod.GET)
    public QueryFileStatus queryFileStatus(String fileName,
                                           Integer fileSize,
                                           String md5,
                                           Integer chunkSize,
                                           MultipartFile file) {
        return fileComponent.queryFileStatus(fileName, fileSize, md5, chunkSize);
    }


    @RequestMapping(value= "/sendPartObject", method = RequestMethod.POST)
    public boolean sendPartData(MultipartFile file,
                                 String fileSize,
                                 String md5,
                                 String chunkMd5,
                                 String fileName,
                                 Integer chunk,
                                 Integer chunkSize) {

        String path = "H:\\test\\copy\\" + fileName;
        long start = (long) chunk * chunkSize;
        long size = file.getSize();

        byte[] buf = new byte[1024];
        int len = 0;

        try(RandomAccessFile f = new RandomAccessFile(path, "rw");
            InputStream in = file.getInputStream()){
            f.seek(start);
            while ( (len = in.read(buf) ) != -1 ) {
                f.write(buf,0,len);
            }

        }catch (Exception e) {

        }

        return true;
    }

    @Autowired
    MyKafkaProducer producer;

    @RequestMapping(value= "/kafka", method = RequestMethod.GET)
    public boolean kafka() {

        producer.sendMessage(UploadInfo.uploadInfoDefault.getInfo());

        return true;
    }


//    @Autowired
//    EtagFeignClient etagFeignClient;
//
//    @RequestMapping(value= "/test", method = RequestMethod.GET)
//    public void test() {
//        etagFeignClient.getEtagValue("UDM","111",1000L);
//    }


    public String finishPartObject(String md5,
                                   String fileSize) {


        return "";
    }


    public String copy(String fileKey,String appCode) {


        return "";
    }


}
