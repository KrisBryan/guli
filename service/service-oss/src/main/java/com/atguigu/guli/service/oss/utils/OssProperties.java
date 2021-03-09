package com.atguigu.guli.service.oss.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 * Created by Kris on 2021/3/8
 */
@Data
@Component
@ConfigurationProperties(prefix="aliyun.oss")
public class OssProperties {
    private String schema;
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}
