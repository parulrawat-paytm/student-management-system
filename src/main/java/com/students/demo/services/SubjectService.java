package com.students.demo.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students.demo.models.Subject;
import com.students.demo.respository.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Optional<Subject> getSubject(String string) {
		if (!ObjectId.isValid(string)) {
			throw new Error("Subject not valid");
		}
	    Optional<Subject> subject = subjectRepository.findById(string);
	    return subject;
	}
}
