package com.auth.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.auth.entities.ClientEntity;

@Service
public class BankDaoImpl implements BankDao{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<ClientEntity> findClientsByBankId(Integer id){
		String sql = "select distinct c.firstName, c.lastName, c.jmbg, c.email " +
					"from Client c, Account a, Bank b " + 
			        "where c.id = a.client and a.bank = :id";
		
		Query query = em.createQuery(sql);
		
		List<ClientEntity> clients = new ArrayList<>();
		clients = query.getResultList();
		return clients;
	}

}
