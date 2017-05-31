package org.merlin.quick.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;


//should implement like this
//public class User implements UserDetails, CredentialsContainer

@Entity
@Table(name = "users")
public class User {
	@Id
	private String username;
	@NotNull
	private String password;
	@NotNull
	private int enabled;
	
	public User(String username, String password, int enabled) {
		this.username = username;	
		this.enabled = enabled;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}