package com.jcx.military.project.pipeline.context;


import java.io.InputStream;


public class FileDownloadContext extends PipelineContext {

    private String appCode;

    private String filekey;

    private boolean local;

    private boolean isCache;

    private boolean useRange;

    private boolean encrypt;

    private String userId;

    private int encryptValue;

    private int encryptLevel;

    private boolean tag;

    private String filePath;

    private InputStream stream;

    private String error;


    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getFilekey() {
        return filekey;
    }

    public void setFilekey(String filekey) {
        this.filekey = filekey;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    public boolean isUseRange() {
        return useRange;
    }

    public void setUseRange(boolean useRange) {
        this.useRange = useRange;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getEncryptValue() {
        return encryptValue;
    }

    public void setEncryptValue(int encryptValue) {
        this.encryptValue = encryptValue;
    }

    public int getEncryptLevel() {
        return encryptLevel;
    }

    public void setEncryptLevel(int encryptLevel) {
        this.encryptLevel = encryptLevel;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
