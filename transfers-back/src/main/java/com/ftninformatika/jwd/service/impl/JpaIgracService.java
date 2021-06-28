package com.ftninformatika.jwd.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.repository.IgracRepository;
import com.ftninformatika.jwd.service.IgracService;

@Service
public class JpaIgracService implements IgracService{
	
	@Autowired
	private IgracRepository repo;

	@Override
	public Igrac findOne(Long id) {
		return repo.findOneById(id);
	}

	@Override
	public Page<Igrac> findAll(int pageNo) {
		return repo.findAll((PageRequest.of(pageNo, 3)));
	}

	@Override
	public Page<Igrac> search(String pozicija, String klubNaziv, int pageNo) {
		if (pozicija != null) {
			pozicija = "%" + pozicija + "%";
		}
		if (klubNaziv != null) {
			klubNaziv = "%" + klubNaziv + "%";
		}
		return repo.search(pozicija, klubNaziv, (PageRequest.of(pageNo, 3)));
	}

	@Override
	public Igrac save(Igrac igrac) {
		return repo.save(igrac);
	}

	@Override
	public Igrac delete(Long id) {
		Optional<Igrac> igrac = repo.findById(id);
		if (igrac.isPresent()) {
			repo.deleteById(id);
			return igrac.get();
		}
		return null;
	}

	@Override
	public Igrac update(Igrac igrac) {
		return repo.save(igrac);
	}

}
