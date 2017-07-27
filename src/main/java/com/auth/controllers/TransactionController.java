package com.auth.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entities.AccountEntity;
import com.auth.entities.ClientEntity;
import com.auth.entities.TransactionEntity;
import com.auth.repositories.AccountRepository;
import com.auth.repositories.ClientRepository;
import com.auth.repositories.TransactionRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ebank/transaction")
public class TransactionController {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private ClientRepository clientRepo;

//	@Autowired
//	private TransactionRepository transactionRepo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> doTransaction(@RequestBody TransactionEntity transaction) {
		try {
			String accountSenderNumber = transaction.getSenderAccount().getAccountNumber();
			String accountReceiverNumber = transaction.getReceiverAccount();
			AccountEntity sender = accountRepo.findAccountByAccountNumber(accountSenderNumber);
			AccountEntity receiver = accountRepo.findAccountByAccountNumber(accountReceiverNumber);
			Double value = transaction.getValue();
			Double newSenderBalance = sender.getAccountBalance() + value;
			Double newReceiverBalance = receiver.getAccountBalance() - value;
			sender.setAccountBalance(newSenderBalance);
			receiver.setAccountBalance(newReceiverBalance);
			accountRepo.save(sender);
			accountRepo.save(receiver);
			return new ResponseEntity<AccountEntity>(receiver, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method = RequestMethod.GET, value="/client/{id}")
	public ResponseEntity<?> getTransactionsByClient(@PathVariable Integer id){
		try {
		ClientEntity client = clientRepo.findOne(id);
		List<AccountEntity> accounts = client.getAccounts();
		List<TransactionEntity> transactions = new ArrayList<>();
		for ( AccountEntity account : accounts ){
//			List<TransactionEntity> sTransactions = account.getSendingTransaction()();
//			List<TransactionEntity> rTransactions = account.getReceivingTransaction();
			transactions.addAll(account.getSendingTransaction());
		}
			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@CrossOrigin
//	@RequestMapping(method = RequestMethod.GET, value="/list")
//	public ResponseEntity<?> getAllTransactions(){
//		try {
//			
//			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
//		} catch (Exception e){
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

}
