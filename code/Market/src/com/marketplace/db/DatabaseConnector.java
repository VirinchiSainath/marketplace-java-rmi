// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.db;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;


public class DatabaseConnector {
	
	private Connection conn = null;
	private static DatabaseConnector db;
	
	private DatabaseConnector() {
		//Default Constructor
		String hostname = "localhost:3306";
		String dbName = "vnalluri_db";
		String url = "jdbc:mysql://" + hostname + "/" + dbName;
		String username = "vnalluri";
		String password = "vnalluri";

		System.out.println("Connecting database...");

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = (Connection) DriverManager.getConnection(url, username, password);
				
		    System.out.println("Database connected!");
		} catch (Exception e) {
			System.out.println("Cannot connect to database !");
		    e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return conn;
	}

	public static DatabaseConnector getDatabaseConnection(){
		/*
		 * scope - locking
		 */
		if(db == null){
			synchronized (DatabaseConnector.class){
				if (db == null) {
					db = new DatabaseConnector();
				}
			}			
		}
		return db;
		
	}
}