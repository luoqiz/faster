package org.wolf.security3.config.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * provider
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //先获取由参数组装的token，并验证，若是验证成功则创建新的验证过的token，返回给前端
        SmsCodeAuthenticationToken smsAuthenticationToken = (SmsCodeAuthenticationToken)authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        if(user==null){
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        SmsCodeAuthenticationToken stoken = new SmsCodeAuthenticationToken(user,user.getAuthorities());
        stoken.setDetails(authentication.getDetails());
        return stoken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
