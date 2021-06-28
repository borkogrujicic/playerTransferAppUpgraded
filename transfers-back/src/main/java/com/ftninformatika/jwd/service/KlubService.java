package com.ftninformatika.jwd.service;

import java.util.List;

import com.ftninformatika.jwd.model.Klub;

public interface KlubService {
	
	Klub findOne (Long id);
	
	List<Klub> findAll();
	
	Klub save (Klub klub);

}
