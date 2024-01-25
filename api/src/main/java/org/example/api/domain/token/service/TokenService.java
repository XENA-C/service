package org.example.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.ifs.TokenHelperIfs;
import org.example.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


/* 토큰의 도메인 로직
* (토큰만 처리)*/
@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;
    //jwt/UUId로

    public TokenDto issueAccessToken(Long userId){ //
        var data = new HashMap<String, Object>(); //
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token){ //토큰 벨리데이션 후 로직("userId")의 값을 리턴
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, ()->{throw new ApiException(ErrorCode.NULL_POINT, "");
        });

        return Long.parseLong(userId.toString());
    }

    public TokenDto issueRefreshToken;

}
