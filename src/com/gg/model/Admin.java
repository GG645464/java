package com.gg.model;

public class Admin {
	
	private int id;
	private String name;
	private String password;
	private Type type;
	private int age;
	
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	

	public Admin(String name, String password, Type type) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	

	public Admin(int id, String name, String password, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.type = type;
	}


	public Admin(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	
	
	
}
