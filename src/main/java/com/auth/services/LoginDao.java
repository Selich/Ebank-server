package com.auth.services;

import com.auth.entities.ClientEntity;

public interface LoginDao {
	
	
//	public findC
	public ClientEntity findClientByEmailAndPassword(String email, String password);

}
