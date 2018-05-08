// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.entity;

import java.io.Serializable;

//Ryan: Always include useful comments in each file.
//Fixed

/*
 * This is pojo class for admin
 */

public class Admin implements Serializable{
	
	private int admin_id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	
	
	public Admin() {
		super();
	}

	public Admin(int admin_id, String firstName, String lastName, String username, String password) {
		super();
		this.admin_id = admin_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	// getters and setters	
	
	
	public String getUsername() {
		return username;
	}
	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
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

	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + "]";
	}
	
	
	
	
}