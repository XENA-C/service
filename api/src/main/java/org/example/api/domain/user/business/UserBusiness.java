package org.example.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Business;
import org.example.api.domain.token.controller.model.TokenResponse;
import org.example.api.domain.token.business.TokenBusiness;
import org.example.api.domain.user.contoller.model.UserLoginRequest;
import org.example.api.domain.user.contoller.model.UserRegisterRequest;
import org.example.api.domain.user.contoller.model.UserResponse;
import org.example.api.domain.user.converter.UserConverter;
import org.example.api.domain.user.service.UserService;

@RequiredArgsConstructor
@Business
public class UserBusiness { // Controller --> Business -> Service(Domain 로직 처리) -> Repository

    private final UserService userService ;

    private final UserConverter userConverter;
    // request-> Entity, Entity -> response
    private final TokenBusiness tokenBusiness; //같은 레벨의 비즈니스를 사용o

    // <사용자 가입처리 로직>
    // 해당 Request --> Entity 로 바꾸고(UserConverter)
    // entity -> save
    // save Entity -> response
    // response return

    public UserResponse register(UserRegisterRequest request){

        var entity = userConverter.toEntity(request); //request--> Entity 변환
        var newEntity = userService.register(entity); //서비스에 entity 전달 후 가입처리
        var response = userConverter.toResponse(newEntity);
        return response;

/* UserBusiness 에서 Converter 호출하여 ApiController 로 받은 request 를 Entity 로 변환
        //UserService 의 register 메서드를 통해 Entity 를 repository 에 저장
        // userService 에서 Repository로 저장 한 객체(new Entity)를 return


/* 함수형(람다식) -->
        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "request null"));*/
    }

/**
* 1. email, password 로 사용자 체크
* 2. userEntity 로그인 확인
* 3. token 생성 -> 4. token response */

    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        // 사용자 없으면 throw

        //TODO 토큰 생성로직으로 변경
//        var tokenResponse = tokenBusiness.issueToken(userEntity);
//        var userId = userEntity.getId();
//        return userConverter.toResponse(userEntity);
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }


    public UserResponse me(Long userId){ //로그인 시 사용자 정보 불러오기
        var userEntity = userService.getUserWithThrow(userId);
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
