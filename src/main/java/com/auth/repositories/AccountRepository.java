package com.auth.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.AccountEntity;
import com.auth.entities.ClientEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

	List<AccountEntity> findByClient(ClientEntity client);

	AccountEntity findAccountByAccountNumber(String accountNumber);

}
