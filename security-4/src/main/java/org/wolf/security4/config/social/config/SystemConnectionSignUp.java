package org.wolf.security4.config.social.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.stereotype.Component;

/**
 * 第三方登录时，自动注册用户信息
 */
@Slf4j
@Component
public class SystemConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        log.info("【SystemConnectionSignUp】 自动注册用户");
        OAuth2Connection oAuth2Connection=(OAuth2Connection)connection;
        ConnectionKey key = oAuth2Connection.getKey();

        log.info("key={}",key);

        //若是返回为null，则不会自动注册用户
        return oAuth2Connection.getDisplayName();
    }
}
