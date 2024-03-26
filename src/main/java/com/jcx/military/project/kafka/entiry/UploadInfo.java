package com.jcx.military.project.kafka.entiry;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UploadInfo extends FileInfo implements Serializable{

    int id;

    long decryptTime;

    long uploadTime;

    InfoType type;

    List<String> maxValue;

    public UploadInfo(String fileKey, long totalTime, long fileSize, boolean success,
                      long decryptTime, long uploadTime, InfoType type) {
        super(fileKey, totalTime, fileSize, success);
        this.decryptTime = decryptTime;
        this.uploadTime = uploadTime;
        this.type = type;
    }

    public static class uploadInfoDefault {
        public static UploadInfo getInfo() {
            Random random = new Random();
            UploadInfo info =  new UploadInfo("key",random.nextLong(),random.nextLong(),true,random.nextLong(),random.nextLong(), InfoType.Upload);

//            List<String> list = new ArrayList<>();
//            for(int i =0;i<100;i++) {
//                list.add("用来沾满数据各用来沾满数据各用来沾满数据各用来沾满数据各用来沾满数据各用来沾满数据各用来沾满数据各用来沾满数据各用来沾满数据各用来沾");
//            }
//            info.setMaxValue(list);
            return info;
        }
    }



}


