package com.wedul.javajunit5studyjunit.docker;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@Entity(name = "student")
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    private String address;
    private String name;
    private int age;
    private String studentNickName;

    @Builder
    public Student(long studentId, String address, String name, int age, String studentNickName) {
        this.studentId = studentId;
        this.address = address;
        this.name = name;
        this.age = age;
        this.studentNickName = studentNickName;
    }
}
