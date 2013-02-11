package com.battleship.manager;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:17:44 15/01/2013
 */
public class SocketClient { 
	
	private Socket socket;
	
	public Socket startClient(String host, int port){
		
		try {
			socket = new Socket(host, port);
			
			return socket;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void stopClient(){
        try{
        	if(socket != null){
        		socket.close();
        	}
        }
        catch (IOException e) {
			e.printStackTrace();
		} 
    }
	 
}  