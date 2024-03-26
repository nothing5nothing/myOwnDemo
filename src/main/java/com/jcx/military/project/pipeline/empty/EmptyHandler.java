package com.jcx.military.project.pipeline.empty;


import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;

public class EmptyHandler implements ContextHandler<FileDownloadContext> {

    @Override
    public boolean handler(FileDownloadContext context) {
        return false;
    }
}