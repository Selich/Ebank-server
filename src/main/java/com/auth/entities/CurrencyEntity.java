package com.auth.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "currency")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CurrencyEntity {
	
	@Id
	private Integer id;
	
	@Column(name = "country_name")
	private String countryName;

	@Column(name = "currency_symbol")
	private String currencySymbol;
	@Column(name = "worth_for")
	private Integer worthFor;
	@Column(name = "buying_rate")
	private Double buyingRate;
	@Column(name = "selling_rate")
	private Double sellingRate;
	
	
	@JsonIgnoreProperties("currency")
	@OneToMany(mappedBy = "currency",
			   cascade  = CascadeType.ALL,
			   fetch    = FetchType.LAZY)
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
	public Integer getWorthFor() {
		return worthFor;
	}
	public void setWorthFor(Integer worthFor) {
		this.worthFor = worthFor;
	}
	public Double getBuyingRate() {
		return buyingRate;
	}
	public void setBuyingRate(Double buyingRate) {
		this.buyingRate = buyingRate;
	}
	public Double getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(Double sellingRate) {
		this.sellingRate = sellingRate;
	}
	public List<TransactionEntity> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	
}
