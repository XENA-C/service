package org.example.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 작동기간 (RUNTIME:실행 중 적용)
@Service // Business 클래스의 로직을 처리하는 어노테이션
public @interface Converter{

    @AliasFor(annotation = Service.class) //주석 속성에 대한 별칭 선언
    //--> 선언되는 변수(value default)가 선언된 어노테이션 상위클래스 지칭
    //String value() default ""; 가 선언된 어노테이션 클래스(Service)
    String value() default "";
}
