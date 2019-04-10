package org.wolf.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

//    @Autowired
//    private UserDetailsService userDetailsService;

    /**
     * 此方法处理http请求相关逻辑
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .loginPage("/signIn.html")  //指定用户需要登陆时的处理url
                .loginProcessingUrl("/login") //指定用户认证处理时的url
                .successHandler(authenticationSuccessHandler) //登录成功处理
//                .failureHandler()   //登录失败处理
//                .and()
//                .rememberMe()
//                    .rememberMeServices(userDetailsService)
//                    .tokenRepository(persistentTokenRepository())
//                    .tokenValiditySeconds(7000)
                .and()
                .authorizeRequests()
                .antMatchers("**.html", "**.css", "**.js").permitAll()
                .anyRequest().authenticated(); //所有请求需要认证
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 指定记录用户时的tokekn存储
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setCreateTableOnStartup(true);//在系统首次启动时创建对应的用户记录表，之后应该注释，否则因表已经存在而启动失败
        return tokenRepository;
    }
}
