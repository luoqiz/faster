package org.wolf.security1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 此处可以自定义实现UserDetails接口，这样可以和数据库中的用户实体对应
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名是{}", username);
        return new User(username, passwordEncoder.encode("123456"), //此处必须使用加密方式
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
