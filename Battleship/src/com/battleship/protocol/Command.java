package com.battleship.protocol;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 18:11:51 15/02/2013
 */
public class Command {
	
	private final int COUNT_BYTE_FIXED = 3;
	
	public byte[] getContentCommand(byte[] command){
		if(validateCommand(command)){
			byte[] content = new byte[command.length - COUNT_BYTE_FIXED];
			
			for (int i = 2, m = 0; i < command.length - 1; i++, m++) {
				content[m] = command[i];
			}
			
			return content;
		}else{
			return null;
		}
	}
	
	public int getActionCommand(byte[] command){
		if(validateCommand(command)){
			return command[1];
		}else{
			return -1;
		}
	}
	
	public byte[] formCommand(int action, byte[] count){
		byte checksun = calculateChecksun(action, count);
		
		byte[] command = new byte[count.length + COUNT_BYTE_FIXED];
		
		command[0] = (byte) ActionCommand.START.getActionCommand();
		command[1] = (byte) action;
		command[command.length - 1] = checksun;
		
		for (int a = 0, b = 2; a < count.length; a++, b++) {
			command[b] = count[a];
		}
		
		return command;
	}
	
	public byte calculateChecksun(int action, byte[] content){
		int checksun = ActionCommand.START.getActionCommand() + action;
		
		for (byte info : content) {
			checksun += info;
		}
		
		return (byte)checksun;
	}
	
	public boolean validateCommand(byte[] command){
		if(validateChecksun(command) && command.length > COUNT_BYTE_FIXED && command[0] == ActionCommand.START.getActionCommand()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean validateChecksun(byte[] command){
		byte checksun = 0;
		
		for (int i = 0; i < command.length - 1; i++) {
			checksun += command[i];
		}
		
		if(checksun == command[command.length - 1]){
			return true;
		}else{
			return false;
		}
	}

}