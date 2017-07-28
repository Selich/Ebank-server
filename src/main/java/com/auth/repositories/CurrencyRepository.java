package com.auth.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.CurrencyEntity;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Integer> {

	List<CurrencyEntity> findAll();
    CurrencyEntity findCurrencyByCurrencySymbol(String currencySymbol);
}
