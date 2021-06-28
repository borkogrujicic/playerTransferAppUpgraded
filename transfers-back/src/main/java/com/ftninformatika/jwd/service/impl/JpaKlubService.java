package com.ftninformatika.jwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.repository.KlubRepository;
import com.ftninformatika.jwd.service.KlubService;

@Service
public class JpaKlubService implements KlubService{
	
	@Autowired
	private KlubRepository repo;

	@Override
	public Klub findOne(Long id) {
		return repo.findOneById(id);
	}

	@Override
	public List<Klub> findAll() {
		return repo.findAll();
	}

	@Override
	public Klub save(Klub klub) {
		// TODO Auto-generated method stub
		return repo.save(klub);
	}

}
