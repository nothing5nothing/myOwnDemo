package com.jcx.military.project.area;



public class Area {

    /**
     * nf sz
     */
    String area;

    /**
     * 区域编码
     * 与property对应
     * nf ： nf01
     * sz ： stand01, nas01
     *
     */
    String StorageCode;

    boolean defaultUpload;

    boolean avalivable;

    String S3Value;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStorageCode() {
        return StorageCode;
    }

    public void setStorageCode(String storageCode) {
        StorageCode = storageCode;
    }

    public boolean isDefaultUpload() {
        return defaultUpload;
    }

    public void setDefaultUpload(boolean defaultUpload) {
        this.defaultUpload = defaultUpload;
    }

    public boolean isAvalivable() {
        return avalivable;
    }

    public void setAvalivable(boolean avalivable) {
        this.avalivable = avalivable;
    }

    public String getS3Value() {
        return S3Value;
    }

    public void setS3Value(String s3Value) {
        S3Value = s3Value;
    }
}
