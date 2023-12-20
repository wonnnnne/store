package com.devtw.store.config;

import com.devtw.store.common.JwtProvider;
import com.devtw.store.domain.interceptor.JwtInterceptor;
import com.devtw.store.domain.interceptor.LogInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtProvider jwtProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new JwtInterceptor(jwtProvider))
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/logout", "/join",
                        "/css/**", "/*.ico", "/error");
    }
}
