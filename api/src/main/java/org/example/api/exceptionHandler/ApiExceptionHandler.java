package org.example.api.exceptionHandler;

import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.example.api.common.api.Api;
import org.example.api.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) //최소값 최우선처리
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(
            ApiException apiException
    ){
            log.error("", apiException);

            var errorCode = apiException.getErrorCodeIfs();
            return ResponseEntity
                    .status(errorCode.getHttpStatusCode())
                    .body(
                       Api.ERROR(errorCode, apiException.getErrorDescription())
                    );
    }


}
