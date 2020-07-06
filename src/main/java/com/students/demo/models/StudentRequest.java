package com.students.demo.models;

import java.util.List;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {
	@Id
	private String id;
	private String name;
	private String address;
	private String standard;
	private List<String> enrolledSubjects;
	
	private List<StudentRequest> students;
	
	//default constructor
		public StudentRequest()
		{
		 super();
		}
}
