package com.jcx.military.project.upload;

import com.jcx.military.project.data.FileHash;
import com.jcx.military.project.upload.cache.FileContextCache;
import org.springframework.web.multipart.MultipartFile;

public interface UploadUtil {

    String getFileChunk();


    FileContextCache initFileStatus();

    void uploadPart(MultipartFile file,
                    String md5,
                    String etag,
                    long size,
                    int chunkSize,
                    int chunk,
                    String cachePath);

    FileHash completeUpload(String md5,
                            String etag,
                            long size,
                            int chunkSize,
                            int chunk,
                            String cachePath);


    boolean checkFilePart(String md5,
                          long size,
                          int chunkSize,
                          String cachePath,
                          String bitmap);
}
