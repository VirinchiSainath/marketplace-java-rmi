// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.daoservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.marketplace.entity.Item;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class ItemDaoService extends BaseDaoService{

	public List<Item> getItemList() {
		List<Item> itemList = new ArrayList<>();
		try {
			ps = (PreparedStatement) conn.prepareStatement("select * from item");
			rs = ps.executeQuery();
			while(rs.next()){
				itemList.add(
						new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}

	public Item addItemToInventory(Item item) {
		
		// first get max(id) and then insert there
		
		int maxId = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM item ORDER BY item_id DESC LIMIT 0, 1");
			rs = ps.executeQuery();
			if(rs.next()){
				maxId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("new Item's Id is "+maxId);
		
		Lock lock = new ReentrantLock(true);
		lock.lock();
		try {
			ps = (PreparedStatement) conn.prepareStatement(
					"insert into item values (?,?,?,?,?)");
			ps.setInt(1, ++maxId);
			ps.setString(2, item.getType());
			ps.setString(3, item.getDescription());
			ps.setInt(4, item.getPrice());
			ps.setInt(5, item.getQuantity());
			System.out.println(ps.asSql());
			ps.execute();
		} catch (MySQLIntegrityConstraintViolationException e1) {
			System.out.println("Contraint error. A product with similar description already exists");
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		item.setId(maxId);
		return item;
	}

	public Item updateItemById(Item item) {
		
		// first check if such an item 
		// if such an item doesn't exist then update is blocked.
		// admin has to add this item as new item (not update)
		
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM item where item_id="+item.getId());
			rs = ps.executeQuery();
			if(!rs.next()){
				// no such item exists
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Lock lock = new ReentrantLock(true);
		lock.lock();
		try {
			String q = "update item set type=?, description=?, price=?, quantity=? where item_id="+item.getId();
			ps = (PreparedStatement) conn.prepareStatement(q);
			ps.setString(1, item.getType());
			ps.setString(2, item.getDescription());
			ps.setInt(3, item.getPrice());
			ps.setInt(4, item.getQuantity());
			ps.executeUpdate();
		} catch (MySQLIntegrityConstraintViolationException e1) {
			System.out.println("Contraint error. A product with similar description already exists");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		return item;
	}

	public boolean removeItemById(int idToRemove) {
		
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM item where item_id="+idToRemove);
			rs = ps.executeQuery();
			if(!rs.next()){
				// no such item exists
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		Lock lock = new ReentrantLock(true);
		lock.lock();
		try {
			String q = "delete from item where item_id="+idToRemove;
			ps = (PreparedStatement) conn.prepareStatement(q);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return true;
	}

	
	
}
