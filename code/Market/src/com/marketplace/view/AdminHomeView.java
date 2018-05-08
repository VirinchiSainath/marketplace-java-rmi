// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri


/*
Home page for the admin once he logins in
*/
package com.marketplace.view;

import java.util.Scanner;

/*
 * This is view for Admin home.
 * Once the admin logs in he comes here
 */

import com.marketplace.controller.FrontController;
import com.marketplace.entity.Admin;
import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.marketplace.entity.UserSession;

public class AdminHomeView implements HomeViewInterface{
	// Ryan: Do these need scope here?
	// Fixed: added private
		
	public void showFunctions(UserSession session, FrontController fc) {
		boolean isExitRequested = false;
		while(!isExitRequested){
			
			System.out.println("---------------------MENU---------------------");
			System.out.print("1. Browse  ");
			System.out.print("2. Add another Admin  ");
			System.out.println("3. Add Item to Inventory");
			System.out.print("4. Update Item in Inventory  ");
			System.out.println("5. Remove Item in Inventory  ");
			System.out.print("6. Remove a customer account  ");
			System.out.println("7. Add a customer account  ");
			System.out.print("8. Exit\n");
			System.out.println("----------------------------------------------");
			
			Scanner sc = new Scanner(System.in);
			String input = sc.hasNextLine() ? sc.nextLine() : "";
			
			if(input.equals("1")){
				System.out.println("Following are the products : ");
				fc.tellModelToShowProducts(session,fc);
			}
			else if(input.equals("2")){
				System.out.println("Enter admin firstname :");
				Scanner adminSC = new Scanner(System.in);
				String adminFirstName = adminSC.nextLine();
				
				System.out.println("Enter admin lastname : ");
				adminSC = new Scanner(System.in);
				String adminLastName = adminSC.nextLine();
				
				System.out.println("Enter admin username : ");
				adminSC = new Scanner(System.in);
				String adminUsername = adminSC.nextLine();
				
				System.out.println("Enter admin password : ");
				adminSC = new Scanner(System.in);
				String adminPassword = adminSC.nextLine();
				
				fc.addNewAdmin(session, fc, new Admin(0,adminFirstName,adminLastName, adminUsername, adminPassword));
				
			}
			else if (input.equals("3")){
				
				if (session.getUserType().equals("admin")){
					System.out.println("Enter product type :");
					Scanner pSc = new Scanner(System.in);
					String productType = pSc.nextLine();
					
					System.out.println("Enter product description : ");
					pSc = new Scanner(System.in);
					String productDescription = pSc.nextLine();
					
					System.out.println("Enter product price : ");
					pSc = new Scanner(System.in);
					int productPrice = pSc.nextInt();
					
					System.out.println("Enter product quantity : ");
					pSc = new Scanner(System.in);
					int productQuantity = pSc.nextInt();						
				
					fc.addItemToInventory(session, fc, 
							new Item(0, productType, productDescription, productPrice, productQuantity)
							);
				}
				else {
					System.out.println("you don't have access to this feature");
				}
				
				
			}
			else if (input.equals("4")){
				System.out.println("Following are the products in the inventory: ");
				fc.tellModelToShowProducts(session,fc);
				System.out.println("Enter the id of the item you want to update");
				Scanner updateItemSc = new Scanner(System.in);
				int idToUpdate = updateItemSc.nextInt();
				
				System.out.println("Enter the updated item's type");
				updateItemSc = new Scanner(System.in);
				String updatedType = updateItemSc.nextLine();		
				
				System.out.println("Enter the updated item's description");
				updateItemSc = new Scanner(System.in);
				String updatedDesc = updateItemSc.nextLine();
				
				System.out.println("Enter the updated item's price");
				updateItemSc = new Scanner(System.in);
				int updatedPrice = updateItemSc.nextInt();
				
				System.out.println("Enter the updated item's quantity");
				updateItemSc = new Scanner(System.in);
				int updatedQuantity = updateItemSc.nextInt();
				
				Item retrivedItem = fc.updateItemById(session, fc, 
						new Item(idToUpdate, updatedType, updatedDesc, updatedPrice, updatedQuantity));
				
				if(retrivedItem == null){
					System.out.println("Item with id "+idToUpdate+" doesn't exist");
				}else {System.out.println("Product successfully added to Inventory");}
						
			}
			else if (input.equals("5")){
				// remove an item in inventory
				System.out.println("Following are the products in the inventory: ");
				fc.tellModelToShowProducts(session,fc);
				System.out.println("Enter the id of the item you want to remove");
				Scanner removeItemSc = new Scanner(System.in);
				int idToRemove =removeItemSc.nextInt();
				
				boolean isRemoveSuccessful = 
						fc.removeItemById(session, fc, idToRemove);
				
				if (!isRemoveSuccessful){
					System.out.println("Id doesn't exist. Remove not successful");
				}else {System.out.println("Successfully removed item");}
			}
			else if (input.equals("6")){
				// remove a customer account
				System.out.println("Enter the username of the customer to delete");
				Scanner removeCustomerSc = new Scanner(System.in);
				String customerUserNameToRemove =removeCustomerSc.nextLine();
				
				boolean isRemoveSuccessful = 
						fc.removeCustomerByUserName(session, fc, customerUserNameToRemove);
				
				if (!isRemoveSuccessful){
					System.out.println("Id doesn't exist. Remove not successful");
				}else {System.out.println("successfully removed customer");}
			}
			else if (input.equals("7")){
				// add new customer
				System.out.println("Enter customer firstname :");
				Scanner customerSc = new Scanner(System.in);
				String customerFirstName = customerSc.nextLine();
				
				System.out.println("Enter customer lastname : ");
				customerSc = new Scanner(System.in);
				String customerLastName = customerSc.nextLine();
				
				System.out.println("Enter customer username : ");
				customerSc = new Scanner(System.in);
				String customerUsername = customerSc.nextLine();
				
				System.out.println("Enter customer password : ");
				customerSc = new Scanner(System.in);
				String customerPassword = customerSc.nextLine();
				
				boolean isCustomerAdded = fc.addNewCustomer(session, fc, 
						new Customer(0, customerFirstName, customerLastName, customerUsername, customerPassword));
				
				if(!isCustomerAdded){
					System.out.println("customer account not created");
				}
				System.out.println("customer account created");
			}
			else if (input.equalsIgnoreCase("8")){
				isExitRequested = true;
			}
		}
		System.out.println("Thank You, Exiting application...");
			
	}
	
	@Override
	public void show(UserSession session, FrontController fc) {
		System.out.println("Hello Admin, You have logged in.");		
		showFunctions(session, fc);
		
	}
	
	


}
