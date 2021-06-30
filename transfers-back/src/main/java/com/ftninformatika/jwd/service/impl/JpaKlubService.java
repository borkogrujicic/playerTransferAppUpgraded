package com.ftninformatika.jwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		return repo.save(klub);
	}

	@Override
	public Page<Klub> findAll(int pageNo) {
		return repo.findAll(PageRequest.of(pageNo, 5));
	}

}
