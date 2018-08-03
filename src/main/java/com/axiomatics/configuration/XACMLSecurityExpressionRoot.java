package com.axiomatics.configuration;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.axiomatics.controller.LoginController;
import com.axiomatics.event.DecisionEvent;
import com.axiomatics.model.Risk;
import com.axiomatics.repository.UserRepository;
import com.axiomatics.sdk.context.SDKResponse;
import com.axiomatics.spring.security.xacml.AbstractXACMLSecurityExpressionRoot;

@Component
@Lazy
public class XACMLSecurityExpressionRoot extends AbstractXACMLSecurityExpressionRoot {
	static Logger log = LoggerFactory.getLogger(XACMLSecurityExpressionRoot.class.getName());
	public XACMLSecurityExpressionRoot(Authentication a) {
		super(a);
	}
	
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private LoginController loginController;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
	@Override
	protected void setDefaultAttributes() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		attrCatAry.add("SUBJECT");
		attrTypeAry.add("STRING");
		attrIdAry.add("com.axiomatics.emailAddress");
		attrValAry.add(auth.getName());
		Collection<?> authorities = auth.getAuthorities();
		for (Iterator<?> roleIter = authorities.iterator(); roleIter.hasNext();){
			GrantedAuthority grantedAuthority = (GrantedAuthority) roleIter.next();
			attrCatAry.add("SUBJECT");
			attrTypeAry.add("STRING");
			attrIdAry.add("role");
			attrValAry.add(grantedAuthority.getAuthority());
		}
		
		// add risk score too
		attrCatAry.add("SUBJECT");
		attrTypeAry.add("INTEGER");
		attrIdAry.add("com.axiomatics.riskScore");
		int riskScore= userRepository.findByEmail(auth.getName()).getRisk();
		log.info("risk score of user is added as attribute : " + riskScore);
		attrValAry.add(riskScore);
		
		// add access level
		
		attrCatAry.add("SUBJECT");
		attrTypeAry.add("INTEGER");
		attrIdAry.add("com.axiomatics.accessLevel");
		int accessLevel= userRepository.findByEmail(auth.getName()).getAccess();
		log.info("access level added as attribute : " + accessLevel);
		attrValAry.add(accessLevel);
		
		// add training completed
		
		attrCatAry.add("SUBJECT");
		attrTypeAry.add("STRING");
		attrIdAry.add("com.axiomatics.training");
		String training= userRepository.findByEmail(auth.getName()).getTraining();
		log.info("training is added as attribute : " + training);
		attrValAry.add(training);
	}
	
	@Override
	protected void postAuthzDecisionSetDefaultAttributes() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void postFilterDecisionSetDefaultAttributes() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void preAuthzDecisionSetDefaultAttributes() {
		// 
		log.info("PRE AUTH Z DECISION SET DEF ATTRS CALLED!");
		// 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		attrCatAry.add("SUBJECT");
		attrTypeAry.add("INTEGER");
		attrIdAry.add("com.axiomatics.seniority");
		Integer userId = null;
		try {
			userId = userRepository.findByEmail(auth.getName()).getSeniority();
		} catch (Exception e) {
			log.info(e.toString());
		}
		attrValAry.add(userId);
		log.info("UI DECISION SET DEFAULT ATTRIBUTE WORKING...");

		
	}
	@Override
	protected void preFilterDecisionSetDefaultAttributes() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean processXACMLResponse(SDKResponse response){
	    
		if(response.getDecision()==0) {
			System.out.println("allowed!!!!!!!!!!!!! ");
			Risk risk = new Risk();
			risk.setScore("0");
			log.info("risk score is set to : " + risk.getScore() + "inside processXACMLResposne method");
			/*try {
				log.info("trying to send web message...");
				loginController.webMessage(risk);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("the try catch block in processXACMLResponse triggered catch");
				e.printStackTrace();
			}*/
			return permitAll;
		}else {
			System.out.println("Denied access. ");
	        System.out.println("Publishing custom event. ");
	        DecisionEvent decisionEvent = new DecisionEvent(this, this.getClass().getName());
	        applicationEventPublisher.publishEvent(decisionEvent);
			return denyAll;
		}

	}

}
