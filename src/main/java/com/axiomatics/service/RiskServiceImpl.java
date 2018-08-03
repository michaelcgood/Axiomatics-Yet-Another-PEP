package com.axiomatics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.axiomatics.model.User;
import com.axiomatics.repository.UserRepository;

@Service
public class RiskServiceImpl implements RiskService {
	
	@Autowired
	UserRepository userRepository;
	
/*	@Autowired
	RiskRepository riskRepository;*/
	
	Logger log = LoggerFactory.getLogger(RiskServiceImpl.class);

	@Override
	public Integer increaseRiskScore() {
		log.info("##Beginning risk score ###");
		// TODO Auto-generated method stub
		// GET USER PRINCIPAL INFO AND THEN SET RISK SCORE AND INCREMENT IT
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("#auth is : " + auth.getName() +  " ###");
		try {
			User user = userRepository.findByEmail(auth.getName());
			int riskInt = user.getRisk();
			log.info("The current user :: " + auth.getName() + "has the risk score : " + riskInt + "before incrementing risk score.");
			
			riskInt++;
			
			// if the risk score is equivalent to 3, 
			user.setRisk(riskInt);
			userRepository.save(user);
			log.info("risk int ++ : " + riskInt++ + "risk int ++ again : " + riskInt++);
			log.info("The current user's risk score has been incremented to : " + user.getRisk());
			
		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;
	}

	@Override
	public boolean freezeUserAccount() {
		// TODO Auto-generated method stub
		return false;
	}

}
