package com.wedul.javajunit5studyjunit.docker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@ActiveProfiles("test")
@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    @DisplayName("학생 추가하기")
    void create_student_test() {
        studentService.createStudent(Student.builder()
            .studentId(2L)
            .age(10)
            .name("wedul")
            .address("seoul jamsil")
            .build()
        );
    }

    @DisplayName("student 조회 테스트")
    @Test
    @Transactional
    void find_student_test() {
        studentService.createStudent(Student.builder()
            .studentId(2L)
            .age(10)
            .name("wedul")
            .address("seoul jamsil")
            .studentNickname("duri")
            .build()
        );
        Student student = studentService.getStudent(1L);
        assertThat(student).isNotNull();
    }

}