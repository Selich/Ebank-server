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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class ClientEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	private String jmbg;
	private String password;
	
	@Version
	private Integer version;
//
	@ManyToOne(cascade = CascadeType.ALL,
			   fetch   = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity address;

	@JsonIgnore
	@OneToMany(mappedBy = "client",
			   cascade  = CascadeType.REFRESH,
			   fetch    = FetchType.LAZY)
	private List<AccountEntity> accounts = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL,
			   fetch   = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;

	public ClientEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public RoleEntity getRole() {
		return role;
	}





	public void setRole(RoleEntity role) {
		this.role = role;
	}





	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public AddressEntity getAddress() {
		return address;
	}


	public void setAddress(AddressEntity address) {
		this.address = address;
	}


	public List<AccountEntity> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	
}
