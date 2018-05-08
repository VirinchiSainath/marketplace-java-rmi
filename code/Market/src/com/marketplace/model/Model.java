// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.model;

import java.util.List;

import com.marketplace.entity.Admin;
import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.marketplace.entity.PurchaseReceipt;
import com.marketplace.entity.UserSession;
import com.marketplace.service.ItemService;

/**
 * This interface serves as the proxy between the Model and Controller. 
 * The ModelImpl must implement these methods.
 */
public interface Model extends java.rmi.Remote {
	
	// Record number of visits
	public int getVisitorNumber() throws java.rmi.RemoteException;
	
	//connect and print message that a connection has been made
	public void connect() throws java.rmi.RemoteException;

	@RequiresRole("customer")
	public PurchaseReceipt purchaseItemsInCart(UserSession session) throws java.rmi.RemoteException;

	public List<Item> getAllItems() throws java.rmi.RemoteException;
	
	//No need of checking requires Role as it is taken care of in the front controller
	public UserSession authenticateCustomer(String username, String password) throws java.rmi.RemoteException;
	
	//No need of checking requires Role as it is taken care of in the front controller
	public UserSession authenticateAdmin(String username, String password) throws java.rmi.RemoteException;

	public ItemService getProductService() throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public void checkIfUserHasAccess(UserSession session) throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public Item addItemToInventory(UserSession session, Item item) throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public void addNewAdmin(UserSession session, Admin newAdmin) throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public Item updateItemById(UserSession session, Item item) throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public boolean removeItemById(UserSession session, int idToRemove) throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public boolean removeCustomerByUserName(UserSession session, String customerUserNameToRemove) 
			throws java.rmi.RemoteException;

	@RequiresRole("admin")
	public boolean addNewCustomer(UserSession session, Customer customer) throws java.rmi.RemoteException;

	@RequiresRole("customer")
	public Item addToCartByItemId(UserSession session, int itemId) throws java.rmi.RemoteException;

	@RequiresRole("customer")
	public void clearItemsInCart(UserSession session) throws java.rmi.RemoteException;

	@RequiresRole("customer")
	public List<Item> checkAvailabiltyOfCartItems(UserSession session) throws java.rmi.RemoteException;

	public boolean addNewCustomer(Customer customer) throws java.rmi.RemoteException;
	
}
