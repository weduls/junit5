package com.wedul.javajunit5studyjunit.weduls;

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
 * @since 2019/12/23
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("quick")
@Test
public @interface QuickTag {
}
