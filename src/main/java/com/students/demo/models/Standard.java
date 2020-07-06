package com.students.demo.models;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.students.demo.models.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@ToString
public class Standard {
	@Id
	private String id;
	private String name;
	private String level;
	private List<ObjectId> subjects;
	
	//default constructor
	public Standard()
	{
	 super();
	}
//	public Standard(String name, String level, List<Subject> subjects) {
//		super();
//		this.name = name;
//		this.level = level;
//		this.subjects = subjects;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	public List<Subject> getSubjects() {
//		return subjects;
//	}
//	public void setSubjects(List<Subject> subjects) {
//		this.subjects = subjects;
//	}
//
//	public String getLevel() {
//		return level;
//	}
//
//	public void setLevel(String level) {
//		this.level = level;
//	}
}
