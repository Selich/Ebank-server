package com.auth.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bank")
public class BankEntity {
	
	
	@Id
	@GeneratedValue
	@Column(name = "bank_id")
	private Integer id;
	@Column(name = "bank_name")
	private String bankName;

	@JsonIgnore
	@OneToMany(mappedBy = "bank", fetch = FetchType.LAZY, cascade= { CascadeType.REFRESH})
	private List<AccountEntity> accounts = new ArrayList<>();
	

	public BankEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	
	
	

}
