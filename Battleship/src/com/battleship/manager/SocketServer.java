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

	private Socket socket;
	private ServerSocket server;
	
	public Socket startServer(int port, int timerout){
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(timerout*1000);
			
			socket = server.accept();
			
			return socket;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public void stopServer(){
        try{
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
	
}