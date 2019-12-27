package com.wedul.javajunit5studyjunit.weduls;

import com.wedul.javajunit5studyjunit.weduls.domain.Wedul;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WedulTest {

    WedulService wedulService;

    @Mock
    WedulRepository wedulRepository;

    @BeforeEach
    void setup() {
        wedulService = new WedulService(wedulRepository);
    }

    @DisplayName("가입 오류 테스트")
    @QuickTag()
    void when_join_not_error() {
        Wedul wedul = new Wedul();

        assertEquals(wedul.getId(), 1L, "아이디가 다릅니다.");
    }

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{currentRepetition}/{totalRepetition} {displayName}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("반복 테스트");
    }

    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @MethodSource()
    void parameterTest(String str) {
        System.out.println(str);
    }

    static Stream<Arguments> parameterTest() {
        return Stream.of(
          Arguments.of("cjung"),
            Arguments.of("wedul")
        );
    }

    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @MethodSource()
    void parameterAggregatorTest(@AggregateWith(WedulAggregator.class) Wedul wedul) {
        System.out.println(wedul.getId());
        System.out.println(wedul.getBalance());
    }

    static Stream<Arguments> parameterAggregatorTest() {
        return Stream.of(
            Arguments.of("1", 21),
            Arguments.of("2", 41)
        );
    }

    static class WedulAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Wedul(accessor.getLong(0), accessor.getInteger(1));
        }
    }

}