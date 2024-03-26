package com.jcx.military.project.pipeline.enums;

public enum HeadSizePipelineEnum {


    HEAD_SIZE_BY_LOCAL,

    HEAD_SIZE_BY_CACHE,

    HEAD_SIZE_BY_REMOTE;

    public static HeadSizePipelineEnum getHeadEnum(boolean cache,
                                            boolean remote) {
        if(cache) return HEAD_SIZE_BY_CACHE;
        if(remote) return HEAD_SIZE_BY_REMOTE;
        return HEAD_SIZE_BY_LOCAL;


    }
}
