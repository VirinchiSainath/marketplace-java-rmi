// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

/*
If the user enters wrong details he/she will be brought here.
*/

package com.marketplace.view;

/*
 * This the view when login fails
 */

import com.marketplace.controller.FrontController;
import com.marketplace.entity.UserSession;

public class LoginErrorView implements ErrorViewInterface {
	
	
	@Override
	public void show(UserSession session, FrontController fc) {
		System.out.println("login failed");
	}

}
