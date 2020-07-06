package com.students.demo.models;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document
@AllArgsConstructor
public class Student {
	@Id
	private String id;
	private String name;
	private String address;
	private ObjectId standard;
	private List<ObjectId> enrolledSubjects;
	
	//default constructor
	public Student()
	{
	 super();
	}
}
