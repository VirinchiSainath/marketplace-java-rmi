// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.view;

import com.marketplace.controller.FrontController;
import com.marketplace.entity.UserSession;

public class CartView implements ViewInterface{

	@Override
	public void show(UserSession session, FrontController fc) {
		System.out.println(session.getCart());
		//showFunctions(session, fc);		
	}
	
	

}
