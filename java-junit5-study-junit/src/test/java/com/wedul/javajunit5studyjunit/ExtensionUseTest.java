package com.wedul.javajunit5studyjunit;

import com.wedul.javajunit5studyjunit.extensions.FindSlowTestExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@ExtendWith(FindSlowTestExtension.class)
public class ExtensionUseTest {

    @Test
    @DisplayName("slow 테스트 권장을 하게 하는 테스트 (확장팩 적용)")
    void slow_extension_test() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("일부러 늦게 동작하게 만들어 slow 테스트 추천 확장팩 정상 동작여부 확인");
    }

    @SlowTest
    @DisplayName("slow 테스트 권장을 하게 하는 테스트 (확장팩 적용)")
    void already_slow_extension_test() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("일부러 늦게 동작하게 만들어 slow 테스트 추천되어야 하나 이미 slowtest annotation이 붙어있어 동작안함");
    }
}
