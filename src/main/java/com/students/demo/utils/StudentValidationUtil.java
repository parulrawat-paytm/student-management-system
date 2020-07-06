package com.students.demo.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.assertj.core.util.Arrays;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.students.demo.models.*;
import com.students.demo.services.*;
import com.students.demo.respository.StandardRespository;
import com.students.demo.respository.StudentRepository;

@Component
public class StudentValidationUtil {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectService subjectService;
	public Boolean validateNewStudent(StudentRequest studentRequest) throws Exception {
		
		// validate name
		if(studentRequest.getName() == null) {
			throw new Exception("Please pass a name for student");
		}
		Pattern pattern = Pattern.compile("/^[a-z ,.'-]+$/i");
	    Matcher matcher = pattern.matcher(studentRequest.getName());
	    if (matcher.find()) throw new Exception("Name not valid");
	    
	    // validate class exists
	    if(studentRequest.getStandard() == null) {
			throw new Exception("Please pass address for student");
		}
	    Optional<Standard> standard = studentService.getStandard(studentRequest.getStandard());
	    if (!standard.isPresent()) throw new Exception("Standard for student "+studentRequest.getName()+"does not exists");
	    
	    // validate subjects exist & for same class
	    List<String> subjects = studentRequest.getEnrolledSubjects();
	    
	    if(subjects.size() == 0 || subjects == null) {
			throw new Exception("Please pass suject(s) for student");
		}
	    Iterator<String> subjItr = subjects.iterator();
	    while (subjItr.hasNext()) {
	    	String subjectId = (String) subjItr.next();
	    	Optional<Subject> subject = subjectService.getSubject(subjectId);
	    	
	    	// Valid subject
	 	    if (!subject.isPresent()) {
	 	    	throw new Exception("Subject for student "+studentRequest.getName()+" does not exists");
	 	    }
	 	    
	 	    // Check if subjects opted are for same class
	 	    if (!standard.get().getSubjects().contains(new ObjectId(subject.get().getId()))) {
	 	    	throw new Exception("Student cannot opt subject "+subject.get().getName()+" for standard "+standard.get().getName());
	 	    }
	    }
	    return true;
	}
	
public Boolean validateOldStudent(StudentRequest studentRequest) throws Exception {
		
		// validate by id
		if(studentRequest.getId() == null) {
			throw new Exception("Please pass student's id");
		}
		Student res = studentService.findById1(studentRequest.getId());
		if (res == null) throw new Exception("Student with id "+studentRequest.getId()+" not found");
		
		// validate name
		if(studentRequest.getName() != null) {
			Pattern pattern = Pattern.compile("/^[a-z ,.'-]+$/i");
		    Matcher matcher = pattern.matcher(studentRequest.getName());
		    if (matcher.find()) throw new Exception("Name not valid");
		}
	    
	    // validate class exists
		Optional<Standard> standard;
		if(studentRequest.getStandard() != null) {
			standard = studentService.getStandard(studentRequest.getStandard());
		    if (!standard.isPresent()) throw new Exception("Standard for student "+studentRequest.getName()+"does not exists");
		}
		else standard = studentService.getStandard(res.getStandard().toString());
	    
	    // validate subjects exist & for same class
	    List<String> subjects = studentRequest.getEnrolledSubjects();
	    if (subjects != null && subjects.size() > 0) {
	    	Iterator<String> subjItr = subjects.iterator();
		    while (subjItr.hasNext()) {
		    	String subjectId = (String) subjItr.next();
		    	Optional<Subject> subject = subjectService.getSubject(subjectId);
		    	
		    	// Valid subject
		 	    if (!subject.isPresent()) {
		 	    	throw new Exception("Subject for student "+studentRequest.getName()+" does not exists");
		 	    }
		 	    
		 	    // Check if subjects opted are for same class
		 	    if (!standard.get().getSubjects().contains(new ObjectId(subject.get().getId()))) {
		 	    	throw new Exception("Student cannot opt subject "+subject.get().getName()+" for standard "+standard.get().getName());
		 	    }
		    }
	    }
	    return true;
	}
}
