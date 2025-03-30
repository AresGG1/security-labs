package com.smaha.security.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
    @author taras
    @project security
    @class StudentRestController
    @version 1.0.0
    @since 29.03.25 - 21.59
*/
@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentRestController {

    private final StudentService service;

    @GetMapping
    public List<Student> getStudents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getOneStudent(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }
}
