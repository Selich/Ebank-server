package com.auth.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.BankEntity;

@Repository
public interface BankRepository extends CrudRepository<BankEntity, Integer> {
	
	List<BankEntity> findAll();

}
