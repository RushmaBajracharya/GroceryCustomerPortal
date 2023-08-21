package com.entity;

public class ChangePassword {
	private String username;
	private String password;
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePassword(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String setUsername(String username) {
		return this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String setPassword(String password) {
		return this.password = password;
	}
	}
