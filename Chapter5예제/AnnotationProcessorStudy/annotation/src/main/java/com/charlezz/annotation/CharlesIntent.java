package com.charlezz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    메타 애노테이션 : 애노테이션을 정의할 때 사용되는 다른 애노테이션
 */
// 커스텀 애노테이션을 만드는 애노테이션 모듈 --> @interface
@Target(ElementType.TYPE) // 애노테이션을 적용할 수 있는 자바 요소의 종류를 제한하려고 표시
@Retention(RetentionPolicy.SOURCE) // 애노테이션이 어떻게 저장되는지를 기술
public @interface CharlesIntent {
}
