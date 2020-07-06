package com.students.demo.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.students.demo.models.StudentRequest;
import com.students.demo.models.StudentResponse;
import com.students.demo.services.StudentService;
import com.students.demo.utils.StudentValidationUtil;


@RestController
@RequestMapping(value = "/student")
public class StudentController {
	
	@Autowired
	private StudentValidationUtil studentValidationUtil;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("")
	public List<StudentResponse> getAllUsers() {
		return studentService.findAll();
	}
	
	@GetMapping("?id={id}")
	public ResponseEntity<StudentResponse> getUser(@PathVariable(value = "id") String id) {
		StudentResponse student = studentService.findById(id);
		if(student != null)
		{
			return ResponseEntity
	        		.ok()
	        		.body(student);
		}
		else {
			return ResponseEntity
	        		.badRequest()
	        		.body(student);
		}
	}
	
	@PostMapping("")
	ResponseEntity<String> addStudents(@RequestBody StudentRequest studentRequest) {
		List<StudentRequest> students = studentRequest.getStudents();
		Iterator<StudentRequest> studentIterator = students.iterator();
		while(studentIterator.hasNext()) {
			StudentRequest st = studentIterator.next();
			try {
				if (studentValidationUtil.validateNewStudent(st)) {
					studentService.createAndSaveNewStudent(st);
				}
			}
			catch(Exception e) {
				return ResponseEntity
					      .badRequest()
					      .body("{'message_type': 'error', 'message': "+e.getMessage()+"}");
			}
		}

	    return ResponseEntity
	    		.ok()
        		.body("{'message_type': 'Success', 'message': Student(s) created");
	  }

	@DeleteMapping("")
	public ResponseEntity<String> deleteUser(@RequestParam("id") String id) {
		try {
			studentService.deleteById(id);
		}
		catch(Exception e) {
			return ResponseEntity
	        		.badRequest()
	        		.body("{'message_type': 'error', 'message': Student cannot be deleted due to "+e.getMessage()+"}");
		}
		return ResponseEntity
        		.ok()
        		.body("{'message_type': 'Success', 'message': Student deleted");
	}

	@PutMapping("")
	ResponseEntity<String> updateStudents(@RequestBody StudentRequest studentRequest) {
		List<StudentRequest> students = studentRequest.getStudents();
		Iterator<StudentRequest> studentIterator = students.iterator();
		while(studentIterator.hasNext()) {
			StudentRequest st = studentIterator.next();
			try {
				if (studentValidationUtil.validateOldStudent(st)) {
					studentService.updateStudent(st);
				}
			}
			catch(Exception e) {
				return ResponseEntity
					      .badRequest()
					      .body("{'message_type': 'error', 'message': "+e.getMessage()+"}");
			}
		}

	    return ResponseEntity
	    		.ok()
        		.body("{'message_type': 'Success', 'message': Student(s) updated");
	  }

}
