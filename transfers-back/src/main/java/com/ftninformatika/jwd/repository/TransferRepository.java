package com.ftninformatika.jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{
	
	Transfer findOneById (Long id);

}
