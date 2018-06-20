package com.VoluntaryWork.VoluntaryLoginPage.Model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name="USER_DETAILS")
public class UserTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;
	
	
	@Column(name="EMAIL")
    @Email(message="*Please Provide a Valid Email")
	@NotEmpty(message="*Email Cannot be left blank")
    private String email;
	
	@Column(name="PASSWORD")
    @Length(min =5 ,message="Password Should be greater than 5 character")
	@NotEmpty(message="Password cannot be left empty")
	@Transient
	private String password;
	
	@Column(name="FIRST_NAME")
	@NotEmpty(message="First Name Cannot be left blank")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="USER_NAME")
	@NotEmpty(message="Please Enter a User Name")
	private String userName;
	
	/*@Column(name="USER_ROLE")
	@NotEmpty(message="Please Select Type of User")
	private String user_role;*/
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role_mapping",joinColumns=@JoinColumn(name="USER_ID") ,inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
    
	private Set<RoleTO> roles;


	public Set<RoleTO> getRoles() {
		return roles;
	}


	public void setRoles(Set<RoleTO> roles) {
		this.roles = roles;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	/*public String getUser_role() {
		return user_role;
	}*/


	/*public void setUser_role(String user_role) {
		this.user_role = user_role;
	}*/


	
	
}
