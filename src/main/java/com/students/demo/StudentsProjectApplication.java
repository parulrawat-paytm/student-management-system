package com.students.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.students.demo.models.Standard;
import com.students.demo.models.Subject;
import com.students.demo.respository.StandardRespository;
import com.students.demo.respository.SubjectRepository;

@SpringBootApplication
public class StudentsProjectApplication implements CommandLineRunner{
	
	@Autowired
	private StandardRespository standardRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentsProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//.append("score",99)
//		List<Subject> l = new ArrayList<Subject>();
//		String a = "5efef79be728726ff60bb384";
//		Subject sub = subjectRepository.findById(a).get();
//		if (sub != null) l.add(sub);
//	
//		
//		Standard s = new Standard("Class 12", "12", l );
//		
//		standardRepository.save(s);
		
	}

}
