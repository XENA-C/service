package org.example.api.domain.token.business;
import ch.qos.logback.core.rolling.helper.TokenConverter;
import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Business;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.controller.model.TokenResponse;
import org.example.api.domain.token.service.TokenService;
import org.example.db.user.UserEntity;

import java.util.Optional;
@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /** 1.user entity 에서 user Id 추출
     * 2.access, refresh token 발행
     * 3.converter --> token response 변경
     * */

    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(ue -> {  //userEntity
                     return ue.getId(); //
                })
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken, refreshToken); 
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));

    }

}
