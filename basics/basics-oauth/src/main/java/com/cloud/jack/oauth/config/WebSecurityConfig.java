package com.cloud.jack.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 身份验证管理器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * 设置拦截器
     * 除了 /login  /oauth/authorize /oauth/token请求外，设置为任意的请求都需要登录认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login","oauth/authorize","/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().permitAll();


    }

    /**
     * 静态资源放行
     * @param webSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        // 静态资源放行
        webSecurity.ignoring().antMatchers("/dist/**", "/module/**", "/plugins/**");
    }
}
