package org.wolf.security4.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSocialConfigurer systemSpringSocialConfigurer;
    /**
     * 此方法处理http请求相关逻辑
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(systemSpringSocialConfigurer);
        http.formLogin()
                .loginPage("/singIn.html").permitAll() //指定用户需要登陆时的处理url
                .loginProcessingUrl("/login").permitAll() //指定用户认证处理时的url
                .and()

                .authorizeRequests()
                .antMatchers("/**","/login", "/test/getverifycode/**", "/login**", "/auth/mobile*")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
