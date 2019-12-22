package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName(value = "스터디 만들기 테스트")
    void create_new_study() {
        Study study = new Study();
        assertNotNull(study);
        // 실패 문자열에 대한 반환 값도 지정할 수 있다.
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT이여야 한다.");
        // 단순 문자열도 가능하고 Supplier도 사용이 가능하다. (supplier를 사용해서 실패 메시지를 만들 때 편하다.)
        // 그리고 단순 문자열로 지정하게 되면 문자열이 실패하든 말든 문자열이 만들어지지만 람다식으로 supplier를 쓰게 되면 문자열이 무조건 만들어지지 않아서 성능에 유리
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT이여야 한다.");
        System.out.println("created");
    }
}