package org.example.api.config.web;

import lombok.RequiredArgsConstructor;
import org.example.api.interCepter.AuthorizationInterceptor;
import org.example.api.resolver.UserSessionResolver;
import org.hibernate.dialect.function.ListaggStringAggEmulation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //인터셉터 등록 : component 에 의해 주입
    private final AuthorizationInterceptor authorizationInterceptor;
    private final UserSessionResolver userSessionResolver;

    private List<String> OPEN_API = List.of(
            "/open-api/**"
       // open-api/하위는 Interceptor(로그인 검증) 거치지 않음
       // 사용자 가입을 위해 검증을 거치지 않는 기능(로그인 x)
    );

    private List<String > DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns(OPEN_API)
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER)
                ;

        //인증 : user 만 사용하는 Api
        // exclude --> 인증을 하지 않는 Api
        // 로그인을 하지 않아도 접근 가능한 기능: ex) 회원가입, 약관 등 --> Interceptor 에 정의
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // WebMvcConfigure.super.addArgumentResolvers(resolvers);
       resolvers.add(userSessionResolver);
    }
}
