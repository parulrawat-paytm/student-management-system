package com.students.demo.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponse {
	private String name;
	private String address;
	private Standard standard;
	private List<Subject> enrolledSubjects;
	
	//default constructor
		public StudentResponse()
		{
		 super();
		}
}
