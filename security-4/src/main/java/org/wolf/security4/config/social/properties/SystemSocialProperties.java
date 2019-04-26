package org.wolf.security4.config.social.properties;

import lombok.Data;
import org.wolf.security4.config.social.qq.QQProperties;

@Data
public class SystemSocialProperties {

    private String filterProcessesUrl;

    private QQProperties qq = new QQProperties();
}
