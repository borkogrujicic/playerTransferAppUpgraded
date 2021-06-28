package com.ftninformatika.jwd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Igrac;

@Repository
public interface IgracRepository extends JpaRepository<Igrac, Long>{
	
	Igrac findOneById (Long id);
	
	Page <Igrac> findAll (Pageable pageable);
	
	@Query("SELECT t FROM Igrac t WHERE" +
			"(:pozicija = NULL OR t.pozicija LIKE :pozicija) AND " + 
			"(:klubNaziv = NULL OR t.klub.naziv LIKE :klubNaziv)")
	Page<Igrac> search(@Param("pozicija") String pozicija, @Param("klubNaziv") String klubNaziv, Pageable pageable);

	
	

}
