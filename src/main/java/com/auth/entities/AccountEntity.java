package com.auth.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "account_balance")
	private Integer accountBalance;
	@Column(name = "available_balance")
	private Integer availableBalance;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "client")
	private ClientEntity client;

//	@JsonManagedReference
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "bank")
//	private BankEntity bank;
//	
//	@JsonBackReference
//	@OneToMany(mappedBy = "senderAccount", fetch = FetchType.LAZY, cascade= { CascadeType.ALL})
//	private List<TransactionEntity> senderTransaction = new ArrayList<>();
//
//	@JsonBackReference
//	@OneToMany(mappedBy = "recieverAccount", fetch = FetchType.LAZY, cascade= { CascadeType.ALL})
//	private List<TransactionEntity> recieverTransaction = new ArrayList<>();
	

//	@JsonBackReference
//	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade= { CascadeType.ALL})
//	private List<TransactionEntity> transactions = new ArrayList<>();
	
	
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

	public Integer getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Integer getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Integer availableBalance) {
		this.availableBalance = availableBalance;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}



}
