package org.wolf.security4.config.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.wolf.security4.config.properties.SystemSecurityProperties;
import org.wolf.security4.config.social.qq.QQProperties;
import org.wolf.security4.config.social.qq.connect.QQConnectionFactory;

@Configuration
// 当配置了app-id的时候才启用
@ConditionalOnProperty(prefix = "system.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;
    /**
     * 添加QQConnectionFacitonry到ConnectionFactionry中
     *
     * @param cfConfig
     * @param env
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        QQProperties qq = systemSecurityProperties.getSocial().getQq();
        QQConnectionFactory qqConnectionFactory = new QQConnectionFactory(qq.getProviderId(), qq.getAppId(),
                qq.getAppKey());
        cfConfig.addConnectionFactory(qqConnectionFactory);
    }

    // 后补：做到处理注册逻辑的时候发现的一个bug：登录完成后，数据库没有数据，但是再次登录却不用注册了
    // 就怀疑是否是在内存中存储了。结果果然发现这里父类的内存ConnectionRepository覆盖了SocialConfig中配置的jdbcConnectionRepository
    // 这里需要返回null，否则会返回内存的 ConnectionRepository
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
