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

import com.auth.entities.ClientEntity;
import com.auth.repositories.ClientRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ebank/client")
public class ClientController {

	@Autowired
	private ClientRepository clientRepo;
	
//	@Autowired
//	private Encryption encryption;
	
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
	public ResponseEntity<?> getClientById(@PathVariable Integer id) {
		try {
			ClientEntity client = clientRepo.findOne(id);
			return new ResponseEntity<ClientEntity>(client, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json")
	public ResponseEntity<?> createClient(@RequestBody ClientEntity client) {
		try {
//			String oldPass = client.getPassword();
//			String newPass = encryption.getPassEncoded(oldPass);
//			client.setPassword(newPass);
			clientRepo.save(client);
			return new ResponseEntity<ClientEntity>(client, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = "application/json")
	public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
		try {
			ClientEntity client = clientRepo.findOne(id);
			clientRepo.delete(client);
			return new ResponseEntity<ClientEntity>(client, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<?> updateClient(@PathVariable Integer id, @RequestBody ClientEntity client) {
		try {
			ClientEntity currentClient = clientRepo.findOne(id);
			currentClient.setFirstName(client.getFirstName());
			currentClient.setLastName(client.getLastName());
			currentClient.setAddress(client.getAddress());
			currentClient.setJmbg(client.getJmbg());
			currentClient.setEmail(client.getEmail());
			currentClient.setPassword(client.getPassword());
			clientRepo.save(currentClient);
			return new ResponseEntity<ClientEntity>(currentClient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
