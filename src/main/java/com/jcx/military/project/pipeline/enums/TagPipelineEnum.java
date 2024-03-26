package com.jcx.military.project.pipeline.enums;

public enum TagPipelineEnum {

    TAG,

    NO;

    public static TagPipelineEnum getTagEnum(boolean tag) {
        return tag ? TAG : NO;
    }

}
