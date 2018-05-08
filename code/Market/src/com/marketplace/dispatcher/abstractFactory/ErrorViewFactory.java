// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

// Ryan: Always include useful comments in each file.
//Fixed
package com.marketplace.dispatcher.abstractFactory;

//Ryan: Always include useful comments in each file.
//Fixed

/*
 * This factory is used to creat all error view pages
 * such as LoginError and PageNotFountError
 */

import com.marketplace.view.LoginErrorView;
import com.marketplace.view.PageNotFoundErrorView;
import com.marketplace.view.ViewInterface;

public class ErrorViewFactory implements AbstractFactory{

	@Override
	public ViewInterface createView(String viewDescription) {
		
		if (viewDescription.equals("loginError")){
			return new LoginErrorView();
		}
		
		return new PageNotFoundErrorView();
	}

}
