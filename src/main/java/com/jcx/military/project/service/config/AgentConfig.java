package com.jcx.military.project.service.config;


import org.springframework.beans.factory.annotation.Value;

public class AgentConfig {

    public static boolean agent;

    public static boolean isAgent() {
        return agent;
    }

    @Value("${agent.use}")
    public static void setAgent(boolean agent) {
        AgentConfig.agent = agent;
    }
}
