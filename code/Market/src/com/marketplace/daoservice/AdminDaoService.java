// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.daoservice;

import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.marketplace.entity.Admin;
import com.marketplace.entity.Item;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class AdminDaoService extends BaseDaoService{

	public int validateAdminLogin(String username, String password) {
		
		int id = 0;
		try {
		ps = (PreparedStatement) conn.prepareStatement("select admin_id from admin where username=? and password=?");
		ps.setString(1, username);
		ps.setString(2, password);
		rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		return id;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	public Item saveItemToInventory(Item item) {
		
		// to insert new entry into item table first get the sequentially next item_id 
		// insert into this item into item table on the obtained item id
		
		Lock lock = new ReentrantLock(true);
		lock.lock();
		int maxId = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement("select max(id) from item");
			rs = ps.executeQuery();
			if(rs.next()){
				maxId = rs.getInt(1);
			}		
			
			ps = (PreparedStatement) conn.prepareStatement("insert into item values (?,?,?,?,?)");
			ps.setInt(1, maxId++);
			ps.setString(2, item.getType());
			ps.setString(3, item.getDescription());
			ps.setInt(4, item.getPrice());
			ps.setInt(5, item.getQuantity());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return item;
	}

	public Admin addNewAdmin(Admin newAdmin) {
		
		// get the sequentially next admin_id
		// insert admin in that admin_id
		
		int maxId = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement("select max(admin_id) from admin");
			rs = ps.executeQuery();
			if(rs.next()){
				maxId  = rs.getInt(1);
			}		
			
			ps = (PreparedStatement) conn.prepareStatement("insert into admin values (?,?,?,?,?)");
			ps.setInt(1, ++maxId);
			ps.setString(2, newAdmin.getFirstName());
			ps.setString(3, newAdmin.getLastName());
			ps.setString(4, newAdmin.getUsername());
			ps.setString(5, newAdmin.getPassword());
			ps.execute();
		} catch (MySQLIntegrityConstraintViolationException e1){
			System.out.println("Contraint error. Username already exists");
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		newAdmin.setAdmin_id(maxId);
		return newAdmin;
	}

}
