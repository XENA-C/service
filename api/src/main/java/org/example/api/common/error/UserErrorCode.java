package org.example.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {
                                    //내부 에러코드
    USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없음"),

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;  //enum 클래스의 값은 변경되지 않음 --> final


}
