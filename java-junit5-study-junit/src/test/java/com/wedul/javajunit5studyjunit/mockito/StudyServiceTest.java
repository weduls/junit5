package com.wedul.javajunit5studyjunit.mockito;

import com.wedul.javajunit5studyjunit.Study;
import com.wedul.javajunit5studyjunit.StudyRepository;
import com.wedul.javajunit5studyjunit.StudyService;
import com.wedul.javajunit5studyjunit.StudyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    StudyService studyService;

    @Mock
    StudyRepository studyRepository;

    @BeforeEach
    void setup() {
        this.studyService = new StudyService(studyRepository);
    }

    @Test
    @DisplayName("스터디 정보 가져오기")
    void find_study_info() {
        Study study = new Study(1L, StudyStatus.DRAFT, 1);

        when(studyRepository.getStudy(anyLong())).thenReturn(study);

        // study repository가 3L Argument를 받을 때 문제
        doThrow(new IllegalArgumentException()).when(studyRepository).getStudy(3L);
        assertThat(studyService.getStudy(1L)).isNotNull();
        assertThat(studyService.getStudy(2L)).isNotNull();
        assertThrows(IllegalArgumentException.class, () -> studyService.getStudy(3L));
    }

    @Test
    @DisplayName("반복되는 테스트에서 서로 다른 값 호출")
    void repeatTest() {
        when(studyRepository.getStudy(anyLong()))
            .thenReturn(null)
            .thenThrow(NullPointerException.class)
            .thenReturn(new Study());

        assertThat(studyService.getStudy(1L)).isNull();
        assertThrows(NullPointerException.class, () -> studyService.getStudy(1L));
        assertThat(studyRepository.getStudy(2L).getClass()).isEqualTo(Study.class);
    }

    @Test
    @DisplayName("verify를 통해 메서드가 얼마나 호출 되었는지 확인")
    void verifyMethod() {
        when(studyRepository.getStudy(anyLong()))
            .thenReturn(new Study());

        assertThat(studyService.getStudy(1L)).isNotNull();
        // 1번 호출되었는지 확인
        verify(studyRepository, times(1)).getStudy(1L);
        verify(studyRepository, never()).validate();
        // 해당 Mock이 더 이상 interactiondl 발생되지 않아야 한다.
        verifyNoMoreInteractions(studyRepository);
    }

    @Test
    @DisplayName("BDD 스타일 Mock API")
    void bddStyle() {
        // given
        Study study = new Study(1L, StudyStatus.DONE, 3);
        given(studyRepository.getStudy(1L)).willReturn(study);

        // when
        Study selectStudy = studyService.getStudy(1L);

        // then
        assertThat(selectStudy).isEqualTo(study);
        then(studyRepository).should(times(1)).getStudy(1L);
        then(studyRepository).shouldHaveNoMoreInteractions();
    }

}
