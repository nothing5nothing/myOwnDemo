package com.jcx.military.project.upload;

import com.amazonaws.services.s3.model.PartETag;
import com.jcx.military.project.S3.AWSClient;
import com.jcx.military.project.area.Area;
import com.jcx.military.project.area.AreaFactory;
import com.jcx.military.project.data.FileHash;
import com.jcx.military.project.upload.cache.FileContextCache;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DirectUpload extends AbsUpload {


    @Override
    // 根据bucket key upload 获取文件分片情况
    public String getFileChunk() {

        List<PartETag> partETags =  AWSClient.getUploadStatus("","","");
        int fileSize = 10;
        StringBuilder sb = new StringBuilder();
        while(fileSize-->0) {
            sb.append('0');
        }
        partETags.forEach((part) -> {
            sb.setCharAt(part.getPartNumber(), '1');
        });

        return sb.toString();
    }

    @Override
    public FileContextCache initFileStatus() {

        Area area  = AreaFactory.getDefaultArea();
        // 创建uploadID,调用S3进行初始化

        // 刷新缓存

        return new FileContextCache();
    }

    @Override
    public void uploadPart(MultipartFile file,
                    String md5,
                    String etag,
                    long size,
                    int chunkSize,
                    int chunk,
                    String cachePath){

        // 调用S3 传递分片并做端到端校验



    }


    @Override
    public FileHash completeUpload(String md5,
                                   String etag,
                                   long size,
                                   int chunkSize,
                                   int chunk,
                                   String cachePath){

        // 调用S3实现文件合并


        // 返回文件hash
        return new FileHash();
//        return FileHash.builder()
//                .fielHash(md5)
//                .sourceFileSize(size)
//                .sourceMd5(md5)
//                .sourceFileSize(size)
//                .build();


    }


    @Override
    public boolean checkFilePart(String md5,
                                 long size,
                                 int chunkSize,
                                 String cachePath,
                                 String bitmap){

        // S3根据uploadId获取是所有分片

        // 校验文件是否完整

        return super.checkBitMap(bitmap,size,chunkSize);
    }
}
