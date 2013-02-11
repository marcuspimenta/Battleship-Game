package com.battleship.manager;

import com.battleship.business.Action;


/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 16:28:55 24/01/2013
 */
public interface SocketCallback {

	void onSocketReceiverMsg(String msg);
	
	void onPrintMsgConsole(String msg);
	
	void onAction(Action action);
}