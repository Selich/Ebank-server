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

import com.auth.entities.BankEntity;
import com.auth.entities.ClientEntity;
import com.auth.repositories.BankRepository;
import com.auth.services.BankDao;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ebank/bank")
public class BankController {

	
	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private BankDao	bankDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getBanks() {
		try {

			List<BankEntity> banks = bankRepo.findAll();

			return new ResponseEntity<List<BankEntity>>(banks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method = RequestMethod.GET, value="/{id}/clients")
	public ResponseEntity<?> getClientsByBankId(@PathVariable Integer id) {
		try {
			List<ClientEntity> clients = bankDao.findClientsByBankId(id);
			return new ResponseEntity<List<ClientEntity>>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method = RequestMethod.POST, value="/create")
	public ResponseEntity<?> createBank(@RequestBody BankEntity bank) {
		try {
			bankRepo.save(bank);
			return new ResponseEntity<BankEntity>(bank, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<?> deleteBank(@PathVariable Integer id) {
		try {
			BankEntity bank = bankRepo.findOne(id);
			bankRepo.delete(bank);
			return new ResponseEntity<BankEntity>(bank, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
