package com.gg.model;

public class Classes {
	private int id;
	private String name;
	
	
	
	public Classes(int id) {
		super();
		this.id = id;
	}
	public Classes(String name) {
		super();
		this.name = name;
	}
	public Classes(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
