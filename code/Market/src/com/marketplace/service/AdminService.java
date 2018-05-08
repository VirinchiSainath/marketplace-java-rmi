// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri
/*
All the methods/functionality of the Admin will be placed here.
This acts as the receiver for the commands of the admin.
*/
package com.marketplace.service;

import com.marketplace.daoservice.AdminDaoService;
import com.marketplace.daoservice.ItemDaoService;
import com.marketplace.entity.Admin;
import com.marketplace.entity.Item;
import com.marketplace.entity.UserSession;

public class AdminService{
	
	private AdminDaoService adminDaoService;
	
	public AdminService(){
		adminDaoService = new AdminDaoService();
	}

	public UserSession validateAdminLogin(String username, String password){
		
		int id = adminDaoService.validateAdminLogin(username, password);
		return new UserSession(username, id, "admin", null);
		
	}
	
	public Item saveItemToInventory(Item item){
		
		return adminDaoService.saveItemToInventory(item);
		
	}

	public Admin addNewAdmin(Admin newAdmin) {
		return adminDaoService.addNewAdmin(newAdmin);
		
	}

		
}
