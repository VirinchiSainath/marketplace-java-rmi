// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.model;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.marketplace.entity.Admin;
import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.marketplace.entity.PurchaseReceipt;
import com.marketplace.entity.UserSession;
import com.marketplace.service.AdminService;
import com.marketplace.service.CustomerService;
import com.marketplace.service.ItemService;

/**
 * ModelImpl - Must implement any and all methods found in the Model
 * interface. The variable 'name' must include the location where the
 * ModelImple is going to be registered with RMI to run.
 */
public class ModelImpl extends UnicastRemoteObject implements Model {
	
	private int id = 0;
	private ItemService itemService;
	private CustomerService customerService;
	private AdminService adminService;
	
	public ModelImpl() throws RemoteException {
		super();
		itemService = new ItemService();
		customerService = new CustomerService();
		adminService = new AdminService();
	}

	@Override
	public List<Item> getAllItems() {
		return itemService.getItemList();
	}

	@Override
	public ItemService getProductService() {
		return itemService;
	}
	
	@Override
	public void connect() throws RemoteException {
		System.out.println("New Connection to Server");
	}

	@Override
	public synchronized int getVisitorNumber() throws RemoteException {
		id++; 
		return id;
	}	
	/*
	 * Purchases the items in cart
	 * Removes the purchased items by removing those items from inventory (decrementing product quantity)
	 */
	@Override
	public PurchaseReceipt purchaseItemsInCart(UserSession session){
		return customerService.purchaseItemsInCart(session);
	}

	@Override
	public void checkIfUserHasAccess(UserSession session) {
	}

	@Override
	public UserSession authenticateCustomer(String username, String password) throws RemoteException {
		System.out.println("Requesting Authentiacation for "+username);
		return customerService.validateCustomerLogin(username, password);		
	}

	@Override
	public UserSession authenticateAdmin(String username, String password) throws RemoteException {
		System.out.println("Requesting Authentiacation for "+username);
		return adminService.validateAdminLogin(username, password);
		
	}

	@Override
	public Item addItemToInventory(UserSession session, Item item) {

		return itemService.addItemToInventory(item);
	}

	@Override
	public void addNewAdmin(UserSession session, Admin newAdmin) {
		adminService.addNewAdmin(newAdmin);
	}

	@Override
	public Item updateItemById(UserSession session, Item item) {
		return itemService.updateItemById(item);
		
	}

	@Override
	public boolean removeItemById(UserSession session, int idToRemove) throws RemoteException {
		return itemService.removeItemById(idToRemove);
	}

	@Override
	public boolean removeCustomerByUserName(UserSession session, String customerUserNameToRemove)
			throws RemoteException {
		return customerService.removeCustomerByUserName(customerUserNameToRemove);
	}

	@Override
	public boolean addNewCustomer(UserSession session, Customer customer) {
		return customerService.addNewCustomer(customer);
	}

	@Override
	public Item addToCartByItemId(UserSession session, int itemId) {
		return customerService.addToCart(session, itemId);
	}

	@Override
	public void clearItemsInCart(UserSession session) throws RemoteException {
		customerService.clearItemsInCart(session);
	}

	@Override
	public List<Item> checkAvailabiltyOfCartItems(UserSession session) throws RemoteException {
		return customerService.checkAvailabilityOfCartItems(session);
	}

	@Override
	public boolean addNewCustomer(Customer customer) {
		return customerService.addNewCustomer(customer);
	}

}


