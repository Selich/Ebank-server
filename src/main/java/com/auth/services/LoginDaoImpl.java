package com.auth.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.auth.entities.ClientEntity;

@Service
public class LoginDaoImpl implements LoginDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public ClientEntity findClientByEmailAndPassword(String email, String password) {
		String sql = "SELECT * FROM client WHERE email=:email AND password=:password";
		Query q = em.createQuery(sql);
		q.setParameter("email", email);
		q.setParameter("password", password);
		ClientEntity client = new ClientEntity();
		client = (ClientEntity) q.getResultList();
		return client;
	}
	
	

}
