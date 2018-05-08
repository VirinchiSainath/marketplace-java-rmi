// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.daoservice;

import java.sql.ResultSet;

import com.marketplace.db.DatabaseConnector;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BaseDaoService {
	
	/*
	 * common variables placed in this class and other DaoService layers extend from this class.
	 */
	
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	
	public BaseDaoService (){
		conn = DatabaseConnector.getDatabaseConnection().getConnection();
	}

}
