package com.akshat.springt_security.controller;

import com.akshat.springt_security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Akshat", "Java"),
            new Student(2, "Rahul", "Python"),
            new Student(3, "Rohit", "C++")
    ));

    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("students")
    public void addStudent(@RequestBody Student student) {
        students.add(student);
    }


    @GetMapping("csrf")
    public CsrfToken getCsrf(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");
    }

}
