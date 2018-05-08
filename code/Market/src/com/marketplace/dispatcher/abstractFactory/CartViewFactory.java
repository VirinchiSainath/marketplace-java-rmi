// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.dispatcher.abstractFactory;

import com.marketplace.view.CartView;
import com.marketplace.view.ViewInterface;

public class CartViewFactory implements AbstractFactory{

	@Override
	public ViewInterface createView(String viewDescription) {
		return new CartView();
	}
}
