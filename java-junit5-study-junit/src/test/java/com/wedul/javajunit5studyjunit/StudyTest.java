package com.wedul.javajunit5studyjunit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디")
    void create_new_study() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("created");
    }

    @AfterAll
    static void after_all() {
        System.out.println("After All");
    }

    @BeforeAll
    static void before_all() {
        System.out.println("Before All");
    }

    @BeforeEach
    void before_each() {
        System.out.println("Before each");
    }

    @AfterEach
    void after_each() {
        System.out.println("After Each");
    }

}