package com.springboot.config.security;

import com.springboot.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public UserDetailsService customUserDetailsService(){
        logger.info("[WebSecurityConfig][customUserDetailsService]invoke....start");
        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
        logger.info("[WebSecurityConfig][customUserDetailsService]result:{}]....end",customUserDetailsService);
        return customUserDetailsService;
    }

    //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
    //再次访问index页面无需登录直接访问
    //访问http://localhost:8080/home 不拦截，直接访问，
    //访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("[WebSecurityConfig][configure]invoke....start");
        http
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()//访问：/index 无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .antMatchers("/hello").hasAuthority("ADMIN") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .and()
                .formLogin()
                .loginPage("/login")//指定登录页是”/login”
                .permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutSuccessUrl("/home") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(1209600);
        logger.info("[WebSecurityConfig][configure][result:{}]....end",http);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        logger.info("[WebSecurityConfig][configureGlobal]invoke....start");
//        //指定密码加密所使用的加密器为passwordEncoder()
//        //需要将密码加密后写入数据库
//        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);
//        logger.info("[WebSecurityConfig][configureGlobal][result:{}]....end",auth);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("[WebSecurityConfig][configure]invoke....start");
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(new PasswordEncoder() {
            //指定密码加密所使用的加密器为passwordEncoder()
            @Override
            public String encode(CharSequence password) {
                String encodePassword = MD5Util.encode((String) password);
                logger.info("[WebSecurityConfig][configure][encode][result:[password={},encodePassword{}]]....start",password,encodePassword);
                return null;
            }

            //用户登陆是进行密码比对
            @Override
            public boolean matches(CharSequence password, String encodedPassword) {
                logger.info("[WebSecurityConfig][configure][matches][result:[password={},encodedPassword={}]]....start",password,encodedPassword);
                return encodedPassword.equals(MD5Util.encode((String)password));
            }
        });
        logger.info("[WebSecurityConfig][configure][result:{}]....end",auth);
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        logger.info("[WebSecurityConfig][passwordEncoder]invoke....start");
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//        logger.info("[WebSecurityConfig][passwordEncoder][result:{}]....end",bCryptPasswordEncoder);
//        return bCryptPasswordEncoder;
//    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        logger.info("[WebSecurityConfig][loginSuccessHandler]invoke....start");
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        logger.info("[WebSecurityConfig][loginSuccessHandler][result:{}]....end",loginSuccessHandler);
        return loginSuccessHandler;
    }

}
