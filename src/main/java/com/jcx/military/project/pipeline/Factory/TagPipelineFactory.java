package com.jcx.military.project.pipeline.Factory;



import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;
import com.jcx.military.project.pipeline.empty.EmptyHandler;
import com.jcx.military.project.pipeline.enums.TagPipelineEnum;
import com.jcx.military.project.pipeline.tag.TagFileHandler;

import java.util.HashMap;
import java.util.Map;

public class TagPipelineFactory {

    private final static Map<TagPipelineEnum, ContextHandler<FileDownloadContext>> map = new HashMap<>();

    static{
        map.put(TagPipelineEnum.NO, new EmptyHandler());
        map.put(TagPipelineEnum.TAG, new TagFileHandler());
    }

    public static ContextHandler<FileDownloadContext> getDownloadHandler(TagPipelineEnum enums) {
        return map.get(enums);
    }

}