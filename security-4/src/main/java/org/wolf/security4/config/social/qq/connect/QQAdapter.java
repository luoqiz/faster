package org.wolf.security4.config.social.qq.connect;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.wolf.security4.config.social.qq.api.QQ;
import org.wolf.security4.config.social.qq.QQUserInfo;

/**
 * qq返回的信息为spring social提供的适配器
 */
public class QQAdapter implements ApiAdapter<QQ> {
	
    @Override
    public boolean test(QQ api) {
    	//测试qq服务器是否正常工作
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
    	//将获取的qq用户信息，转为social的标准用户信息，保存到数据库中
        QQUserInfo userInfo = api.getUserInfo();

        values.setProviderUserId(userInfo.getOpenId());//openId 唯一标识
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
    	//返回用户操作信息，例如微博用户，可以更新微博状态等。在QQ中没有这个概念

    }
}