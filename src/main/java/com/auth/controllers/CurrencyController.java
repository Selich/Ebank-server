package com.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entities.CurrencyEntity;
import com.auth.repositories.CurrencyRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ebank/currency")
public class CurrencyController {
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ResponseEntity<?> getCurrencies() {
		try {
			List<CurrencyEntity> currencies = currencyRepo.findAll();
			return new ResponseEntity<List<CurrencyEntity>>(currencies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
