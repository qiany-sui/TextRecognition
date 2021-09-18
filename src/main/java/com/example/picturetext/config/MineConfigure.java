package com.example.picturetext.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qianyuqi
 * @create 2021-09-18 13:00
 */
@ConfigurationProperties(prefix = "mine.baidu")
@Component
@Data
public class MineConfigure {
    private String appId;
    private String appKey;
    private String secretKey;
}
