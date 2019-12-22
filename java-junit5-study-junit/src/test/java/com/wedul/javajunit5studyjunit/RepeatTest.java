package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/22
 **/
public class RepeatTest {

    /**
     * @param repetitionInfo 반복 과정에 대한 정보 (반복 횟수 등)
     */
    @DisplayName("반복 테스트 설정")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetition}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("반복 테스트" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    /**
     * 파라미터 개수 만큼 돈다.
     * @param message 파라미터 내용들
     */
    @DisplayName("파라미터 받는 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"반복", "테스트", "하는중"})
    @EmptySource // 비어있는 문자열 파라미터로 넣어줌
    @NullAndEmptySource // 비어있고 null인 소스 넣어줌
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    /**
     * 메소드 argument test
     * @param study
     */
    @DisplayName("methodSource를 통해 받는 테스트")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @MethodSource()
    void methodSourceTest(@ConvertWith(StudyConverter.class) Study study) {
        assertThat(study).isNotNull();
        assertThat(study.getStatus()).isNotNull();
    }

    static Stream<Arguments> methodSourceTest() {
        return Stream.of(
           Arguments.of("1"),
           Arguments.of("2")
        );
    }

    // 들어오는 메시지 컨버트 가능
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertThat(Study.class).isEqualTo(targetType);
            return new Study(Integer.parseInt(source.toString()));
        }
    }


    /**
     * 메소드 argument test
     * @param study
     */
    @DisplayName("methodSource를 통해 받는 테스트")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @MethodSource()
    void methodSourceTestWithAggregator(@AggregateWith(StudyAggregator.class) Study study) {
        assertThat(study).isNotNull();
        assertThat(study.getStatus()).isNotNull();
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            assertDoesNotThrow(() ->  StudyStatus.valueOf(accessor.getString(1)));
            StudyStatus studyStatus = StudyStatus.valueOf(accessor.getString(1));
            return new Study(accessor.getInteger(0), studyStatus);
        }
    }

    static Stream<Arguments> methodSourceTestWithAggregator() {
        return Stream.of(
            Arguments.of("1", "DONE"),
            Arguments.of("2", "DRAFT")
        );
    }

}
