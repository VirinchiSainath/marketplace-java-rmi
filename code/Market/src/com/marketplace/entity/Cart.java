// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.entity;

import java.io.Serializable;
import java.util.ArrayList;

//Ryan: Always include useful comments in each file.
//Fixed

/*
 * this is pojo class for Cart
 */

import java.util.List;

public class Cart implements Serializable{

	private List<Item> itemList;
	private int price = 0;

	public Cart(List<Item> itemList) {
		super();
		this.itemList = itemList;
		for(Item i : this.itemList ){
			this.price += i.getPrice();
		}
	}

	public Cart() {
		this.itemList = new ArrayList<>();
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart [itemList=" + itemList + ", price=" + price + "]";
	}

	public void addItemToCart(Item itemToBeAddedToCart) {
		System.out.println("adding "+itemToBeAddedToCart.getDescription()+" to cart");
		itemList.add(itemToBeAddedToCart);
		this.price += itemToBeAddedToCart.getPrice();
	}

	public void clearItems() {
		this.itemList.clear();	
		this.price = 0;
	}
	
	
	
	
	
}
