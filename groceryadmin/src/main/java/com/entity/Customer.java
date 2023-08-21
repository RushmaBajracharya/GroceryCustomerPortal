package com.entity;
//import java.util.Date;
public class Customer {
	private int id;
	private String name;
	private String join_date;
	private String username;
	private String password;
	private String email;
	private String contact;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String username, String password, String email, String contact) {
		super();
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}
	

	public Customer(int id, String name, String username, String password, String email,
			String contact) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return join_date;
	}

	public void setDate(String join_date) {
		this.join_date = join_date;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", join_date=" + join_date + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", contact=" + contact + "]";
	}
	
	
	
}
