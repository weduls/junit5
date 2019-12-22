package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
// 기존에는 테스트마다 클래스를 새로 만들었으니 이 값을 부여하면 클래스 마다 테스트의 인스턴스를 한번만 만든다.
// 그렇기 때문에 static 메소드여야 했던 각 beforeAll, AfterAll 메소드가 모두 static이 아니여도 가능하다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceTest {

    @BeforeAll
    void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    void afterAll() {
        System.out.println("After all");
    }

    @Test
    void test() {

    }

}
