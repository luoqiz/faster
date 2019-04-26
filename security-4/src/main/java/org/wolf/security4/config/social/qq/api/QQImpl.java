package org.wolf.security4.config.social.qq.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.wolf.security4.config.social.qq.QQUserInfo;

@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 获取 openId 的url地址
     */
    private static final String QQ_URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息
     * http://wiki.connect.qq.com/get_user_info(access_token由父类提供)
     */
    private static final String QQ_URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * appId 配置文件读取
     */
    private String appId;
    /**
     * openId 请求QQ_URL_GET_OPENID返回
     */
    private String openId;

    /**
     * 构造方法获取openId
     *
     * @param accessToken
     * @param appId
     */
    public QQImpl(String accessToken, String appId) {
        //access_token作为查询参数来携带。
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;
        String url = String.format(QQ_URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        log.info("【QQImpl】 获取openId地址：QQ_URL_GET_OPENID={}  获取openId结果：result={}", QQ_URL_GET_OPENID, result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(QQ_URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        log.info("【QQImpl】获取用户信息 地址：QQ_URL_GET_USER_INFO={} 结果：result={}", QQ_URL_GET_USER_INFO, result);

        QQUserInfo userInfo = null;
        try {
            userInfo = JSON.parseObject(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
