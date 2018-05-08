// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

/*
In case there is mistake in the login in the controller and 
the appropirate view is not created user will be directed instead 
of a stack trace or wrong view.
*/
package com.marketplace.view;

/*
 * This view is shown when page is not found
 */

import com.marketplace.controller.FrontController;
import com.marketplace.entity.UserSession;

public class PageNotFoundErrorView implements ErrorViewInterface{
	
	
	@Override
	public void show(UserSession session, FrontController fc) {
		System.out.println("Page Not Found");
		
	}

}
