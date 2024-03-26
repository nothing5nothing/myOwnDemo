package com.jcx.military.project.pipeline.Factory;



import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;
import com.jcx.military.project.pipeline.download.CacheDownloadFileHandler;
import com.jcx.military.project.pipeline.download.LocalPathDownloadFileHandler;
import com.jcx.military.project.pipeline.download.LocalStreamDownloadFileHandler;
import com.jcx.military.project.pipeline.enums.DownloadPipelineEnum;
import com.jcx.military.project.pipeline.sizehandler.RemoteFileSizeHandler;

import java.util.HashMap;
import java.util.Map;

public class DownloadPipelineFactory {
    private final static Map<DownloadPipelineEnum, ContextHandler<FileDownloadContext>> map = new HashMap<>();

    static{
        map.put(DownloadPipelineEnum.DOWNLOAD_BY_STREAM, new LocalStreamDownloadFileHandler());
        map.put(DownloadPipelineEnum.DOWNLOAD_BY_PATH, new LocalPathDownloadFileHandler());
        map.put(DownloadPipelineEnum.DOWNLOAD_BY_CACHE, new CacheDownloadFileHandler());
        map.put(DownloadPipelineEnum.DOWNLOAD_BY_REMOTE, new RemoteFileSizeHandler());
    }

    public static ContextHandler<FileDownloadContext> getDownloadHandler(DownloadPipelineEnum downloadPipelineEnum) {
        return map.get(downloadPipelineEnum);
    }

}
