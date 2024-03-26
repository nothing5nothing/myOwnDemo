package com.jcx.military.project.pipeline.enums;

public enum DownloadPipelineEnum {

    DOWNLOAD_BY_PATH,

    DOWNLOAD_BY_STREAM,

    DOWNLOAD_BY_CACHE,

    DOWNLOAD_BY_REMOTE;

    public static DownloadPipelineEnum getDownloadEnum(boolean encrypt,
                                                boolean tag,
                                                boolean useCache,
                                                boolean useRange,
                                                boolean remote) {

        if(useCache) {
            return DownloadPipelineEnum.DOWNLOAD_BY_CACHE;
        }
        if (useRange && (tag || encrypt)) {
            return DownloadPipelineEnum.DOWNLOAD_BY_PATH;
        }
        if (remote) {
            return DownloadPipelineEnum.DOWNLOAD_BY_REMOTE;
        }
        return DownloadPipelineEnum.DOWNLOAD_BY_STREAM;


    }


}
