package com.jcx.military.project.kafka.entiry;

import java.util.Random;


public class DownloadInfo extends FileInfo{

    long encryptTime;

    long tagTime;

    long downloadTime;

    InfoType type;

    public DownloadInfo(String fileKey, long totalTime, long fileSize, boolean success,
                        long encryptTime, long tagTime, long downloadTime, InfoType type) {
        super(fileKey, totalTime, fileSize, success);
        this.downloadTime= downloadTime;
        this.encryptTime =encryptTime;
        this.tagTime=tagTime;
        this.type= type;
    }

    public static class uploadInfoDefault {
        public static DownloadInfo getInfo() {
            Random random = new Random();
            return new DownloadInfo("key",random.nextLong(),random.nextLong(),true,random.nextLong(),
                    random.nextLong(), random.nextLong(), InfoType.Download);
        }
    }

}
