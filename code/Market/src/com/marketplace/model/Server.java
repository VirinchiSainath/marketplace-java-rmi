// Honor Pledge:

package com.marketplace.model;

/*
 * This is where binding is done
 */

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server{
	

	public static void main(String args[]) {
		
		
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		
		try {
			System.out.println("Creating a Server!");
			
			Model model = (Model) Proxy.newProxyInstance(Model.class.getClassLoader(),new Class<?>[] {Model.class},
	                new AuthorizationInvocationHandler(new ModelImpl()));
	     
			
			// Location of Server
            String name = "//in-csci-rrpc01.cs.iupui.edu:2323/Server";
			//String name = "//localhost/Server";
			
		/*	// Create a new instance of a Server.
			ModelImpl marketPlace = new ModelImpl();*/
			
			System.out.println("Server: binding it to name: " + name);
			
			// Binds the Server to the RMI Service.
			Naming.rebind(name, model);
			
			System.out.println("Server Ready!");
			
		} catch (Throwable e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
