package com.axiomatics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.axiomatics.model.User;
import com.axiomatics.service.UserService;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
/*	 @Autowired
	    UserRepository userRepository;*/
	
	static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private int lastDecision;

	public int getLastDecision() {
		return lastDecision;
	}

	public void setLastDecision(int lastDecision) {
		this.lastDecision = lastDecision;
	}

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		
		return modelAndView;
	}
	
/*    @MessageMapping("/riskd")
    @SendTo("/topic/risk")
    public WebMessage webMessage(Risk risk) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new WebMessage("Hello you've received, " + HtmlUtils.htmlEscape(risk.getScore()) + "!");
    }*/

    
    @GetMapping(value="/secret")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'secretmessage'})")
    @ResponseBody
    public String getSecret() {
		return "Permit secret data";
    	
    }
    
    @GetMapping(value="/topsecret")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'topsecret'})")
    @ResponseBody
    public String getTopSecret() {
		return "Permit top secret data";
    	
    }
    
    @GetMapping(value="/unclassified")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'unclassified'})")
    @ResponseBody
    public String getUnclassified() {
		return "Permit unclassified data";
    	
    }
    
    // end classification auth and start arbitrary
    @GetMapping(value="/pii")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'pii'})")
    @ResponseBody
    public String getPii() {
		return "Permit PII data";
    	
    }
    
    @GetMapping(value="/centos")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'centos'})")
    @ResponseBody
    public String getCentos() {
		return "Permit CentOS data";
    	
    }
    
    @GetMapping(value="/admin")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'admin'})")
    @ResponseBody
    public String getAdmin() {
		return "Permit admin data";
    	
    }
    
    @GetMapping(value="/midlevel")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'mid'})")
    @ResponseBody
    public String getMidlevel() {
		return "Permit mid level access data";
    	
    }
    
    @GetMapping(value="/hr")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'hr'})")
    @ResponseBody
    public String getHr() {
		return "Permit hr data";
    	
    }
    
    @GetMapping(value="/it")
    @PreAuthorize("xacmlDecisionPreAuthz({'RESOURCE'}, {'STRING'}, {'urn:oasis:names:tc:xacml:1.0:resource:resource-id'}, {'it'})")
    @ResponseBody
    public String getIt() {
		return "Permit IT data";
    	
    }
    
    // return riskscore through ajax request
    
    @GetMapping(value="/riskscore")
    @ResponseBody
    public String getRiskScore() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
    	int riskscore = user.getRisk();
    	return " " + riskscore;
    }
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("riskscore" , "Risk score is : " + user.getRisk());
		//Optional<String> advice = Optional.ofNullable(user.getAdvice());
		//modelAndView.addObject("advice", advice);
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

}