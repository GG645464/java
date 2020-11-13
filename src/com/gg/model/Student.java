package com.gg.model;

public class Student {

	private int id;
	private String name;
	private int age;
	private String password;
	private Classes classes;
	private Type type;
	
	
	
	
	public Student(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}


	public Student(String name, String password, Type type) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	
	public Student(int id, String name, int age, String password, Classes classes) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.password = password;
		this.classes = classes;
	}


	public Student(String name, int age, String password, Classes classes, Type type) {
		super();
		this.name = name;
		this.age = age;
		this.password = password;
		this.classes = classes;
		this.type = type;
	}


	public Student(int id, String name, int age, String password, Classes classes, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.password = password;
		this.classes = classes;
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
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
		
	

}
