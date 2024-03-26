package com.jcx.military.project.service.feign;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import feign.form.util.CharsetUtil;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        Reader reader = null;
        try {
            reader = response.body().asReader(CharsetUtil.UTF_8);
            String errMsg = Util.toString(reader);

            return new RuntimeException(errMsg);
        } catch (IOException e) {
            return new RuntimeException(MessageFormat.format("自定义Feign错误信息出错：{0}", e.getMessage()));
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
