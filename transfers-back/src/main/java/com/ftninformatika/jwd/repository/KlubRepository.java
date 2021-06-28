package com.ftninformatika.jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Klub;

@Repository
public interface KlubRepository extends JpaRepository<Klub, Long>{
	
	Klub findOneById (Long id);

}
