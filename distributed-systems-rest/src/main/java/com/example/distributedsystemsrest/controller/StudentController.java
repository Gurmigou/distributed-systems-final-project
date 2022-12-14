package com.example.distributedsystemsrest.controller;

import com.example.distributedsystemsrest.model.Student;
import com.example.distributedsystemsrest.repository.DataAccessObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/student")
@RestController
public class StudentController {
    private final DataAccessObject dataAccessObject = new DataAccessObject();

    @GetMapping
    public ResponseEntity<Student> getSthById(Integer id) {
        Student student = dataAccessObject
                .findStudentById(id)
                .orElseThrow(() -> new IllegalStateException("Student not found"));
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(Student student) {
        dataAccessObject.createNewStudent(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(Student student) {
        dataAccessObject.updateStudent(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping
    public ResponseEntity<Student> deleteStudent(Integer id) {
        dataAccessObject.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
