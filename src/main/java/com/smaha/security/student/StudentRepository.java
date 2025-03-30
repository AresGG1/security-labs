package com.smaha.security.student;

/*
    @author taras
    @project security
    @class StudentRepository
    @version 1.0.0
    @since 29.03.25 - 21.57
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
