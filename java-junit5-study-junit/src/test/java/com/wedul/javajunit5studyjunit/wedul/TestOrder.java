package com.wedul.javajunit5studyjunit.wedul;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrder {

    @Test
    @Order(0)
    void second() {
        System.out.println(1);
    }

    @Test
    @Order(1)
    void first() {
        System.out.println(2);
    }

}
