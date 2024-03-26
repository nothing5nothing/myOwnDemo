package com.jcx.military.project.pipeline.tag;


import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;

public class TagFileHandler  implements ContextHandler<FileDownloadContext> {

    @Override
    public boolean handler(FileDownloadContext context) {
        return false;
    }
}
