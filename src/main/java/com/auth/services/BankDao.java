package com.auth.services;

import java.util.List;

import com.auth.entities.BankEntity;
import com.auth.entities.ClientEntity;

public interface BankDao {
	
	List<ClientEntity> findClientsByBankId(Integer id);

}
