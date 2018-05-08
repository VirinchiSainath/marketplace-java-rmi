// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri


/*
Home page for the customer once he logins in
*/

package com.marketplace.view;

import java.util.List;
import java.util.Scanner;

/*
 * This is where customer comes
 * once he logs in
 */

import com.marketplace.controller.FrontController;
import com.marketplace.entity.Item;
import com.marketplace.entity.PurchaseReceipt;
import com.marketplace.entity.UserSession;

public class CustomerHomeView implements HomeViewInterface{
	
	public void showFunctions(UserSession session, FrontController fc) {
		boolean isExitRequested = false;
		while(!isExitRequested){
			
			System.out.println("---------------------MENU---------------------");
			System.out.print("1. Browse  ");
			System.out.print("2. Add Item To Cart  ");
			System.out.print("3. View Cart  ");
			System.out.println("4. Clear Items in Cart");
			System.out.print("5. Purchase Items in Cart  ");
			System.out.print("6. Exit\n");
			System.out.println("----------------------------------------------");
			
			Scanner sc = new Scanner(System.in);
			String input = sc.hasNextLine() ? sc.nextLine() : "";
			
			if(input.equals("1")){
				System.out.println("Following are the products : ");
				fc.tellModelToShowProducts(session,fc);
			}
			else if(input.equals("2")){
				System.out.println("Following are the products : ");
				fc.tellModelToShowProducts(session,fc);
				
				System.out.println("Enter the item id you want to add to cart.");
				Scanner addToCartSc = new Scanner(System.in);
				int itemId = addToCartSc.nextInt();
				
				boolean isAddToCartSuccessful = fc.addToCartByItemId(session, fc, itemId);
				if(!isAddToCartSuccessful){
					System.out.println("Add to Cart - Not Successful ");
				}
				else {
					System.out.println("Successfully added to Cart");
				}
			}
			else if (input.equals("3")){
				
				List<Item> itemList = session.getCart().getItemList();
				
				if (itemList.isEmpty()){
					System.out.println("No items in cart");
				}
				
				for(Item i : itemList){
					System.out.println(i.toString());
				}
				System.out.println("Total Price of Cart = "+session.getCart().getPrice());
				
			}
			else if (input.equalsIgnoreCase("4")){
				// clear all items in cart
				fc.clearItemsInCart(session, fc);
				System.out.println("Cleared Cart");
			}
			else if (input.equals("5")){
				PurchaseReceipt receipt = fc.purchaseItemsInCart(session,fc);
				receipt.printReceipt();
			}
			else if (input.equals("6")){
				isExitRequested = true;
			}
		}
		System.out.println("Thank You, Exiting application...");
			
	}
	
	@Override
	public
	void show(UserSession session, FrontController fc){
		System.out.println("Hi Customer, You have logged in");
		showFunctions(session,fc);
	}

}
