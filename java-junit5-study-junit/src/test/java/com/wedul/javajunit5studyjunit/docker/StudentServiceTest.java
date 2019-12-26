package com.wedul.javajunit5studyjunit.docker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = StudentServiceTest.ContainerPropertyInitializer.class)
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Value("${container.port}")
    private int port;

    @Container
    static DockerComposeContainer composeContainer =
        new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
        .withExposedService("mysql", 3306);

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
        System.out.println(port);
        studentService.createStudent(Student.builder()
            .age(10)
            .name("wedul")
            .address("seoul jamsil")
            .studentNickname("duri")
            .build()
        );
        List<Student> studentList = studentService.getStudentList();
        Student student = studentService.getStudent(1L);
        assertThat(student).isNotNull();
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("container.port=" + composeContainer.getServicePort("mysql", 3306))
                .applyTo(applicationContext.getEnvironment());
        }
    }

}