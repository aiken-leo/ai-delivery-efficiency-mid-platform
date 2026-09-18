package com.leo.ai.delivery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用部署相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.deploy")
public class AppDeployConfig {

    /**
     * 应用部署访问域名，例如 http://localhost
     */
    private String host = "http://localhost";
}
