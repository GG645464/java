package com.gg.model;

public class Test {
	private int id;
	private String testName;
	private int time;
	public Test(int id) {
		super();
		this.id = id;
	}
	public Test(int id, String testName) {
		super();
		this.id = id;
		this.testName = testName;
	}
	
	public Test(int id, String testName, int time) {
		super();
		this.id = id;
		this.testName = testName;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
	
}
