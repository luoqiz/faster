package org.wolf.security4.config.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.wolf.security4.config.social.qq.api.QQ;
import org.wolf.security4.config.social.qq.api.QQImpl;

public class QQOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    public String appId;
    /**
     * 获取 授权码code 地址
     */
    private static final String QQ_URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 获取access_token 也就是令牌
     */
    private static final String QQ_URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQOAuth2ServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, QQ_URL_AUTHORIZE, QQ_URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
