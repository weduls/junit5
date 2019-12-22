package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrder {

    int value = 1;

    @Order(1)
    @Test
    @DisplayName("테스트 순서 테스트")
    void test_order_1() {
        System.out.println(this);
        System.out.println("1");
        System.out.println("value : " + value++);
    }

    @Order(2)
    @Test
    @DisplayName("테스트 순서 테스트")
    void test_order_2() {
        System.out.println(this);
        System.out.println("2");
        System.out.println("value : " + value++);
    }

    @Order(3)
    @Test
    @DisplayName("테스트 순서 테스트")
    void test_order_3() {
        System.out.println(this);
        System.out.println("3");
    }

    @Order(4)
    @Test
    @DisplayName("테스트 순서 테스트")
    void test_order_4() {
        System.out.println(this);
        System.out.println("4");
    }


}
