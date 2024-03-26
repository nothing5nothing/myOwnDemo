package com.jcx.military.project.pipeline.Factory;


import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;
import com.jcx.military.project.pipeline.enums.HeadSizePipelineEnum;
import com.jcx.military.project.pipeline.sizehandler.CacheFileSizeHandler;
import com.jcx.military.project.pipeline.sizehandler.LocalFileSizeHandler;
import com.jcx.military.project.pipeline.sizehandler.RemoteFileSizeHandler;

import java.util.HashMap;
import java.util.Map;

public class HeadPipelineFactory {

    private final static Map<HeadSizePipelineEnum, ContextHandler<FileDownloadContext>> map = new HashMap<>();

    static{
        map.put(HeadSizePipelineEnum.HEAD_SIZE_BY_CACHE, new CacheFileSizeHandler());
        map.put(HeadSizePipelineEnum.HEAD_SIZE_BY_LOCAL, new LocalFileSizeHandler());
        map.put(HeadSizePipelineEnum.HEAD_SIZE_BY_REMOTE, new RemoteFileSizeHandler());
    }

    public static ContextHandler<FileDownloadContext> getDownloadHandler(HeadSizePipelineEnum enums) {
        return map.get(enums);
    }

}
