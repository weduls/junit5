package com.wedul.javajunit5studyjunit;

import com.wedul.javajunit5studyjunit.extensions.FindSlowTestExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
public class ExtensionUseFieldTest {

    // 클래스 선언적으로 하게 되면 커스터 마이징이 안되는데 이렇게 RegisterExtension을 사용하게되면 선언적으로 사용할 수 있다.
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

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
