package com.wedul.javajunit5studyjunit.docker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
            .orElse(null);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

}
