package com.ti.soap.documentsuploader.bean;

public class User {
	private int Id;
	private String name;
	private String type;
	private String email;

	public User(int id, String name, String type, String email) {
		super();
		Id = id;
		this.name = name;
		this.type = type;
		this.email = email;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
