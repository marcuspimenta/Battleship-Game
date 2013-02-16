package com.battleship.protocol;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 18:20:30 15/02/2013
 */
public enum ActionCommand {
	
	START(10),
    MSG_CHAT(20),
	XY_SQUARE(30);
    
    private int actionCommand;
    
    private ActionCommand(int actionCommand) {
    	this.actionCommand = actionCommand;
    }

    public int getActionCommand() {
    	 return actionCommand;
    }

}