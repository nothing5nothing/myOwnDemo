package com.jcx.military.project.pipeline.encrypt;


import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;

public class FileEncryptHandler  implements ContextHandler<FileDownloadContext> {

    @Override
    public boolean handler(FileDownloadContext context) {
        return false;
    }
}
