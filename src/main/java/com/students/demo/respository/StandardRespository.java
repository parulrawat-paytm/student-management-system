package com.students.demo.respository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.students.demo.models.Standard;

@Repository
public interface StandardRespository extends MongoRepository<Standard, String>{
//
//	<S> S save(Standard s);
//	@Override
//    public default Optional < Standard > findById(Long id) {
//        return StandardRespository.findById(id);
//    }

}
