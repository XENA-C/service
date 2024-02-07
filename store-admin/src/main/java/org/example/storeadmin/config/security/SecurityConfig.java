package org.example.storeadmin.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity //security 활성화: bean 등록해서 설정
public class SecurityConfig {

    //swagger 주소 리스트
    private List<String> SWAGGER = List.of(
            "*/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .csrf().disable() //default: 활성화
                .authorizeHttpRequests(it->{
                    it
                       .requestMatchers(
                              PathRequest.toStaticResources().atCommonLocations()
                       ).permitAll()  //static resource 의 모든 요청 허용

                         //swagger 주소 제외 -> 인증없이 통과
                        .mvcMatchers(
                            SWAGGER.toArray(new String[0])
                         ).permitAll()

                        .mvcMatchers(
                             "/open-api/**"
                         ).permitAll() //open-api 하위 모든 주소 허용

                        //그 외 모든 요청은 인증
                        .anyRequest().authenticated()
                         ;
                   })
                .formLogin(Customizer.withDefaults())
                    ;

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //인코드 매치 및 인코드 업그레이드 등을 구현

        //Hash 방식으로 암호화: password 가 들어오면 hash-> 두 값이 동일한지 확인, 인코딩만 가능(decoding 불가)
        return new BCryptPasswordEncoder();

    }

}
