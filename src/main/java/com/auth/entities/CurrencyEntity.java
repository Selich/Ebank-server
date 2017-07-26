package com.auth.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CurrencyEntity {
	
	@Id
	private Integer id;
	
	@Column(name = "country_name")
	private String countryName;

	@Column(name = "currency_symbol")
	private String currencySymbol;
	@Column(name = "worth_for")
	private String worthFor;
	@Column(name = "buying_rate")
	private String buyingRate;
	@Column(name = "selling_rate")
	private String sellingRate;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "currency", fetch = FetchType.LAZY, cascade= { CascadeType.ALL})
	private List<TransactionEntity> transactions = new ArrayList<>();
	public CurrencyEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public String getBuyingRate() {
		return buyingRate;
	}
	public void setBuyingRate(String buyingRate) {
		this.buyingRate = buyingRate;
	}
	public String getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(String sellingRate) {
		this.sellingRate = sellingRate;
	}
	public String getWorthFor() {
		return worthFor;
	}
	public void setWorthFor(String worthFor) {
		this.worthFor = worthFor;
	}
	
	
}
