package com.jcx.military.project.upload;

public abstract class AbsUpload implements  UploadUtil{


    @Override
    public abstract String getFileChunk();


    public boolean checkBitMap(String bitmap,long size,int chunkSize) {
        int chunk =(int)(size / chunkSize);
        int len = bitmap.length();
        int value = 0;

        for(char c : bitmap.toCharArray()){
            if(c == '1') {
                value++;
            }
        }
        return chunk == value && chunk == len;
    }
}
