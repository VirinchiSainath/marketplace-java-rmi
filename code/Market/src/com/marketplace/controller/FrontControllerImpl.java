// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.controller;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import com.marketplace.dispatcher.Dispatcher;
import com.marketplace.dispatcher.DispatcherImpl;
import com.marketplace.entity.Admin;
import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.marketplace.entity.PurchaseReceipt;
import com.marketplace.entity.UserSession;
import com.marketplace.model.Model;

public class FrontControllerImpl implements FrontController{
	
	private Model model;
	
	private Dispatcher dispatcher = new DispatcherImpl();

	/*
	 * This method will connect to Model by calling a method in ModelImpl
	 */	
	@Override
	public void connectToModel() throws RemoteException{
		String url = "//in-csci-rrpc01.cs.iupui.edu:2323/Server";
		try {
			
			// capturing remote Model Object by url
			model = (Model) Naming.lookup(url);
			
			model.connect(); //to ModelImpl
			int id=model.getVisitorNumber();
			System.out.println("Visit number: "+id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void authenticateUser(String username, String password, String userType){
		
		UserSession userSession = null;
		if (userType.equals("customer")){
			try {
				userSession = model.authenticateCustomer(username, password);
				if (userSession != null){
					dispatcher.dispatch(userSession, "customerHomeView", this);
				}
			} catch (RemoteException e) {e.printStackTrace();}
		}
		else if (userType.equals("admin")){
			try {
				userSession = model.authenticateAdmin(username, password);
				if (userSession != null) {
					dispatcher.dispatch(userSession, "adminHomeView", this);
				}
			} catch (RemoteException e) {e.printStackTrace();}
		}
		if (userSession == null){
			System.out.println("Authentication failed ! ");
		}
		
	}

	@Override
	public PurchaseReceipt purchaseItemsInCart(UserSession session, FrontController fc) {
		
		try {
			PurchaseReceipt receipt =  ((FrontControllerImpl)fc).model.purchaseItemsInCart(session);
			session.clearCart();
			return receipt;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	/*
	 * act on model to get all products from server
	 */
	@Override
	public void tellModelToShowProducts(UserSession session, FrontController fc) {
		List<Item> itemList = null;
		try {
			itemList = ((FrontControllerImpl)fc).model.getAllItems();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		dispatcher.dispatchItemsView(session, itemList, fc);
	}

	@Override
	public void addItemToInventory(UserSession session, FrontController fc, Item item) {
		
		try {
			((FrontControllerImpl)fc).model.addItemToInventory(session, item);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addNewAdmin(UserSession session, FrontController fc, Admin newAdmin) {
		try {
			((FrontControllerImpl)fc).model.addNewAdmin(session, newAdmin);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Item updateItemById(UserSession session, FrontController fc, Item item) {
		
		try {
			return ((FrontControllerImpl)fc).model.updateItemById(session, item);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public boolean removeItemById(UserSession session, FrontController fc, int idToRemove) {
		
		try {
			return ((FrontControllerImpl)fc).model.removeItemById(session, idToRemove);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeCustomerByUserName(UserSession session, FrontController fc, String customerUserNameToRemove) {
		try {
			return ((FrontControllerImpl)fc).model.removeCustomerByUserName(session, customerUserNameToRemove);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addNewCustomer(UserSession session, FrontController fc, Customer customer) {
		try {
			return ((FrontControllerImpl)fc).model.addNewCustomer(session, customer);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addToCartByItemId(UserSession session, FrontController fc, int itemId) {
		Item itemToBeAddedToCart = null;
		try {
			itemToBeAddedToCart = ((FrontControllerImpl)fc).model.addToCartByItemId(session, itemId);
			if (itemToBeAddedToCart != null){
				session.getCart().addItemToCart(itemToBeAddedToCart);
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public void clearItemsInCart(UserSession session, FrontController fc) {
		try {
			((FrontControllerImpl)fc).model.clearItemsInCart(session);
			session.clearCart();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean registerNewCustomer(FrontController fController, Customer customer) {
		try {
			return ((FrontControllerImpl)fController).model.addNewCustomer(customer);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
