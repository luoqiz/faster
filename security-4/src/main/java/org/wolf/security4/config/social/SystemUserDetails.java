package org.wolf.security4.config.social;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class SystemUserDetails implements SocialUserDetailsService {
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
