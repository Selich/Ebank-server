package com.auth.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			String model = transaction.getModel();
			String referenceNumber = transaction.getReferenceNumber();
			if (validateChecksum(model, referenceNumber) == false) {
				return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
			} else {

				String accountSenderNumber = transaction.getSenderAccount();
				String accountReceiverNumber = transaction.getReceiverAccount();
				AccountEntity sender = accountRepo.findAccountByAccountNumber(accountSenderNumber);
				AccountEntity receiver = accountRepo.findAccountByAccountNumber(accountReceiverNumber);
				Double oldSenderBalance = sender.getAccountBalance();
				Double oldReceiverBalance = receiver.getAccountBalance();

				if (oldSenderBalance - value < 0 || oldReceiverBalance + value > receiver.getAvailableBalance()) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					Double newSenderBalance = oldSenderBalance - (value / worthFor * buyingRate);
					Double newReceiverBalance = oldReceiverBalance + (value / worthFor * buyingRate);

					TransactionEntity newTransaction = new TransactionEntity();

					newTransaction.setCurrency(currency);
					newTransaction.setModel(transaction.getModel());
					newTransaction.setValue(transaction.getValue());
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
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getTransactions() {
		try {

			List<TransactionEntity> transactions = transactionRepo.findAll();

			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
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
	@RequestMapping(method = RequestMethod.GET, value = "/send/{accountNumber}")
	public ResponseEntity<?> getAllSendingTransactions(@PathVariable String accountNumber) {
		try {

			List<TransactionEntity> transactions = transactionRepo.findTransactionsBySenderAccount(accountNumber);
			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/receive/{accountNumber}")
	public ResponseEntity<?> getAllReceivingTransactions(@PathVariable String accountNumber) {
		try {

			List<TransactionEntity> transactions = transactionRepo.findTransactionsByReceiverAccount(accountNumber);
			return new ResponseEntity<List<TransactionEntity>>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private Boolean validateChecksum(String model, String referenceNumber) {
        Integer modelInt = Integer.parseInt(model);
        String firstK = referenceNumber.substring(0, 2);
        char[] lastK = (referenceNumber.substring(2, referenceNumber.length())).toCharArray();
        String p = "";
        Pattern patN = Pattern.compile("[0-9]");
        Pattern patL = Pattern.compile("[A-Z]");
        for (int i = 0; i < lastK.length; i++) {
            Matcher mN = patN.matcher(String.valueOf(lastK[i]));
            Matcher mL = patL.matcher(String.valueOf(lastK[i]));
            if (mN.find()) {
                p += lastK[i];
            } else if (mL.find()) {
                p += String.valueOf((int) lastK[i] - 55);
            } else {
                break;
            }
        }
//      * 10 % 10 / 10  decimal part of a number
        Double decR = ((Double.parseDouble(p) * 100) / modelInt) * 10 % 10 / 10;
        Long b = modelInt + 1 - Math.round(decR * modelInt);
        if (b < 10)
            b = Long.parseLong("0" + String.valueOf(b));

        if (b == Long.parseLong(firstK)) {
            return true;
        } else {
            return false;
        }

	}

}
