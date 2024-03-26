package com.jcx.military.project.service.config;

import com.jcx.military.project.area.AreaConfig;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SourceAfricaConfig extends AgentConditionConfig{
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        //3、获取当前环境信息
        Environment environment = conditionContext.getEnvironment();

        String area = environment.getProperty("disk.area");
        return "NF".equals(area);
    }
}
