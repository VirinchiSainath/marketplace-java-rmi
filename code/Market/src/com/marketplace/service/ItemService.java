// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.service;

import java.util.List;

import com.marketplace.daoservice.ItemDaoService;
import com.marketplace.entity.Item;


/*
 * This class acts like a database which has inventory of products (productList)
 * also the methods on these products/inventory
 */

public class ItemService {
	
	
	private ItemDaoService itemDaoService;
		
	public ItemService(){
		itemDaoService = new ItemDaoService();
	}

	public List<Item> getItemList() {
		return itemDaoService.getItemList();
	}

	public Item addItemToInventory(Item item) {
		return itemDaoService.addItemToInventory(item);
	}

	public Item updateItemById(Item item) {
		return itemDaoService.updateItemById(item);
	}

	public boolean removeItemById(int idToRemove) {
		
		return itemDaoService.removeItemById(idToRemove);
	}

}
