package com.wedul.javajunit5studyjunit.wedul;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockWedulTest {

    @Mock
    WedulRepository wedulRepository;

    WedulService wedulService;

    @BeforeEach
    void setup() {
        this.wedulService = new WedulService(wedulRepository);
    }

    @Test
    @DisplayName("Mock test")
    void mock_test() {
        Wedul wedul = new Wedul();

        when(wedulRepository.getWedul(anyLong())).thenReturn(wedul);
        assertThat(wedulService.getWedul(1L)).isEqualTo(wedul);
    }

    @Test
    @DisplayName("stubbing verify 테스트")
    void verify_stub_test() {
        Wedul wedul = new Wedul();
        when(wedulRepository.getWedul(anyLong())).thenReturn(wedul);
        assertThat(wedulRepository.getWedul(1L)).isEqualTo(wedul);

        // 목 객체의 getWedul()이 한번 실행되었는지 검증
        verify(wedulRepository, times(1)).getWedul(1L);
        // 목 객체 validate()가 한번도 안 실행되었는지 검증
        verify(wedulRepository, never()).validate();
        // 해당 Mock이 더 이상 interactiondl 발생되지 않아야 한다.
        verifyNoMoreInteractions(wedulRepository);
    }

    @Test
    @DisplayName("BDD 테스트")
    void bdd() {
        // given
        Wedul wedul = new Wedul();
        given(wedulRepository.getWedul(1L)).willReturn(wedul);

        // when
        Wedul selectWedul = wedulService.getWedul(1L);

        // then
        assertThat(selectWedul).isEqualTo(wedul);
        then(wedulRepository).should(times(1)).getWedul(1L);
        then(wedulRepository).shouldHaveNoMoreInteractions();
    }

}
