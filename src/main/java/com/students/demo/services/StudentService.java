package com.students.demo.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students.demo.models.*;
import com.students.demo.respository.StudentRepository;
import com.students.demo.respository.StandardRespository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StandardRespository standardRespository;
	@Autowired
	private StandardService standardService;
	@Autowired
	private SubjectService subjectService;
	
	public StudentResponse findById(String id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return this.getStudentResponseObject(student.get());
		}
		else return null;
	}
	
	public List<StudentResponse> findAll() {
		List<StudentResponse> response = new ArrayList<StudentResponse>();
		
		List<Student> students = studentRepository.findAll();
		Iterator<Student> itr = students.iterator();
		// loop over all student object and populate standard and subjects
		while(itr.hasNext()) {
			Student student = itr.next();
			response.add(this.getStudentResponseObject(student));
		}
		return response;
	}

	public Optional<Standard> getStandard(String string) {
		
		if(string == null)  return null;
		if (!ObjectId.isValid(string)) {
			throw new Error("Standard not valid");
		}
	    Optional<Standard> standard = standardRespository.findById(string);
	    return standard;
	}
	
	public void createAndSaveNewStudent (StudentRequest studentRequest) {
		Student student = new Student();
		student.setName(studentRequest.getName());
		student.setAddress(studentRequest.getAddress());
		ObjectId standardId = standardService.getStandard(studentRequest.getStandard());
		student.setStandard(standardId);
		List<ObjectId> enrolledSubjects = new ArrayList<ObjectId>();
		for(String subject : studentRequest.getEnrolledSubjects()) {
			enrolledSubjects.add(new ObjectId(subject));
		}
		student.setEnrolledSubjects(enrolledSubjects);

		studentRepository.save(student);
	}
	
	public StudentResponse getStudentResponseObject(Student student) {
		StudentResponse studentResponseObj = new StudentResponse();
		// Set name and address
		studentResponseObj.setName(student.getName());
		studentResponseObj.setAddress(student.getAddress());
		
		// set standard
		studentResponseObj.setStandard(this.getStandard(student.getStandard().toString()).get());
		
		// set subjects
		List<Subject> enrolledSubjects = new ArrayList<Subject>();
		for(ObjectId subject : student.getEnrolledSubjects()) {
			enrolledSubjects.add(subjectService.getSubject(subject.toString()).get());
		}
		studentResponseObj.setEnrolledSubjects(enrolledSubjects);
		return studentResponseObj;
	}
	
	public void deleteById(String id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			studentRepository.deleteById(id);
		}
		else throw new Error("Student by studentId "+id+" not found");
	}

	public void updateStudent (StudentRequest studentRequest) {
		Optional<Student> studentObj = studentRepository.findById(studentRequest.getId());
		if (studentObj.isPresent()) {
			Student student = studentObj.get();
			if(studentRequest.getName() != null) {
				student.setName(studentRequest.getName());
			}
			
			if(studentRequest.getAddress() != null) {
				student.setAddress(studentRequest.getAddress());
			}
			
			if(studentRequest.getStandard() != null) {
				student.setStandard(standardService.getStandard(studentRequest.getStandard()));
			}
			
			if(studentRequest.getEnrolledSubjects() != null && studentRequest.getEnrolledSubjects().size() != 0) {
				List<ObjectId> enrolledSubjects = new ArrayList<ObjectId>();
				for(String subject : studentRequest.getEnrolledSubjects()) {
					enrolledSubjects.add(new ObjectId(subject));
				}
				student.setEnrolledSubjects(enrolledSubjects);
			}
			studentRepository.save(student);
		}
	}
	
	public Student findById1(String id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return student.get();
		}
		else return null;
	}

}