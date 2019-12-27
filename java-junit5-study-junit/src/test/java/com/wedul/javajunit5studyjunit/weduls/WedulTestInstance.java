package com.wedul.javajunit5studyjunit.weduls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WedulTestInstance {

    int value = 1;

    @Test
    void test_1() {
        System.out.println(this);
        System.out.print(value++);
    }

    @Test
    void test_2() {
        System.out.println(this);
        System.out.print(value++);
    }

}
