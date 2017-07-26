package com.auth.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressEntity {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	private String street;
	private String city;
	private String country;
	
	@Version
	private Integer version;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address",
			   cascade  = CascadeType.REFRESH,
			   fetch    = FetchType.LAZY)
	private List<ClientEntity> clients = new ArrayList<>();
//
	
	public AddressEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<ClientEntity> getClients() {
		return clients;
	}
	public void setClients(List<ClientEntity> clients) {
		this.clients = clients;
	}
	
	
	

	

}
