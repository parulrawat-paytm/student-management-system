package com.students.demo.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.students.demo.models.Subject;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String>{

}
