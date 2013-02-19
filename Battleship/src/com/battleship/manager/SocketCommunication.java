package com.battleship.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 20:35:24 15/01/2013
 */
public class SocketCommunication extends Thread {
	
	private boolean run;
	
	private Socket socket;
	private SocketCallback socketCallback;

	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public SocketCommunication(Socket socket, SocketCallback socketCallback){
		run = true;

		this.socket = socket;
		this.socketCallback = socketCallback;
	}
	
	@Override
	public void run() {
		 super.run();
		
		 try {
			 dataInputStream = new DataInputStream(socket.getInputStream());
			 dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			 socketCallback.onPrintMsgConsole("Conexao realizada com sucesso");
			 
			 while (run) {
				 if(dataInputStream.available() > 0){
					 byte[] msgReceiver = new byte[dataInputStream.available()];
					 dataInputStream.read(msgReceiver, 0, dataInputStream.available());
					 
					 socketCallback.onMessageListener(msgReceiver);
				 }
			 }
		 } catch (IOException e) {
			 e.printStackTrace(); 
			 
			 stopComunication();
		 }
	}
	
	public void sendMsg(byte[] command){
		try {
			if(dataOutputStream != null){
				dataOutputStream.write(command);
				dataOutputStream.flush();
			}else{
				socketCallback.onPrintMsgConsole("Sem conexao");
			}
			
		} catch (IOException e) {
			e.printStackTrace(); 
			
			socketCallback.onPrintMsgConsole("Conexão perdida. Inicie uma nova partida\n");
		}
	}
	
	 public void stopComunication(){ 
		try {
			run = false;
			
			if(dataInputStream != null && dataOutputStream != null){
				dataInputStream.close();
				dataOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }

 }