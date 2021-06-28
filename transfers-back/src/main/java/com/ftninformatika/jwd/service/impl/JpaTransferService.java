package com.ftninformatika.jwd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.model.Transfer;
import com.ftninformatika.jwd.repository.TransferRepository;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.service.KlubService;
import com.ftninformatika.jwd.service.TransferService;

@Service
public class JpaTransferService implements TransferService{
	
	@Autowired
	private TransferRepository repo;
	
	@Autowired
	private KlubService klubService;
	
	@Autowired
	private IgracService igracService;
	

	@Override
	public Transfer findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findOneById(id);
	}

	@Override
	public Transfer save(Transfer transfer) {
		Igrac igrac = transfer.getIgrac();
		Klub klub = transfer.getKlub();
		
		double budzet = klub.getBudzet() - transfer.getIznos();
		klub.setBudzet(budzet);
		
		igrac.setKlub(klub);
		klub.dodajIgraca(igrac);
		
		igracService.save(igrac);
		klubService.save(klub);
		return repo.save(transfer);
	}
	
	

}
