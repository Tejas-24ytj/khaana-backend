
package com.capgemini.khaana.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * "users": [ { "name": "Alice Johnson", "email": "alice@example.com",
 * "password": "hashed_pw_1", "location": "Downtown", "phone": "1234567890",
 * "userType": "owner", "id": "u1a9b" }]
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String password;
	private String location;
	private String phone;
	private String userType;

	public User() {
	}

	public User(Long id, String name, String email, String password, String location, String phone, String userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.location = location;
		this.phone = phone;
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", location="
				+ location + ", phone=" + phone + ", userType=" + userType + "]";
	}

}
