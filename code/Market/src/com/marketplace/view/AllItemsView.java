// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.view;

/*
 * Browse Product implementation.
 * This is the view showing all the products
 */

import java.util.List;

import com.marketplace.controller.FrontController;
import com.marketplace.entity.Item;
import com.marketplace.entity.UserSession;

public class AllItemsView implements ViewInterface{

	public void show(UserSession session, FrontController fc, List<Item> itemList) {
		
		for(Item i : itemList){
			System.out.println(i.toString());
		}
		
	}

	@Override
	public void show(UserSession session, FrontController fc) {
		// TODO Auto-generated method stub
		
	}

}
