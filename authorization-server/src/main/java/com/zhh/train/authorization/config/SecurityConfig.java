package com.zhh.train.authorization.config;

import com.zhh.train.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/24 9:38 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     * Access Denied 拒绝访问
     * 配置一个拒绝访问异常AccessDeniedException处理器
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
        return oAuth2AccessDeniedHandler;
    }

    /**
     * 启动一个身份验证方案
     * 这里用OAuth2作为身份验证实现
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        return oAuth2AuthenticationEntryPoint;
    }

    /**
     * 不定义没有password grant_type
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/webjars/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals", "/userrole/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.formLogin().loginPage("/login").failureForwardUrl("/login-error").permitAll() // 配置登陆页/login并允许访问
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and().authorizeRequests()
                .antMatchers("/css/**", "index").permitAll()  //允许访问任何静态资源
                .antMatchers("/oauth/authorize", "/oauth/token").permitAll()
                // 其余所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/401")
                // 由于使用的是oauth2，我们这里不需要csrf
                .and().csrf().disable();*/

        http
                .authorizeRequests()
                .antMatchers("/login", "/doLogin", "/logout", "/oauth/authorize", "/oauth/token").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login?authorization_error=true")
                .and()
                // put CSRF protection back into this endpoint
                .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .failureUrl("/login?authentication_error=true")
                .loginPage("/login");
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
