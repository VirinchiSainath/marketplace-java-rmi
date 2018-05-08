// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.controller;

import com.marketplace.entity.Admin;
import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.marketplace.entity.PurchaseReceipt;
import com.marketplace.entity.UserSession;

public interface FrontController {
	
	/*
	 * This method will connect to Model by calling a method in ModelImpl
	 */
	public void connectToModel() throws Exception;
	
	public void authenticateUser(String username, String password, String userType);

	public void tellModelToShowProducts(UserSession session, FrontController fc);

	public PurchaseReceipt purchaseItemsInCart(UserSession session, FrontController fc);

	public void addItemToInventory(UserSession session, FrontController fc, Item item);

	public void addNewAdmin(UserSession session, FrontController fc, Admin admin);

	public Item updateItemById(UserSession session, FrontController fc, Item item);

	public boolean removeItemById(UserSession session, FrontController fc, int idToRemove);

	public boolean removeCustomerByUserName(UserSession session, FrontController fc, String customerUserNameToRemove);

	public boolean addNewCustomer(UserSession session, FrontController fc, Customer customer);

	public boolean addToCartByItemId(UserSession session, FrontController fc, int itemId);

	public void clearItemsInCart(UserSession session, FrontController fc);

	public boolean registerNewCustomer(FrontController fController, Customer customer);

	

}
