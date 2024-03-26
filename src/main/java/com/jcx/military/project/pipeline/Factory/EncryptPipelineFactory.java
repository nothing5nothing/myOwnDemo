package com.jcx.military.project.pipeline.Factory;



import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.context.FileDownloadContext;
import com.jcx.military.project.pipeline.empty.EmptyHandler;
import com.jcx.military.project.pipeline.encrypt.FileEncryptHandler;
import com.jcx.military.project.pipeline.encrypt.StreamEncryptHandler;
import com.jcx.military.project.pipeline.enums.EncryptPipelineEnum;

import java.util.HashMap;
import java.util.Map;

public class EncryptPipelineFactory {

    private final static Map<EncryptPipelineEnum, ContextHandler<FileDownloadContext>> map = new HashMap<>();

    static{
        map.put(EncryptPipelineEnum.NO_ENCRYPT, new EmptyHandler());
        map.put(EncryptPipelineEnum.ENCRYPT_BY_STREAM, new StreamEncryptHandler());
        map.put(EncryptPipelineEnum.ENCRYPT_BY_PATH, new FileEncryptHandler());

    }

    public static ContextHandler<FileDownloadContext> getDownloadHandler(EncryptPipelineEnum enums) {
        return map.get(enums);
    }
}
