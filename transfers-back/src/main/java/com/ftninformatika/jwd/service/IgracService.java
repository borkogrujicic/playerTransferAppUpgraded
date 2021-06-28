package com.ftninformatika.jwd.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.model.Igrac;

public interface IgracService {
	
	Igrac findOne(Long id);
	
	List <Igrac> findByKlub (Long id);
	
	Page<Igrac> findAll(int pageNo);
	
	Page <Igrac> search (String pozicija, String klubNaziv, int pageNo);
	
	Igrac save (Igrac igrac);
	
	Igrac delete (Long id);
	
	Igrac update (Igrac igrac);
	
	

}
