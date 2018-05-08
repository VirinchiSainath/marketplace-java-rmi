// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.view;

/*
 * when you are trying to logging
 * you are going to logging in
 */

import java.util.Scanner;

import com.marketplace.controller.FrontController;
import com.marketplace.entity.UserSession;

public class LoginView{
	

	public void show(FrontController fc) {
		
		System.out.println("Your have connected to the server");
		System.out.println("Welcome to MarketPlace");
		
		UserSession session = new UserSession();
		
		System.out.println("Enter login credentials");
		
		System.out.print("Enter your username : ");
		Scanner userInput = new Scanner(System.in);
		String username = userInput.nextLine();
		
		System.out.print("Enter your password : ");
		userInput = new Scanner(System.in);
		String password = userInput.nextLine();
		
		System.out.print("Enter your userType (customer or admin) : ");
		userInput = new Scanner(System.in);
		String userType = userInput.nextLine();
				
		fc.authenticateUser(username, password, userType);
	}

}
