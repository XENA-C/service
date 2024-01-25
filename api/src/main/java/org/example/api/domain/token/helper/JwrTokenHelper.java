package org.example.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.error.TokenErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.ifs.TokenHelperIfs;
import org.example.api.domain.token.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwrTokenHelper implements TokenHelperIfs{ //인터페이스 구현

    @Value("${token.secret.key}") //yaml jwt 값에 접근
    private String secretKey;
    //yaml 에 정의된 값들이 SpringBoot 실행시 Bean 으로 만들어지면서 해당 변수의 값을 채움

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;



    @Override
    public TokenDto issueAccessToken(Map<String, Object> data){
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        var expiredAt = Date.from(
                expiredLocalDateTime.atZone( //토큰 만료시간
                        ZoneId.systemDefault()
                ).toInstant()
        );

        // 서명 Key 생성
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        //토큰 생성
        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.ES256)
                .setClaims(data)
                .setExpiration(expiredAt) //만료기한
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {

        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);
        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes()); // Key 생성
        var jwtToken = Jwts.builder()     //토큰 생성
                .signWith(key, SignatureAlgorithm.ES256)
                .setClaims(data)
                .setExpiration(expiredAt) //만료기한
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override //validation
    public Map<String, Object> validationTokenWithThrow(String token) {
        //키 생성
        var key = Keys.hmacShaKeyFor(secretKey.getBytes()); // 키 생성
        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try { //파싱 시 에러 발생
            var result =   parser.parseClaimsJws(token);
            return new HashMap<String, Object>(result.getBody());

        }catch (Exception e){

            if (e instanceof SignatureException){ //토큰이 유효하지 않을 때
                throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);

            }else if (e instanceof ExpiredJwtException){ //만료된 토큰
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);

            }else{ //그 외 에러
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
            }
        }

    }
}
