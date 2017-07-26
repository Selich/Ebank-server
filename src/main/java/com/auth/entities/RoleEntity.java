package com.auth.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Integer id;

	@Column(name = "role_name")
	private String name;

//	@JsonBackReference
//	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade= { CascadeType.ALL})
//	private List<ClientEntity> clients = new ArrayList<>();
//
	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<ClientEntity> getClients() {
//		return clients;
//	}
//
//	public void setClients(List<ClientEntity> clients) {
//		this.clients = clients;
//	}

	


}
