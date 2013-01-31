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
	
	private final int PORT = 100;
	
	private Socket socket;
	private ServerSocket server;
	private SocketCommunication communication;
	
	public void startServer(SocketCallback socketCallback){
		try {
			ServerSocket server = new ServerSocket(PORT);
			socket = server.accept();
			
			communication = new SocketCommunication(socket, socketCallback);
			communication.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void stopServer(){
        try{
        	communication.stopComunication();
        	
	        socket.close();
	        server.close();
        }
        catch (IOException e) {
			e.printStackTrace();
		} 
    }

	public SocketCommunication getCommunication() {
		return communication;
	}
	
}