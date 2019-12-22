package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/22
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test           // 기존에 junt5 테스트를 붙여서 새로운 애노테이션의 메타 기능으로 추가가능
@Tag("fast")    // 기존에 junt5 테스트를 붙여서 새로운 애노테이션의 메타 기능으로 추가가능
public @interface FastTest {
    String value() default "";
}
