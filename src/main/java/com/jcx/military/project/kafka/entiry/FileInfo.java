package com.jcx.military.project.kafka.entiry;



import java.beans.ConstructorProperties;
import java.io.Serializable;



public abstract class FileInfo implements Serializable {

    int id;
    String fileKey;

    long totalTime;

    long fileSize;

    boolean success;


    public FileInfo(String fileKey, long totalTime, long fileSize, boolean success) {
        this.fileKey = fileKey;
        this.totalTime = totalTime;
        this.fileSize = fileSize;
        this.success = success;
    }

    public void setId(int id) {
        this.id = id;
    }

}
