package com.auth.controllers;

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
import com.auth.repositories.AccountRepository;
import com.auth.repositories.ClientRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ebank/account")
public class AccountController {
	
	
	@Autowired
	private ClientRepository clientRepo;
	
	
	@Autowired
	private AccountRepository accountRepo;
	
		
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getAccountsById(@PathVariable Integer id) {
		try {
			ClientEntity client = clientRepo.findOne(id);
			List<AccountEntity> accounts = client.getAccounts();
			return new ResponseEntity<List<AccountEntity>>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}", consumes = "application/json")
	public ResponseEntity<?> createAccount(@RequestBody AccountEntity account,
										  @PathVariable Integer id) {
		try {
			ClientEntity client = clientRepo.findOne(id);
			List<AccountEntity> accounts = client.getAccounts();
			accounts.add(account);
			client.setAccounts(accounts);
			accountRepo.save(account);
			return new ResponseEntity<AccountEntity>(account, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}
