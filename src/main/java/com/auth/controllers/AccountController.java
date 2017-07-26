package com.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entities.AccountEntity;
import com.auth.entities.ClientEntity;
import com.auth.repositories.AccountRepository;
import com.auth.repositories.ClientRepository;

@RestController
@RequestMapping("/api/v1/ebank/account")
public class AccountController {
	
	
	@Autowired
	private ClientRepository clientRepo;
	
	
	@Autowired
	private AccountRepository accountRepo;
	
	
//	@RequestMapping(method = RequestMethod.GET,
//			        value  = "{id}")
//	public List<AccountEntity> getAccountsById(
//			@PathVariable Integer clientId
//
//	) {
//		ClientEntity client = clientRepo.findOne(clientId);
//		List<AccountEntity> accounts = client.getAccounts();
//		return accounts;
//	}
	
	
	
	

}
