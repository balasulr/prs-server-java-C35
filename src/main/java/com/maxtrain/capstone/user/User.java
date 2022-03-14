package com.maxtrain.capstone.user;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name="UDIX_username", columnNames={"username"}))
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30,nullable=false)
	private String username;
	@Column(length=30,nullable=false)
	private String password;
	@Column(length=30,nullable=false)
	private String firstName;
	@Column(length=30,nullable=false)
	private String lastName;
	@Column(length=12,nullable=true)
	private String phone;
	@Column(length=255,nullable=true)
	private String email;
	private boolean isReviewer;
	private boolean isAdmin;

	// Default Constructor:
	public User() {}

	// Getters & Setters:
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isReviewer() {
		return isReviewer;
	}

	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}

// Notes:
// The line below @Entity defines the username column to be unique

// Both phone & email are allowed to be null (Null is set to Yes)
// Since both phone & email have the null as Yes, can have the nullable be true or remove completely