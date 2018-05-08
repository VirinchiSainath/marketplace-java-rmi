// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.model;

/*
 * This is the exception raised when there is 
 * wrong authorization
 */
public class AuthorizationException extends RuntimeException {
	
	public AuthorizationException (String methodName){
		super("Invalid Authorization - Access Denied to " + methodName + "() function!");
	}

}
