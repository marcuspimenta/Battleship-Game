package com.battleship.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

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

	private DataInputStream dataInputStream = null;
	private DataOutputStream dataOutputStream = null;
	
	public SocketCommunication(Socket socket, SocketCallback socketCallback){
		this.socket = socket;
		this.socketCallback = socketCallback;
		
		run = true;
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
					 byte[] msg = new byte[dataInputStream.available()];
					 dataInputStream.read(msg, 0, dataInputStream.available());
					 
					 socketCallback.onSocketReceiverMsg(new String(msg));
				 }
			 }
		 } catch (IOException e) {
			 e.printStackTrace(); 
			 
			 dataInputStream = null;
			 dataOutputStream = null;
		 }
	}
	
	public void sendMsg(String msg){
		try {
			if(dataOutputStream != null){
				dataOutputStream.write(msg.getBytes());
				dataOutputStream.flush();
			}else{
				socketCallback.onPrintMsgConsole("Sem conexao");
			}
			
		} catch (IOException e) {
			e.printStackTrace(); 
			 
			socketCallback.onPrintMsgConsole("Falha no envio da mensagem\n" +
											 "Conexão perdida, abandone a partida e inicie outra\n");
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