package org.example.api.interCepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.api.common.error.ErrorCode;
import org.example.api.common.error.TokenErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info("Authorization Interceptor url: {}", request.getRequestURI());

        //WEB, chrome 의 경우 GET, POST OPTIONS = pass
        if (HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }
        // js. html. png resource 를 요청하는 경우 = pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // TODO handler 검증 : 헤더값 꺼내오기
        var accessToken = request.getHeader("authorization-token");
        if (accessToken == null){ //accesstoken이 없으면
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND, "인증 헤더 토큰 없음");
        }

        var userId = tokenBusiness.validationAccessToken(accessToken);

        if (userId != null){
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            //한 가지 request 를 Global 하게 저장할 수 잇는 저장소에 저장???
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST );
            //"userId"에 userId 저장 , request 단위로 저장(SCOPE 설정)
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "인증실패");

    }


}
