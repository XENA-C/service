package org.example.api.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.example.api.common.api.Api;
import org.example.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Order(value = Integer.MAX_VALUE) // 가장 마지막에 실행, 적용
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception) {
        log.error("", exception);
        return ResponseEntity
                .status(500)
                .body(
                   Api.ERROR(ErrorCode.SERVER_ERROR)
                );
    }
}


