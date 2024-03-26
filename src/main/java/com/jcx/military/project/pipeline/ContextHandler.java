package com.jcx.military.project.pipeline;


import com.jcx.military.project.pipeline.context.PipelineContext;

public interface ContextHandler <T extends PipelineContext>{


    /**
     *
     * @param context
     * @return
     */
    boolean handler(T context);
}
