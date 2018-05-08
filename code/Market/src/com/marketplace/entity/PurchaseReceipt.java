// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * Purpose: to save receipt details including items with their status and total price
 * itemStatus has item and its status. 
 * if status is true --> success
 * false --> failed.
 * On sending the recipt object, the front end will interpret this object to show in a
 * formatted way.
 */
public class PurchaseReceipt implements Serializable{

	private Map<Item,Boolean> itemStatuses;
	private int billedPrice;	
	
	public PurchaseReceipt(Map<Item, Boolean> itemStatuses, int billedPrice) {
		super();
		this.itemStatuses = itemStatuses;
		this.billedPrice = billedPrice;
	}

	public PurchaseReceipt() {
		super();
		this.itemStatuses = new HashMap<Item, Boolean>();
	}

	public void printReceipt() {
		System.out.println("Your receipt: ");
		for(Map.Entry<Item,Boolean> entry : itemStatuses.entrySet()){
			Item item = entry.getKey();
			String message = "";
			if (entry.getValue()){
				message = "Successful";
			}
			else {
				message = "Failed";
			}
			System.out.println("Item: "+item.getDescription()+" - "+message);
		}
		
		System.out.println("Price Billed = "+billedPrice);
	}

	public Map<Item, Boolean> getItemStatuses() {
		return itemStatuses;
	}

	public void setItemStatuses(Map<Item, Boolean> itemStatuses) {
		this.itemStatuses = itemStatuses;
	}

	public int getBilledPrice() {
		return billedPrice;
	}

	public void setBilledPrice(int billedPrice) {
		this.billedPrice = billedPrice;
	}

	@Override
	public String toString() {
		return "PurchaseReceipt [itemStatuses=" + itemStatuses + ", billedPrice=" + billedPrice + "]";
	}	
	
}
