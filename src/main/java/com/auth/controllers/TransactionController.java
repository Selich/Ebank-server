package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entities.TransactionEntity;
import com.auth.repositories.AccountRepository;

@RestController
@RequestMapping("/api/v1/ebank/transaction")
public class TransactionController {
	
	
	@Autowired
	private AccountRepository accountRepo;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public TransactionEntity doTransaction(
			@RequestBody TransactionEntity transaction

	) {
		return transaction;
		
	}
	

}
