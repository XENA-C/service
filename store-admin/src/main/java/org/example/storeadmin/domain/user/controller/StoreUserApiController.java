package org.example.storeadmin.domain.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.storeadmin.domain.authorization.model.UserSession;
import org.example.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.example.storeadmin.domain.user.converter.StoreUserConverter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-user")
public class StoreUserApiController{

    public final StoreUserConverter storeUserConverter;

    @GetMapping("/me")
    public StoreUserResponse me(
        @Parameter(hidden = true) //스웨거에서 가림
        @AuthenticationPrincipal UserSession userSession //이미 만들어진 사용자 인증 도구
        //사용자 로그인 --> userDetail 을 상속받은 UserSession(사용자정보) 주입
      ){
        return storeUserConverter.toResponse(userSession); //사용자 정보 반환
    }

    //로그인 --> 브라우저 + 서버 간 연결: 쿠키에 세션id 저장
}
