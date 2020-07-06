package com.students.demo.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students.demo.models.Standard;
import com.students.demo.respository.StandardRespository;;

@Service
public class StandardService {
	@Autowired
	private StandardRespository standardRespository;
	
	public ObjectId getStandard(String string) {
		if (!ObjectId.isValid(string)) {
			throw new Error("Standard not valid");
		}
	    Optional<Standard> standard = standardRespository.findById(string);
	    return new ObjectId(standard.get().getId());
	}
}
