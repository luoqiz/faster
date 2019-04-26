package org.wolf.security4.config.social.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.wolf.security4.config.properties.SystemSecurityProperties;

/**
 * 启动自定义配置的属性
 * @author luoqiz
 *
 */
@Configuration
@EnableConfigurationProperties(SystemSecurityProperties.class)
public class SystemSecurityConfig {

	
}
