package com.jcx.military.project.task.data;


public class SyncTaskFile {

    private long id;

    private String filekey;

    private String taskId;

    private String owner;

    private String appCode;

    private String reason;

    /**
     *  0 待处理
     *  1 处理中
     *  2 完成
     *  3 失败
     */
    private int status;

    private String tag;

    private boolean encrypt;

    private int encryptValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilekey() {
        return filekey;
    }

    public void setFilekey(String filekey) {
        this.filekey = filekey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public int getEncryptValue() {
        return encryptValue;
    }

    public void setEncryptValue(int encryptValue) {
        this.encryptValue = encryptValue;
    }
}
