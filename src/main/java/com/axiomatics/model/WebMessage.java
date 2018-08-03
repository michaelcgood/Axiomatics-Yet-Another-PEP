package com.axiomatics.model;

public class WebMessage {
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public WebMessage() {
		
	}
	
	public WebMessage(String content) {
		this.content= content;
	}

}
