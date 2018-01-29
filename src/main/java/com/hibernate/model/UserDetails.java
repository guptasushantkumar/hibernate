package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_USER_DETAILS")
public class UserDetails {
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@Column(name = "USER_NAME")
	private String userName;
	@ElementCollection
	private Collection<Address> lisOfAddresses = new ArrayList<Address>();

	public Collection<Address> getLisOfAddresses() {
		return lisOfAddresses;
	}

	public void setLisOfAddresses(Collection<Address> lisOfAddresses) {
		this.lisOfAddresses = lisOfAddresses;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
		return "[User Name: " + userName + "\n Office Address: "
				+ lisOfAddresses + "]";
	}
}