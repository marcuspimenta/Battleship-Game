package com.battleship.manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:18:05 15/01/2013
 */
public class SocketServer {
	
	private final int PORT = 96;
	
	private ServerSocket server;
	private Socket socket;
	private SocketCommunication communication;
	
	public void startServer(SocketCallback socketCallback){
		try {
			server = new ServerSocket(PORT);
			socket = server.accept();
			
			communication = new SocketCommunication(socket, socketCallback);
			communication.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void stopServer(){
        try{
        	if(communication != null){
        		communication.stopComunication();
        	}
        	
        	if(socket != null){
        		socket.close();
        	}
        	
        	if(server != null){
        		server.close();
        	}
        }
        catch (IOException e) {
			e.printStackTrace();
		} 
    }

	public SocketCommunication getCommunication() {
		return communication;
	}
	
}