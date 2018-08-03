package com.axiomatics.event;

import org.springframework.context.ApplicationEvent;

public class DecisionEvent extends ApplicationEvent {
    /**
	 * random static final long uid
	 */
	private static final long serialVersionUID = 5658763017387429370L;
	private String message;
	private String advice;
 
    public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public DecisionEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    
    public DecisionEvent(Object source, String message, String advice) {
    	super (source);
    	this.message = message;
    	this.advice= advice;
    }
}
