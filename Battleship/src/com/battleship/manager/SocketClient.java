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
	
	private final int PORT = 92;
	
	private Socket socket;
	private SocketCommunication communication;
	
	public void startClient(String host, SocketCallback socketCallback){
		
		try {
			socket = new Socket(host, PORT);
			
			if(socket.isConnected()){
				communication = new SocketCommunication(socket, socketCallback);
				communication.start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void stopClient(){
        try{
        	communication.stopComunication();
	        socket.close();
        }
        catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
	public SocketCommunication getCommunication() {
		return communication;
	}
	 
}  