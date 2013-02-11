package com.battleship.business;


/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 08:59:34 11/02/2013
 */
public interface ActionsCallback {
	
	void onActionSelected(Action action);
	
	void anSendMsg(String msg);
	
}