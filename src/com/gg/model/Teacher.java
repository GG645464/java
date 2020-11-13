package com.gg.model;

public class Teacher {
	private int id;
	private String name;
	private int age;
	private String password;
	private Type type;
	
	
	public Teacher(int id, String name, int age, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.password = password;
	}
	
	

	public Teacher(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}



	public Teacher(String name, int age, String password) {
		super();
		this.name = name;
		this.age = age;
		this.password = password;
	}



	public Teacher(String name, String password, Type type) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	
	
	public Teacher(String name, int age, String password, Type type) {
		super();
		this.name = name;
		this.age = age;
		this.password = password;
		this.type = type;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	
	
	
}
