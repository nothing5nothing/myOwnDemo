package com.jcx.military.project.interceptor;

import com.jcx.military.project.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;



@Component
//@Slf4j
public class AuthInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String appCode = request.getHeader("app-code");
        String userId = request.getHeader("user-id");
        String secretKey = request.getHeader("x-secret-key");
        String authValue = request.getHeader("x-auth-value");

        // 住户md5校验

//        log.info(">>>>>>>拦截到api相关请求头<<<<<<<<");
        if(null != appCode){
            //直接搂下来，放到ThreadLocal中 后续直接从中获取
            ThreadLocalUtil.putValue(appCode,userId, Boolean.parseBoolean(request.getHeader("encrypt")));

        }
        return true;

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        //移除app-user
        ThreadLocalUtil.remove();

    }
}
