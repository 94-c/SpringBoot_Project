package com.spring.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/", "/post/**", "/user/join")
                .permitAll() // 해당 경로들은 접근 허용
                .anyRequest().authenticated(); // 그 외 요청은 인증 요구
        http.formLogin()
                .loginPage("/user/login") // 로그인 페이지
                .loginProcessingUrl("/user/loginProc");
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/logout")
                .invalidateHttpSession(true);
        return http.build();
    }

}
