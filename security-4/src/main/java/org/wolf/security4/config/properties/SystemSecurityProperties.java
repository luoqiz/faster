package org.wolf.security4.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.wolf.security4.config.social.properties.SystemSocialProperties;

/**
 * 定义属性文件
 *
 * @author luoqiz
 */

@Data
@ConfigurationProperties(prefix = "system.security")
public class SystemSecurityProperties {
    private SystemSocialProperties social = new SystemSocialProperties();
}
