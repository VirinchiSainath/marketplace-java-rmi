// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.dispatcher.abstractFactory;

/*
 * The main purpose of this interface is to 
 * have a global abstract factory and all factories
 * implement from this interface
 */
import com.marketplace.view.ViewInterface;

public interface AbstractFactory {
	
	public ViewInterface createView(String viewDescription);
	
	
	

}
