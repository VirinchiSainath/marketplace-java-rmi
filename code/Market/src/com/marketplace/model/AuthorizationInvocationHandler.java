// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.model;

/*
 * This is when model has to implement add to car
 * this class is checking if they caller has
 * the right access
 */

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.marketplace.controller.FrontController;
import com.marketplace.entity.UserSession;

public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4365200471136592493L;
	private Model model;
	
	public AuthorizationInvocationHandler(Model model) {
		this.model = model;		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if (method.isAnnotationPresent(RequiresRole.class)) {
			RequiresRole requiredRole = method.getAnnotation(RequiresRole.class);
			UserSession session = (UserSession) args[0];
			
				if (session.getUserType().equalsIgnoreCase(requiredRole.value())) {
					try{
					return 	method.invoke(model, args);
					}
					catch(InvocationTargetException e){
						throw e.getCause();
					}
				} 
				else {
					throw new AuthorizationException(method.getName());
				}
		} 
		else {
			try{
				return method.invoke(model, args);
			}
			catch (InvocationTargetException e){
				throw e.getCause();
			}
		}   
	}

}
