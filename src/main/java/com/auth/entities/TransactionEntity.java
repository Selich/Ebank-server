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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

	@Id
	@GeneratedValue
	@Column(name = "transaction_id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "senderAccount")
	private AccountEntity senderAccount;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "receiverAccount")
	private AccountEntity receiverAccount;

	@Column(name = "sender_desc")
	private String senderDescription;

	@Column(name = "payment_code")
	private String paymentCode;

	 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @JoinColumn(name = "currency")
	 private CurrencyEntity currency;

	@Column(name = "value")
	private Double value;

	@Column(name = "model")
	private String model;

	@Column(name = "referenceNumber")
	private String referenceNumber;

	@Column(name = "transaction_date")
	private String transactionDate;

	public TransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public CurrencyEntity getCurrency() {
		return currency;
	}



	public void setCurrency(CurrencyEntity currency) {
		this.currency = currency;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AccountEntity getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(AccountEntity senderAccount) {
		this.senderAccount = senderAccount;
	}

	public AccountEntity getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(AccountEntity receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getSenderDescription() {
		return senderDescription;
	}

	public void setSenderDescription(String senderDescription) {
		this.senderDescription = senderDescription;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

}