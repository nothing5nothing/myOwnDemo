package com.jcx.military.project.pipeline.service;


import com.jcx.military.project.pipeline.ContextHandler;
import com.jcx.military.project.pipeline.Factory.DownloadPipelineFactory;
import com.jcx.military.project.pipeline.Factory.EncryptPipelineFactory;
import com.jcx.military.project.pipeline.Factory.HeadPipelineFactory;
import com.jcx.military.project.pipeline.Factory.TagPipelineFactory;
import com.jcx.military.project.pipeline.PipelineExecutor;
import com.jcx.military.project.pipeline.context.FileDownloadContext;
import com.jcx.military.project.pipeline.enums.DownloadPipelineEnum;
import com.jcx.military.project.pipeline.enums.EncryptPipelineEnum;
import com.jcx.military.project.pipeline.enums.HeadSizePipelineEnum;
import com.jcx.military.project.pipeline.enums.TagPipelineEnum;
import org.springframework.stereotype.Component;

@Component
public class PipelineDownloadComponent {



    public boolean downloadFile(FileDownloadContext context) {
        PipelineExecutor executor = new PipelineExecutor();


        executor = executor.addHandler(HeadPipelineFactory.getDownloadHandler(
                                                HeadSizePipelineEnum.getHeadEnum(context.isCache(),!context.isLocal())))
                .addHandler(EncryptPipelineFactory.getDownloadHandler(
                                                EncryptPipelineEnum.getEncryptEnum(context.isEncrypt(),context.isUseRange(),context.isTag())))
                .addHandler(TagPipelineFactory.getDownloadHandler(
                                                TagPipelineEnum.getTagEnum(context.isTag())))
                .addHandler(DownloadPipelineFactory.getDownloadHandler(
                                                DownloadPipelineEnum.getDownloadEnum(context.isEncrypt(),context.isTag(),
                                                                                context.isCache(), context.isUseRange(), !context.isLocal())));

        boolean result = executor.acceptSync(context);
        return result;
    }


}
