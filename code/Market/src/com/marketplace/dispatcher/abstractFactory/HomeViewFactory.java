// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.dispatcher.abstractFactory;

/*
 * This is for creating home views for customer
 * and admin dynamically.
 */

import com.marketplace.view.AdminHomeView;
import com.marketplace.view.CustomerHomeView;
import com.marketplace.view.PageNotFoundErrorView;
import com.marketplace.view.ViewInterface;

public class HomeViewFactory implements AbstractFactory{

	@Override
	public ViewInterface createView(String viewDescription) {
		
		if (viewDescription.equalsIgnoreCase("customer")){
			return new CustomerHomeView();
		}
		else if (viewDescription.equalsIgnoreCase("admin")){
			return new AdminHomeView();
		}
		
		return new PageNotFoundErrorView();
		
	}
	
	

}
