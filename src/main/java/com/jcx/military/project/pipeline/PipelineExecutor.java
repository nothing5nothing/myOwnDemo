package com.jcx.military.project.pipeline;


import com.jcx.military.project.pipeline.context.FileDownloadContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Objects;

public class PipelineExecutor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 引用 PipelineRouteConfig 中的 pipelineRouteMap
     */
    private List<ContextHandler<FileDownloadContext>> pipelineRouteList;

    public PipelineExecutor addHandler(ContextHandler<FileDownloadContext> handler) {
        pipelineRouteList.add(handler);
        return this;
    }

    /**
     * 同步处理输入的上下文数据<br/>
     * 如果处理时上下文数据流通到最后一个处理器且最后一个处理器返回 true，则返回 true，否则返回 false
     *
     * @param context 输入的上下文数据
     * @return 处理过程中管道是否畅通，畅通返回 true，不畅通返回 false
     */
    public boolean acceptSync(FileDownloadContext context) {
        Objects.requireNonNull(context, "上下文数据不能为 null");

        // 获取数据处理管道
        if (pipelineRouteList.isEmpty()) {
            logger.error("管道为空");
            return false;
        }

        // 管道是否畅通
        boolean lastSuccess = true;

        for (ContextHandler<FileDownloadContext> handler : pipelineRouteList) {
            try {
                // 当前处理器处理数据，并返回是否继续向下处理
                lastSuccess = handler.handler(context);
            } catch (Throwable ex) {
                lastSuccess = false;
                logger.error("[{}] 处理异常，handler={}", context.getName(), handler.getClass().getSimpleName(), ex);
            }

            // 不再向下处理
            if (!lastSuccess) { break; }
        }

        return lastSuccess;
    }
}


