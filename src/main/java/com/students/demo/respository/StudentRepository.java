package com.students.demo.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.students.demo.models.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

}
