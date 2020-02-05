package com.liuchang.uploadfile.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

/**
 * @ Author     ：liuchang
 * @ Date       ：Created in 20:53 2020/2/5
 * @ Description：
 * @ Modified By：
 */

@Configuration
public class BeanConfig {
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver()
    {
        return new CustomMultipartResolver();
    }
}
