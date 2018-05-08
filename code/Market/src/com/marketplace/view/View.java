// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.view;

import java.util.Scanner;

import com.marketplace.controller.FrontController;
import com.marketplace.controller.FrontControllerImpl;
import com.marketplace.entity.Customer;

//Ryan: Here you are violating the principle of separation of concerns
// in that you are mixing View data with the application framework. Instead
// you should isolate these two components.

//Fixed: I moved the data in View to FrontControllerImpl

public class View {
	
	public static void main(String []args){
		System.setSecurityManager(new SecurityManager());
		try{
			
			//connection to Front Controller
			FrontController fController = new FrontControllerImpl();
			fController.connectToModel();
			
			boolean isExitRequested = false;
			while (!isExitRequested){
				System.out.println("----------------------------------------------------------");
				System.out.println("1. customer registration ");
				System.out.println("2. Customer/Admin Login ");
				System.out.println("3. Exit ");
				System.out.println("----------------------------------------------------------");
				
				Scanner sc = new Scanner(System.in);
				String input = sc.nextLine();
				
				if(input.equals("1")){

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
					
					boolean isRegistrationSuccessful = fController.registerNewCustomer(fController,
							new Customer(0, customerFirstName, customerLastName, customerUsername, customerPassword)
							);
					
					if (!isRegistrationSuccessful){
						System.out.println("Registring customer failed. Try with a different username");
					}
				}
				else if (input.equals("2")){
					new LoginView().show(fController);
				}
				else if (input.equals("3")){
					isExitRequested = true;
				}
			}
			
			
		} catch(Exception e){
			System.out.println("View Exception: "+e.getMessage());
			e.printStackTrace();
		}
	}


}
