// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.dispatcher.abstractFactory;

import com.marketplace.view.CartView;

/*
 * This factory is for creating HomeViewFactory or
 * ErrorViewFactory.
 */

import com.marketplace.view.ViewInterface;

public class FactoryCreator {
	
	public AbstractFactory createFactory(String factoryType){
		
		if(factoryType.equals("home")){
			return new HomeViewFactory();
		}
		else if(factoryType.equals("cart")){
			return new CartViewFactory();
		}
		
		return new ErrorViewFactory();
	}

}
