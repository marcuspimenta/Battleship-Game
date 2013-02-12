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
			
			 socketCallback.onMessageListener("Conexao realizada com sucesso");
			 
			 while (run) {
				 if(dataInputStream.available() > 0){
					 byte[] msg = new byte[dataInputStream.available()];
					 dataInputStream.read(msg, 0, dataInputStream.available());
					 
					 socketCallback.onMessageListener("Adversário: " + new String(msg));
				 }
			 }
		 } catch (IOException e) {
			 e.printStackTrace(); 
			 
			 stopComunication();
		 }
	}
	
	public void sendMsg(String msg){
		try {
			if(dataOutputStream != null){
				dataOutputStream.write(msg.getBytes());
				dataOutputStream.flush();
			}else{
				socketCallback.onMessageListener("Sem conexao");
			}
			
		} catch (IOException e) {
			e.printStackTrace(); 
			 
			socketCallback.onMessageListener("Falha no envio da mensagem\n" +
											 "O adversário deve ter abandonado a partida\n" +
											 "Para iniciar outra partida abandone está partida");
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