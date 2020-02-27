package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/22
 **/
public class ContitonalJunitTest {

    @Test
    @DisplayName("조건에 따라 테스트 실행하기")
    void contional_test() {
        String profile = System.getenv("PROFILE");
        System.out.println(profile);
        Study study = new Study();

        // 시스템 환경설정의 profile이 dev일 때만 밑에 기능 테스트 가능!
        assumeTrue("dev".equalsIgnoreCase(profile));

        // assumingTest를 통해 특정 조건이 가능했을 때, 다음파라미터의 테스트 가능
        assumingThat("wedul".equals("wedul"), () -> {
            assertThat(study.getLimit()).isEqualTo(0);
        });

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("OS 종류에 따라 테스트")
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void os_test() {
        System.out.println("test");
    }

    @Test
    @DisplayName("환경 변수 값에 따른 테스트")
    @EnabledIfEnvironmentVariable(named = "PROFILE", matches = "dev")
    void environmentEnable() {
        System.out.println("");
    }

    @Test
    @DisplayName("자바 버전에 따라 테스트")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    void jreTest() {
        System.out.println("zz");
    }

}
