package com.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer>{
	
	
	AccountEntity findAccountByAccountNumber(String accountNumber);
	
	

}
