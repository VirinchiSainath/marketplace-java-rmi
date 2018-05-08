// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.daoservice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.marketplace.entity.Customer;
import com.marketplace.entity.Item;
import com.mysql.jdbc.PreparedStatement;

public class CustomerDaoService extends BaseDaoService{

	public int validateCustomerLogin(String username, String password){
		
		int id = 0;
		try {
		ps = (PreparedStatement) conn.prepareStatement("select c.customer_id from customer c where c.username=? and c.password=?");
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

	public List<Item> getItemsInCartById(int customerId){
		
		// first get all (customer_id, item_id) pairs where customer_id = customerId
		// then for all these itemIds get Item save as item object list and return
		
		List<Item> itemList = new ArrayList<>();
		List<Integer> itemIdList = new ArrayList<>();
		try {
			ps = (PreparedStatement) conn.prepareStatement("select item_id from customer_item where customer_id="+ customerId);
			rs = ps.executeQuery();
			while(rs.next()){
				itemIdList.add(rs.getInt(1));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
		
		for(int i : itemIdList){
			try {
				ps = (PreparedStatement) conn.prepareStatement("select * from item where item_id="+i);
				rs = ps.executeQuery();
				if (rs.next()){
					itemList.add(
							new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5))
							);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemList;
		
	}

	public boolean removeCustomerByUserName(String customerUserNameToRemove) {
		Lock lock = new ReentrantLock(true);
		lock.lock();
		try {
			ps = (PreparedStatement) conn.prepareStatement("delete from customer where username=?");
			ps.setString(1, customerUserNameToRemove);
			ps.execute();
		} catch (SQLIntegrityConstraintViolationException e1) {
			System.out.println("username doesn't exist");
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		return true;
		
	}

	public boolean addNewCustomer(Customer customer) {
		
		// get next sequential id from customer table
		// and insert the new customer in that id.
		
		int maxId = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement("select max(customer_id) from customer");
			rs = ps.executeQuery();
			if(rs.next()){
				maxId = rs.getInt(1);
			}
			else {
				return false;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			String query = "insert into customer values (?,?,?,?,?)";
			ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, ++maxId);
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getUsername());
			ps.setString(5, customer.getPassword());
			ps.execute();
		} catch (SQLIntegrityConstraintViolationException e1) {
			System.out.println("customer with same username exists");
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Item addToCart(int customerId, int itemId) {
		
		//check if enough quantity available
		
		Item itemInDb = null;
		try {
			String query = "select * from item where item_id="+itemId;
			ps = (PreparedStatement) conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				itemInDb = new Item(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//if the item or item's quantity not enough return null
		
		if (itemInDb != null && itemInDb.getQuantity() > 0){
			//add customer_id and item_id to customer_item
			int maxCustomerItemId = 0;
			try {
				String query = "select max(id) from customer_item";
				ps = (PreparedStatement) conn.prepareStatement(query);
				rs = ps.executeQuery();
				if(rs.next()){
					maxCustomerItemId = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//when there is enough quantity and such an item exists only then insert into 
			// customer_id - item_id table
			
			try {
				String query = "insert into customer_item values (?,?,?)";
				ps = (PreparedStatement) conn.prepareStatement(query);
				ps.setInt(1, ++maxCustomerItemId);
				ps.setInt(2, customerId);
				ps.setInt(3, itemId);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return itemInDb;
		}
		return null;
	}

	public void clearItemsInCartOfCustomer(int customerId) {
		
		try {
			String query = "delete from customer_item where customer_id="+customerId;
			ps = (PreparedStatement) conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean purchaseItemForCustomer(int customerId, int itemId) {
		Lock lock = new ReentrantLock(true);
		lock.lock();
		// see if the item is available
		try {
			String query = "select quantity from item where item_id="+itemId;
			ps = (PreparedStatement) conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				//item not available
				if (rs.getInt(1) == 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		lock.lock();
		// if available then purchase (decrement)
		try {
			String query = "update item set quantity=quantity-1 where quantity>0 and item_id="+itemId;
			ps = (PreparedStatement) conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return true;
	}

	public boolean checkAvailabilityOf(int itemId) {

		try {
			String query = "select * from item where item_id="+itemId;
			ps = (PreparedStatement) conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
