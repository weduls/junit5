package com.wedul.javajunit5studyjunit.docker;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@Entity(name = "student")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    private String address;
    private String name;
    private int age;
    private String studentNickname;

    @Builder
    public Student(long studentId, String address, String name, int age, String studentNickname) {
        this.studentId = studentId;
        this.address = address;
        this.name = name;
        this.age = age;
        this.studentNickname = studentNickname;
    }
}
