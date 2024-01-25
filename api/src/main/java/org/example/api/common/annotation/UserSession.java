package org.example.api.common.annotation;

import lombok.RequiredArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //어노테이션이 사용될 Target
@Retention(RetentionPolicy.RUNTIME)
public @interface UserSession {



}
