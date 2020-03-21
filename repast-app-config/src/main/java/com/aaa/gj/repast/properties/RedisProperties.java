package com.aaa.gj.repast.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-09 22:21
 **/
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:properties/redis.properties")
@Data
@Component
public class RedisProperties {
    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
}
