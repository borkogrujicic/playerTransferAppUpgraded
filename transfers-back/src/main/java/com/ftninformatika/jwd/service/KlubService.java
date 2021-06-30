package com.ftninformatika.jwd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.model.Klub;

public interface KlubService {
	
	Klub findOne (Long id);
	
	Page <Klub> findAll (int pageNo);
	
	List<Klub> findAll();
	
	Klub save (Klub klub);

}
