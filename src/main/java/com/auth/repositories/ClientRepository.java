package com.auth.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer>{

	List<ClientEntity> findAll();
	
	ClientEntity findByEmailAndPassword(String email, String password);
	ClientEntity findByEmail(String email);
	ClientEntity findByJmbg(String jmbg);
}
