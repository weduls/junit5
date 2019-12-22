package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/22
 **/
public class TagTest {

    /**
     *  assertAll로 한번에 모든 테스트가 가능
     *  묶지 않았을 때 순차적으로 내려오기 때문에 위에 테스트가 실패하면 밑에 테스트는 진행이 되지 않지만 AssertAll은 모두 사용 가능
     */
    @FastTest("fast")
    @DisplayName("빠른 test")
    void assert_all_test() {
        Study study = new Study();

        // 2, 3번째 테스트가 연달아 깨질것이다.
        assertAll(
            () -> assertNotNull(study),
            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT이여야 한다."),
            () -> assertEquals(study.getLimit(), 0, "스터디 상태가 완료된 상태여야한다."));
    }

    @SlowTest("slow")
    @DisplayName("느린 test")
    void assert_etc_test() {
        // 에러 여부 확인
        assertThrows(IllegalArgumentException.class, () -> new Study(-1));
        // 타임아웃 테스트 (지정한 시간때에 종료되지 않을 시 오류 발생) -- 로직 시간 성능 테스트에 대해 지정할 때 유용
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(50);
        });

        // 그냥 assertTimeout을 사용할 경우에는 안에 로직이 종료될때 까지 기다리는데 기준점을 넘으면 기다리지 않고 죽여버리고 싶으면 assertTimeoutPreemptively사용
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(50);
        });
    }

}
