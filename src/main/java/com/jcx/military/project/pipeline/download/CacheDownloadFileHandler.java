package com.jcx.military.project.pipeline.download;


import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;

public class CacheDownloadFileHandler implements ContextHandler<FileDownloadContext> {

    @Override
    public boolean handler(FileDownloadContext context) {
        return false;
    }
}