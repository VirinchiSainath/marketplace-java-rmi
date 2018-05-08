// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.entity;

import java.io.Serializable;

public class Item implements Serializable{
	
	private int id;
	private String type;
	private String description;
	private int price;
	private int quantity;
	
	public Item(int id, String type, String description, int price, int quantity) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public Item() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", type=" + type + ", description=" + description + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
}
