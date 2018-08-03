package com.axiomatics.configuration;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import com.axiomatics.controller.LoginController;
import com.axiomatics.event.DecisionEvent;
import com.axiomatics.model.Risk;
import com.axiomatics.repository.UserRepository;
import com.axiomatics.sdk.context.SDKResponse;
import com.axiomatics.sdk.context.XacmlObjectStateException;
import com.axiomatics.spring.security.xacml.AbstractXACMLWebSecurityExpressionRoot;

@Component
@Lazy
public class XACMLWebSecurityExpressionRoot extends AbstractXACMLWebSecurityExpressionRoot {
	static Logger log = LoggerFactory.getLogger(XACMLWebSecurityExpressionRoot.class);

	public XACMLWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
		super(a, fi);
	}

	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Autowired
    private LoginController loginController;

	/*
	 * Details about the http request can be retrieved using the getters provided by
	 * AbstractXACMLWebSecurityExpressionRoot
	 */
	HttpServletRequest request = super.getRequest();
	String fullRequesrUrl = super.getFullRequestUrl();


	@Override
	public void setDefaultAttributes() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		attrCatAry.add("SUBJECT");
		attrTypeAry.add("STRING");
		attrIdAry.add("com.axiomatics.emailAddress");
		attrValAry.add(auth.getName());
		Collection<?> authorities = auth.getAuthorities();
		for (Iterator<?> roleIter = authorities.iterator(); roleIter.hasNext();) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) roleIter.next();
			attrCatAry.add("SUBJECT");
			attrTypeAry.add("STRING");
			attrIdAry.add("role");
			attrValAry.add(grantedAuthority.getAuthority());
		}
	}

	/**
	 * Implementation of method that is called when XACML is used in taglib for UI
	 * display control
	 */
	@Override
	public void uiDecisionSetDefaultAttributes() {
		// TODO Add default attributes for decisions related to UI elements access
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

	/**
	 * Implementation of method that is called when XACML is used to control access
	 * to URLs. We are not using this method in this tutorial.
	 */
	@Override
	public void urlDecisionSetDefaultAttributes() {
		// TODO Add default attributes for decisions related to URL access

	}
	
	
	@Override
	public boolean processXACMLResponse(SDKResponse response){
	    
		if(response.getDecision()==0) {
			System.out.println("Allowed access. ");
			Risk risk = new Risk();
			risk.setScore("0");
			log.info("risk score is set to : " + risk.getScore() + "inside processXACMLResposne method");
/*			try {
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