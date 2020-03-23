package com.aaa.gj.repast.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-22 22:59
 **/
@Configuration
public class FeignMultiPartConfig {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    @Bean
    public Encoder feignFormEncoder(){return new SpringFormEncoder(new SpringEncoder(messageConverters));}
}
