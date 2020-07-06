package com.students.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.students.demo.respository.SubjectRepository;
import com.students.demo.models.Subject;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

//	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	private final SubjectRepository subjectRepository;
	
	SubjectController (SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Subject> getAllUsers() {
//		LOG.info(null);
		return subjectRepository.findAll();
	}
}
