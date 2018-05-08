// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

package com.marketplace.model;

/*
 * This is annotation class which imposes
 * requires role on methods
 */

// Ryan: Do you really need everything included in this package?
// Fixed: I think you are taking about the purposes of Server and ModelImpl, as others all are required
// I need not put two different files Server and ModelImpl. However, I wanted to distribute the methods amoung the two files
// I wanted Server to be the Server which is binded and ModelImpl to have all the methods such as AddToCart, PurchaseToCart.
		
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
	
	public String value();

}