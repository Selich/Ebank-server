package com.auth.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.TransactionEntity;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer>{
	
	List<TransactionEntity> findTransactionsByReceiverAccount(String receiverAccount);
}

