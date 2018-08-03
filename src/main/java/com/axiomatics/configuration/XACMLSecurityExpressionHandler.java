package com.axiomatics.configuration;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class XACMLSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler{
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * The use of 'new' to create the XACMLSecurityExpressionRoot instance will not work
	 * because it will not be visible to the Spring Security framework and hence all autowiring etc.
	 * will not be available to the instance.
	 * <p>
	 * Use the method below instead.
	 */
	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation){ 
		XACMLSecurityExpressionRoot root = (XACMLSecurityExpressionRoot) applicationContext.getBean("XACMLSecurityExpressionRoot", authentication);
		root.setThis(invocation.getThis()); 							// Sets the class object from where the jointcut is called 
		root.setPermissionEvaluator(getPermissionEvaluator());			// Expected by SecurityExpressionRoot. Don't change 
		root.setTrustResolver(new AuthenticationTrustResolverImpl());	// Expected by SecurityExpressionRoot. Don't change
		root.setRoleHierarchy(getRoleHierarchy());						// Expected by SecurityExpressionRoot. Don't change
		root.setProtectedMethod(invocation.getMethod());				// Sets the method protected by the annotation
		return root;
	}
}