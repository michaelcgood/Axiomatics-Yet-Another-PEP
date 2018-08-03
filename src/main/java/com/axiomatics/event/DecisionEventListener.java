package com.axiomatics.event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.axiomatics.model.User;
import com.axiomatics.repository.UserRepository;
import com.axiomatics.service.RiskService;

@Component
public class DecisionEventListener implements ApplicationListener<DecisionEvent> {
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	UserRepository userRepository;
	
    @Override
    public void onApplicationEvent(DecisionEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
        // do risk service
        riskService.increaseRiskScore();
        
        if(Optional.of(event.getAdvice()).isPresent()) {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	User user = userRepository.findByEmail(auth.getName());
        	user.setAdvice(event.getAdvice());
        	System.out.println("User advice has been set: " + user.getAdvice());
        	userRepository.save(user);
        }
        // maybe like if decision is PERMIT
        // show permit message
        
        // if decision is DENY
        // increase risk score and show deny message
    }
}
