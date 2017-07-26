package com.auth.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entities.ClientEntity;
import com.auth.entities.RoleEntity;
import com.auth.repositories.ClientRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ebank/client")
public class ClientController {

	@Autowired
	private ClientRepository clientRepo;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ResponseEntity<?> getClients() {
		try {
			List<ClientEntity> clients = clientRepo.findAll();
			return new ResponseEntity<List<ClientEntity>>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getClientById() {
		try {
			List<ClientEntity> clients = clientRepo.findAll();
			return new ResponseEntity<List<ClientEntity>>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json")
	public ResponseEntity<?> createClient(@RequestBody ClientEntity client) {
		try {
			clientRepo.save(client);
			return new ResponseEntity<ClientEntity>(client, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
