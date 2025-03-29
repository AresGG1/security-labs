package com.smaha.security.student;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    @author taras
    @project IntelliJ IDEA
    @class StudentService
    @version 1.0.0
    @since 29.03.25 - 21.59
*/

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    
    private List<Student> students;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
        this.students = new ArrayList<>();
    }

    @PostConstruct
    void init() {
        students.add(new Student("1", "Pavlo", "Stasyk", LocalDate.of(2004, 1, 1)));
        students.add(new Student("2", "Ivan", "Melnyk", LocalDate.of(2004, 2, 15)));
        students.add(new Student("3", "Olha", "Ivanyk", LocalDate.of(2004, 7, 30)));
        repository.saveAll(students);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Student student) {
        return repository.save(student);
    }
}
