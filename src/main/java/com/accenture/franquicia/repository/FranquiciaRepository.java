package com.accenture.franquicia.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.franquicia.model.Franquicia;

@Repository
public interface FranquiciaRepository  extends ReactiveMongoRepository<Franquicia, String>  {
	
	

}
