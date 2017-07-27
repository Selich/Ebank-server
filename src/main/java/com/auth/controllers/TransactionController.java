package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entities.AccountEntity;
import com.auth.entities.TransactionEntity;
import com.auth.repositories.AccountRepository;

@RestController
@RequestMapping("/api/v1/ebank/transaction")
public class TransactionController {

	@Autowired
	private AccountRepository accountRepo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> doTransaction(@RequestBody TransactionEntity transaction) {
		try {
			AccountEntity sender = transaction.getSenderAccount();
			AccountEntity receiver = transaction.getReceiverAccount();
			Double value = transaction.getValue();
			return new ResponseEntity<TransactionEntity>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

	}

}
