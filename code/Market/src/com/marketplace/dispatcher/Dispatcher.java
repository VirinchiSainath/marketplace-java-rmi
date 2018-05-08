// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.dispatcher;

/*
 * This is the interface for Dispatcher
 * Its main function to create appropriate
 * views using factory
 */

import java.util.List;

import com.marketplace.controller.FrontController;
import com.marketplace.entity.Item;
import com.marketplace.entity.UserSession;

public interface Dispatcher {

	public void dispatch(UserSession session, String userView, FrontController fc);

	public void dispatchLoginView(String viewString, FrontController fc);

	public void dispatchItemsView(UserSession session, List<Item> itemList, FrontController fc);

}
