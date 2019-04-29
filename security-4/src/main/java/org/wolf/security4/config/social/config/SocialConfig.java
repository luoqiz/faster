package org.wolf.security4.config.social.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import org.wolf.security4.config.properties.SystemSecurityProperties;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    //第三方用户登录，是否默认直接注册
    @Autowired(required=false)
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    /**
     * Default implementation of {@link #getUsersConnectionRepository(ConnectionFactoryLocator)} that creates an in-memory repository.
     */
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("sys_");

        //首次使用第三方用户登录时，自动创建系统用户
        if(connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
            }
            return authentication.getName();
        };
    }


    @Bean
    public SpringSocialConfigurer systemSpringSocialConfigurer() {
        //自定义过滤器拦截路径
        String filterProcessesUrl = systemSecurityProperties.getSocial().getFilterProcessesUrl();
        SystemSpringSocialConfigurer systemSpringSocialConfigurer=  new SystemSpringSocialConfigurer(filterProcessesUrl);
        return systemSpringSocialConfigurer;
//        return new SpringSocialConfigurer();
    }
}
