package com.axiomatics.model;

public class Risk {
	private String score;
	
	private String advice;

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public Risk() {
		
	}
	
	public Risk(String score) {
		this.score = score;
	}
	
}