package org.example.api.domain.user.contoller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.UserSession;
import org.example.api.common.api.Api;
import org.example.api.domain.user.business.UserBusiness;
import org.example.api.domain.user.contoller.model.User;
import org.example.api.domain.user.contoller.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController{

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me( //로그인 -> Api 호출
            @UserSession User user
    ){
        var response = userBusiness.me(user.getId());

     // requestContext: request 별로 생성
     // var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
     // var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
     // var response = userBusiness.me(Long.parseLong(userId.toString()));
     // var response = userBusiness.me(null);
        return Api.OK(response);
    }
}
