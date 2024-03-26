package com.jcx.military.project.data;




public class FileHash {

    long turelyfileSize;

    long sourceFileSize;

    String fielHash;

    String sourceMd5;

    boolean exist;

    public long getTurelyfileSize() {
        return turelyfileSize;
    }

    public void setTurelyfileSize(long turelyfileSize) {
        this.turelyfileSize = turelyfileSize;
    }

    public long getSourceFileSize() {
        return sourceFileSize;
    }

    public void setSourceFileSize(long sourceFileSize) {
        this.sourceFileSize = sourceFileSize;
    }

    public String getFielHash() {
        return fielHash;
    }

    public void setFielHash(String fielHash) {
        this.fielHash = fielHash;
    }

    public String getSourceMd5() {
        return sourceMd5;
    }

    public void setSourceMd5(String sourceMd5) {
        this.sourceMd5 = sourceMd5;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
