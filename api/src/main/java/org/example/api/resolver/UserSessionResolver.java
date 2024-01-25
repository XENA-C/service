package org.example.api.resolver;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.UserSession;
import org.example.api.domain.user.business.UserBusiness;
import org.example.api.domain.user.contoller.model.User;
import org.example.api.domain.user.service.UserService;
import org.hibernate.annotations.Cache;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {
    //request -> resolver 실행

    private final UserService userService;

    @Override //지원하는 파라미터 체크, 어노테이션 체크
    public boolean supportsParameter(MethodParameter parameter) {
       // 지원하는 파라미터, 어노테이션 체크

        // 1. 어노테이션(UserSession)이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);
        // 2. 파라미터(User.class) 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);
        return (annotation && parameterType);  // --> ok --> resolverArgument 메서드 실행



    }

    @Override //support parameter 에서 true 반환 시 실행
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // request context holder 에서 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));
        //userId 를 통해 userService 에서 userEntity 반환

        //사용자 정보 셋팅 : User 객체에 UserEntity 정보 입력
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .name(userEntity.getName()                )
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build()
                ;

    }
}
