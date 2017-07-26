package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth.entities.ClientEntity;
import com.auth.repositories.ClientRepository;
import com.auth.services.LoginDao;

@Controller
@RequestMapping("/api/v1/ebank/auth")
public class LoginController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private LoginDao loginDao;
	
	
	@RequestMapping(method = RequestMethod.POST,
					value  = "/login",
					consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> login(
			@RequestBody ClientEntity client
	){
		try{
//			ClientEntity client =  loginDao.findClientByEmailAndPassword(email, password);
			return new ResponseEntity<ClientEntity>(clientRepo.findByEmailAndPassword(client.getEmail(), client.getPassword()), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
//	@RequestMapping(method = RequestMethod.POST,
//					value  = "/login",
//					consumes = MediaType.APPLICATION_JSON_VALUE
//			)
//	public ResponseEntity<?> login(
//			@RequestParam String email,
//			@RequestParam String password
//	){
//		try{
//			ClientEntity client =  loginDao.findClientByEmailAndPassword(email, password);
//			return new ResponseEntity<ClientEntity>(clientRepo.findByEmailAndPassword(email,password), HttpStatus.OK);
//		} catch (Exception e){
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//	}

}
