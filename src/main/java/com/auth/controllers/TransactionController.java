package com.auth.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import com.auth.entities.CurrencyEntity;
import com.auth.entities.TransactionEntity;
import com.auth.repositories.AccountRepository;
import com.auth.repositories.ClientRepository;
import com.auth.repositories.CurrencyRepository;
import com.auth.repositories.TransactionRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ebank/transaction")
public class TransactionController {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private CurrencyRepository currencyRepo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> doTransaction(@RequestBody TransactionEntity transaction) {
		try {
			// GETTING VARS
			CurrencyEntity currency = currencyRepo
					.findCurrencyByCurrencySymbol(transaction.getCurrency().getCurrencySymbol());
			Double buyingRate = currency.getBuyingRate();
			Integer worthFor = currency.getWorthFor();
			Double value = transaction.getValue();

			String accountSenderNumber = transaction.getSenderAccount().getAccountNumber();
			String accountReceiverNumber = transaction.getReceiverAccount();
			AccountEntity sender = accountRepo.findAccountByAccountNumber(accountSenderNumber);
			AccountEntity receiver = accountRepo.findAccountByAccountNumber(accountReceiverNumber);
			Double oldSenderBalance = sender.getAccountBalance();
			Double oldReceiverBalance = sender.getAccountBalance();

			if (oldSenderBalance - value < 0 || oldReceiverBalance + value > receiver.getAvailableBalance()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				transaction.setTransactionDate(new Date());
				Double newSenderBalance = oldSenderBalance - (value / worthFor * buyingRate);
				Double newReceiverBalance = oldReceiverBalance + (value / worthFor * buyingRate);

				TransactionEntity newTransaction = new TransactionEntity();
				
				newTransaction.setCurrency(currency);
				newTransaction.setModel(transaction.getModel());
				newTransaction.setReferenceNumber(transaction.getReferenceNumber());
				newTransaction.setPaymentCode(transaction.getPaymentCode());
				newTransaction.setReceiverAccount(transaction.getReceiverAccount());
				newTransaction.setSenderDescription(transaction.getSenderDescription());
				newTransaction.setSenderAccount(transaction.getSenderAccount());
				newTransaction.setTransactionDate(new Date());

				sender.setAccountBalance(newSenderBalance);
				receiver.setAccountBalance(newReceiverBalance);
				accountRepo.save(sender);
				accountRepo.save(receiver);
				
				transactionRepo.save(newTransaction);
				return new ResponseEntity<TransactionEntity>(newTransaction, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/client/{id}")
	public ResponseEntity<?> getTransactionsByClient(@PathVariable Integer id) {
		try {
			ClientEntity client = clientRepo.findOne(id);
			List<AccountEntity> accounts = client.getAccounts();
			List<TransactionEntity> transactions = new ArrayList<>();
			for (AccountEntity account : accounts) {
				// List<TransactionEntity> sTransactions =
				// account.getSendingTransaction()();
				// List<TransactionEntity> rTransactions =
				// account.getReceivingTransaction();
				transactions.addAll(account.getSendingTransaction());
			}
			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/account")
	public ResponseEntity<?> getTransactionsByClient(@RequestBody String accountNumber) {
		try {
			List<TransactionEntity> transactions = new ArrayList<>();
			AccountEntity senderAccount = accountRepo.findAccountByAccountNumber(accountNumber);
			AccountEntity receiverAccount = accountRepo.findAccountByAccountNumber(accountNumber);
			List<TransactionEntity> receivingTransactions = transactionRepo
					.findTransactionsByReceiverAccount(accountNumber);

			List<TransactionEntity> senderTransactions = transactionRepo
					.findTransactionsByReceiverAccount(senderAccount.getAccountNumber());
			//
			// transaction =
			//
			//
			// }
			return new ResponseEntity<List<TransactionEntity>>(senderTransactions, HttpStatus.OK);
			// return new ResponseEntity<AccountEntity>(account, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/receive/{accountNumber}")
	public ResponseEntity<?> getAllTransactions(@PathVariable String accountNumber) {
		try {
			List<TransactionEntity> transactions = transactionRepo.findTransactionsByReceiverAccount(accountNumber);

			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
