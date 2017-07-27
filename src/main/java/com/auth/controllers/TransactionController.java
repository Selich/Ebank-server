package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	public TransactionEntity doTransaction(@RequestBody TransactionEntity transaction) {
		AccountEntity sender = transaction.getSenderAccount();
		AccountEntity receiver = transaction.getReceiverAccount();
		Double value = transaction.getValue();
		
		try {
			if ( value > sender.getAccountBalance() ){
//				return error server

				return null;
				
			} else if ( value + receiver.getAccountBalance() > receiver.getAvailableBalance()){
//				return error server
				return null;
				
			} else {
				sender.setAccountBalance(sender.getAccountBalance() - (value * transaction.getCurrency().getBuyingRate()));
				receiver.setAccountBalance(receiver.getAccountBalance() + (value * transaction.getCurrency().getBuyingRate()));
				accountRepo.save(sender);
				accountRepo.save(receiver);
				return transaction;
			}
		} catch (Exception e){
				return null;
			
		}

	}

}
