package com.VoluntaryWork.VoluntaryLoginPage.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class RoleTO {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ROLE_ID")
	private int id;
	@Column(name="ROLE")
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
