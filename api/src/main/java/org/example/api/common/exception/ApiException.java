package org.example.api.common.exception;

import lombok.Getter;
import org.example.api.common.error.ErrorCodeIfs;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{

    private final ErrorCodeIfs errorCodeIfs; //필수적으로 발생
    private final String errorDescription; //Description 만 받았을 때

    public ApiException(ErrorCodeIfs errorCodeIfs, Exception e){
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription){
        super(errorDescription);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

//예외
    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx, String errorDescription){
     super(tx);
     this.errorCodeIfs = errorCodeIfs;
     this.errorDescription = errorDescription;
}

    }



