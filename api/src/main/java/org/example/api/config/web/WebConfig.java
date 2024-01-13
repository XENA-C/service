package org.example.api.config.web;

import lombok.RequiredArgsConstructor;
import org.example.api.interCepter.AuthorizationInterceptor;
import org.hibernate.dialect.function.ListaggStringAggEmulation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //인터셉터 등록 : component 에 의해 주입
    private final AuthorizationInterceptor authorizationInterceptor;

    private List<String> OPEN_API = List.of( //open Api 검증하지 않겠다
            "/open-api/**"
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
        //인증을 하지 않는 Api ex) 회원가입, 약간 등등 -->  exclude
    }
}
