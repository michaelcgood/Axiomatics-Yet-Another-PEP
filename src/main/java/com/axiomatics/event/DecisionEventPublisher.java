package com.axiomatics.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class DecisionEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
 
    public void doStuffAndPublishAnEvent(final String message) {
        System.out.println("Publishing custom event. ");
        DecisionEvent decisionEvent = new DecisionEvent(this, message);
        applicationEventPublisher.publishEvent(decisionEvent);
    }
}
