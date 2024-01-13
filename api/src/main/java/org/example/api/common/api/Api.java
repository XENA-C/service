package org.example.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> { //Api통신을 위한 공통 스펙 정의

    private Result result;

    @Valid //유효성 검사
    private T body;

    public static <T> Api<T> OK(T data){
        var api = new Api();
        api.result = Result.OK();
        api.body = data;
        return api;
    }

    public static Api<Object> ERROR(Result result){
        //ERROR는 body에 넣을 내용이 없음 --> 제네릭 타입 X, Object-> 아무거나 담음
        var api = new Api<Object>();
        api.result = result;
        return api;
    }

    //ERROR 코드
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }

    //최상위 예외(Throwable tx) 함께 전달
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, tx);
        return api;
    }

    //메세지
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, description);
        return api;
    }

}
