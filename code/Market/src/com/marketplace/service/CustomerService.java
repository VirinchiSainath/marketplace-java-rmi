// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

/*
All the methods/functionality of the customer will be placed here.
This acts as the receiver for the commands of the customer.
*/

package com.marketplace.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.marketplace.daoservice.CustomerDaoService;
import com.marketplace.entity.Cart;
import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.marketplace.entity.PurchaseReceipt;
import com.marketplace.entity.UserSession;

public class CustomerService{
	
	private CustomerDaoService customerDaoService;;
	
	public CustomerService(){
		customerDaoService= new CustomerDaoService();
	}
	
	public UserSession validateCustomerLogin(String username, String password) {
		
		/*
		 * if you get back a customerId then such an id exists in database
		 * if that is the case then insert in a sesssion and return the session otherwise return null
		 */
		
		int customerId = customerDaoService.validateCustomerLogin(username, password);
		if (customerId != 0){
			System.out.println("Customer Validated ");
			List<Item> cartItems = customerDaoService.getItemsInCartById(customerId);
			
			return new UserSession(username, customerId, "customer", new Cart(cartItems));
		}
		
		System.out.println("invalid customer login");
		return null;		
	}

	public boolean removeCustomerByUserName(String customerUserNameToRemove) {
		return customerDaoService.removeCustomerByUserName(customerUserNameToRemove);
	}

	public boolean addNewCustomer(Customer customer) {
		return customerDaoService.addNewCustomer(customer);
	}

	public Item addToCart(UserSession session, int itemId) {
		
		/*
		 * On getting item, which is successfully inserted into database
		 * then return so that the item can be added to cart in previous layers
		 */
		Item itemToBeAddedToCart =  customerDaoService.addToCart(session.getId(), itemId);
		if(itemToBeAddedToCart != null){
			session.getCart().addItemToCart(itemToBeAddedToCart);
			return itemToBeAddedToCart;
		}
		return null;
	}

	public void clearItemsInCart(UserSession session) {
		customerDaoService.clearItemsInCartOfCustomer(session.getId());
		session.clearCart();
		
	}

	public PurchaseReceipt purchaseItemsInCart(UserSession session) {
		
		/*
		 * purchase items one by one and add the item and status to receiptMap.
		 * since purchase of one item doesn't depend on another item. they can be multi-threaded.
		 * this is done using callback and the actual calling of the dao service is done in call
		 * method. The return object which is future object of type is boolean.
		 */
		
		Map<Item,Boolean> receiptMap = new HashMap<Item, Boolean>();
		int price = 0;
		List<Item> itemListInCart = session.getCart().getItemList();
		int customerId = session.getId();
		ExecutorService executorService = Executors.newFixedThreadPool(itemListInCart.size());
		for (Item i : itemListInCart){
			//Callable thread
			Callable<Boolean> callableTask = new Callable<Boolean>() {
	
				@Override
				public Boolean call() throws Exception {
					return customerDaoService.purchaseItemForCustomer(customerId, i.getId());
				}
			};
			Future<Boolean> future = executorService.submit(callableTask);
			try {
				if (future.get()){ //try-catch blocks for future.get()
					receiptMap.put(i, true);
					price = price + i.getPrice();
				}
				else {
					receiptMap.put(i, false);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		
		/* 
		 * the commented code is the same implementation but without multithreading
		 * 
		 * for(Item i : itemListInCart){
			if (customerDaoService.purchaseItemForCustomer(customerId, i.getId())){
				receiptMap.put(i, true);
				price = price + i.getPrice();
			}
			else {
				receiptMap.put(i, false);
			}
		}*/
		customerDaoService.clearItemsInCartOfCustomer(customerId);
		
		return new PurchaseReceipt(receiptMap, price);
		
	}

	public List<Item> checkAvailabilityOfCartItems(UserSession session) {
		
		/*
		 * while checking if the current items is not available any more 
		 * then remove it. For this purpose itrator is used.
		 */
		List<Item> notAvailableItems = new ArrayList<>();
		Iterator itr = session.getCart().getItemList().iterator();
		while(itr.hasNext()){
			//check availability of this item
			Item currItem = (Item) itr.next();
			if ( !customerDaoService.checkAvailabilityOf(currItem.getId()) ){
				itr.remove();
				notAvailableItems.add(currItem);
			}
		}
		return notAvailableItems;
	}

}
