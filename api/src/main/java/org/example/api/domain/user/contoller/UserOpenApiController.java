package org.example.api.domain.user.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.common.api.Api;
import org.example.api.domain.token.controller.model.TokenResponse;
import org.example.api.domain.user.business.UserBusiness;
import org.example.api.domain.user.contoller.model.UserLoginRequest;
import org.example.api.domain.user.contoller.model.UserRegisterRequest;
import org.example.api.domain.user.contoller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {
    //로그인을 하지 않아도 authorization 하지 않음 : 회원가입, 약관, 로그인 등
    // * authorization: 로그인 유무를 체크

    private final UserBusiness userBusiness;

    //사용자 가입요청
    @PostMapping("/register") //주소할당
    public Api<UserResponse> register( // 등록 메서드
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ){
        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ){
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
