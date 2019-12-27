package com.wedul.javajunit5studyjunit.docker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.List;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/26
 **/
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> student(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> students() {
        return ResponseEntity.ok(studentService.getStudentList());
    }

    @PostMapping("/student")
    public ResponseEntity<?> insertStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
