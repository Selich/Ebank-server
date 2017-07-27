package com.auth.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AccountEntity {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "account_balance")
	private Double accountBalance;
	@Column(name = "available_balance")
	private Double availableBalance;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "client")
	private ClientEntity client;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "bank")
	private BankEntity bank;
	//
	@JsonIgnore
	@JsonIgnoreProperties("senderAccount")
	@OneToMany(mappedBy = "senderAccount",
			   cascade  = CascadeType.ALL,
			   fetch    = FetchType.LAZY)
	private List<TransactionEntity> sendingTransaction = new ArrayList<>();


	public AccountEntity() {
		super();
		// TODO Auto-generated constructor stub

	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}


	public Double getAvailableBalance() {
		return availableBalance;
	}


	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}


	public ClientEntity getClient() {
		return client;
	}


	public void setClient(ClientEntity client) {
		this.client = client;
	}


	public BankEntity getBank() {
		return bank;
	}


	public void setBank(BankEntity bank) {
		this.bank = bank;
	}


	public List<TransactionEntity> getSendingTransaction() {
		return sendingTransaction;
	}


	public void setSendingTransaction(List<TransactionEntity> sendingTransaction) {
		this.sendingTransaction = sendingTransaction;
	}
	
	


}
