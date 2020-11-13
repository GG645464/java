package com.gg.model;

public class Subject {
	private int id;
	private String title;
	private String optionsA;
	private String optionsB;
	private String optionsC;
	private String optionsD;
	private String answer;
	private int score;
	private Test test;
	private String desc;
	
	
	
	public Subject(int id, String title, String optionsA, String optionsB, String optionsC, String optionsD,
			String answer, int score, Test test,String desc) {
		super();
		this.id = id;
		this.title = title;
		this.optionsA = optionsA;
		this.optionsB = optionsB;
		this.optionsC = optionsC;
		this.optionsD = optionsD;
		this.answer = answer;
		this.score = score;
		this.test = test;
		this.desc = desc;
	}
	
	public Subject(String title, String optionsA, String optionsB, String optionsC, String optionsD, String answer,
			int score, Test test, String desc) {
		super();
		this.title = title;
		this.optionsA = optionsA;
		this.optionsB = optionsB;
		this.optionsC = optionsC;
		this.optionsD = optionsD;
		this.answer = answer;
		this.score = score;
		this.test = test;
		this.desc = desc;
	}

	public Subject(int id, String title, String optionsA, String optionsB, String optionsC, String optionsD,
			String answer, int score) {
		super();
		this.id = id;
		this.title = title;
		this.optionsA = optionsA;
		this.optionsB = optionsB;
		this.optionsC = optionsC;
		this.optionsD = optionsD;
		this.answer = answer;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOptionsA() {
		return optionsA;
	}
	public void setOptionsA(String optionsA) {
		this.optionsA = optionsA;
	}
	public String getOptionsB() {
		return optionsB;
	}
	public void setOptionsB(String optionsB) {
		this.optionsB = optionsB;
	}
	public String getOptionsC() {
		return optionsC;
	}
	public void setOptionsC(String optionsC) {
		this.optionsC = optionsC;
	}
	public String getOptionsD() {
		return optionsD;
	}
	public void setOptionsD(String optionsD) {
		this.optionsD = optionsD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
