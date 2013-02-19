package com.battleship.business;


/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 08:59:34 11/02/2013
 */
public interface ActionsCallback {
	
	void onActionSelected(Action msgReceiver);
	
	void onSendMessageChat(String msg);
	
	void onSendCoordinateSquare(int row, int column);
	
}