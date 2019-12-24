package com.wedul.javajunit5studyjunit.docker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;
/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Container
    static MySQLContainer mariaDBContainer = new MySQLContainer();

    @Test
    @DisplayName("학생 추가하기")
    @Transactional
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
        Student student = studentService.getStudent(2L);
        assertThat(student).isNotNull();
    }

}