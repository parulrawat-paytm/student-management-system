package com.students.demo.models;
//import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor
public class Subject {
	@Id
	private String id;
	private String name;
	
	//default constructor
	public Subject()
	{
	 super();
	}
	public Subject(String name) {
		super();
		this.name = name;
	}
	
}
