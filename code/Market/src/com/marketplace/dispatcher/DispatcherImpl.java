// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri
/*
This class takes care of dispatching
*/
package com.marketplace.dispatcher;


/*
 * Its main function to create appropriate
 * views using factory
 */

import java.util.List;

import com.marketplace.controller.FrontController;
import com.marketplace.dispatcher.abstractFactory.AbstractFactory;
import com.marketplace.dispatcher.abstractFactory.FactoryCreator;
import com.marketplace.entity.Item;
import com.marketplace.entity.UserSession;
import com.marketplace.view.AllItemsView;
import com.marketplace.view.LoginView;
import com.marketplace.view.ViewInterface;


public class DispatcherImpl implements Dispatcher{
	
	private AbstractFactory absFactory;
	
	private ViewInterface view;
	
	private FactoryCreator factoryCreator;
	
	/*
	Depending upon the choice given by the ControllerImpl, 
	dispatcher creates the appropirate factory using which 
	it will create view whose show() method will be invoked.
	*/
	

	@Override
	public void dispatch(UserSession session, String viewChoice, FrontController fc) {
		
		if(viewChoice.equals("customerHomeView")){
			factoryCreator = new FactoryCreator();
			absFactory = factoryCreator.createFactory("home");
			view = absFactory.createView("customer");
			view.show(session,fc);
		}
		
		else if (viewChoice.equals("adminHomeView")){
			factoryCreator = new FactoryCreator();
			absFactory = factoryCreator.createFactory("home");
			view = absFactory.createView("admin");
			view.show(session,fc);
		}
		else if (viewChoice.equals("loginErrorView")){
			factoryCreator = new FactoryCreator();
			absFactory = factoryCreator.createFactory("Error");
			view = absFactory.createView("loginError");
			view.show(session,fc);
		}
		else if (viewChoice.equals("cartView")){
			factoryCreator = new FactoryCreator();
			absFactory = factoryCreator.createFactory("cart");
			view = absFactory.createView("cart");
			view.show(session,fc);
		}
		else{
			absFactory = new FactoryCreator().createFactory("PageNotFound");
			view = absFactory.createView("PageNotFound");
			view.show(session,fc);
		}
		
		
	}

	@Override
	public void dispatchLoginView(String viewChoice, FrontController fc) {
		if (viewChoice.equals("loginView")){
			new LoginView().show(fc);
		}
		
	}


	@Override
	public void dispatchItemsView(UserSession session, List<Item> itemList, FrontController fc) {
		factoryCreator = new FactoryCreator();
		new AllItemsView().show(session, fc, itemList);
	}


}
