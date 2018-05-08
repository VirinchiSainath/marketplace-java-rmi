// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
// vnalluri

/*
Command Invoker 
First it has to be set a command
then once you have set you can simply call invoke command
you can also invoke a series of commands
*/

package com.marketplace.controller;

/*
 * to invoke command
 */
import java.util.ArrayList;
import java.util.List;

import com.marketplace.commands.Command;

public class CommandInvoker {
	// Ryan: Do these need scope here?
	// Fixed: changed to private
	
	private List<Command> commandList = new ArrayList<>();
	
	public void setCommand(Command c){
		commandList.add(c);
	}
	
	public void invokeCommand(){
		for(Command c : commandList){
			c.execute();
		}
	}

}
