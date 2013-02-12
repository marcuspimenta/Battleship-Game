package com.battleship.business;

import java.net.Socket;

import com.battleship.manager.SocketCallback;
import com.battleship.manager.SocketClient;
import com.battleship.manager.SocketCommunication;
import com.battleship.manager.SocketServer;
import com.battleship.view.WindowBuilder;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 22:47:37 30/01/2013
 */
public class BusinessLogic {
	
	private final int PORT = 97;
	
	private SocketServer server;
	private SocketClient client;
	private SocketCommunication communication;
	
	private WindowBuilder windowBuilder;
	
	public BusinessLogic(){
		windowBuilder = new WindowBuilder(actionsCallback);
	}
	
	public void showWindow(){
		windowBuilder.printTabuleiro();
	}
	
	private ActionsCallback actionsCallback = new ActionsCallback(){

		@Override
		public void onActionSelected(Action action) {
			startAction(action);
		}

		@Override
		public void onSendMsg(String msg) {
			if(communication != null){
				communication.sendMsg(msg);
			}else{
				windowBuilder.printMsgDisplay("Sem conexão");
			}
		}
		
	};
	
	private SocketCallback callback = new SocketCallback() {
		
		@Override
		public void onSocketReceiverMsg(String msg) {
			windowBuilder.printMsgDisplay("Adversário: " + msg);
		}

		@Override
		public void onPrintMsgConsole(String msg) {
			windowBuilder.printMsgDisplay(msg);
		}

		@Override
		public void onAction(Action action) {
			startAction(action);
		}
	};
	
	private void startAction(Action action){
		switch (action) {
			case START_SERVER:
				stopCommunication();
				
				windowBuilder.printMsgDisplay("Servidor aberto");
				windowBuilder.printMsgDisplay("Aguardando conexao... \n");

				new Thread(){
					@Override
					public void run() {
						super.run();
						
						server = new SocketServer();
						configCommunication(server.startServer(PORT));
					}
				}.start();
				break;
				
			case START_CLIENT:
				if(!WindowBuilder.host.equals("")){
					stopCommunication();
					windowBuilder.printMsgDisplay("Cliente iniciado \n");

					client = new SocketClient();
					configCommunication(client.startClient(WindowBuilder.host, PORT));
				}else{
					windowBuilder.printMsgDisplay("Digite o valor do host");
				}
				break;
			
			case CLOSE_COMMUNICATION:
				stopCommunication();
				break;
				
			case LOST_CONNECTION:
				windowBuilder.printMsgDisplay("Conexao perdida");
				
				stopCommunication();
				windowBuilder.confEnableButtonsMenu(true);
				break;
		}
	}

	public void configCommunication(Socket socket){
		communication = new SocketCommunication(socket, callback);
		communication.start();
	}
	
	public void stopCommunication(){
		if(server != null){
			server.stopServer();
		}
		
		if(client != null){
			client.stopClient();
		}
	}
	
}