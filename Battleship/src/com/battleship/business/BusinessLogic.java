package com.battleship.business;

import java.net.Socket;

import com.battleship.manager.SocketCallback;
import com.battleship.manager.SocketClient;
import com.battleship.manager.SocketCommunication;
import com.battleship.manager.SocketServer;
import com.battleship.protocol.ActionCommand;
import com.battleship.protocol.Command;
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
	
	private Command command;
	private WindowBuilder windowBuilder;
	
	public BusinessLogic(){
		command = new Command();
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
				communication.sendMsg(command.formCommand(ActionCommand.MSG_CHAT.getActionCommand(), msg.getBytes()));
			}else{
				windowBuilder.printMsgDisplay("Sem conexão");
			}
		}
	};
	
	private SocketCallback callback = new SocketCallback() {
		
		@Override
		public void onMessageListener(byte[] msgReceiver) {
			command(msgReceiver);
		}

		@Override
		public void onPrintMsgConsole(String msg) {
			windowBuilder.printMsgDisplay(msg);
		}
	};
	
	public void command(byte[] msgReceiver){
		int actionCommand = command.getActionCommand(msgReceiver);
		
		if(actionCommand == ActionCommand.MSG_CHAT.getActionCommand()){
			byte[] content = command.getContentCommand(msgReceiver);
			
			if(content != null){
				windowBuilder.printMsgDisplay("Adversário: " + new String(content));
			}
		}else if(actionCommand == ActionCommand.XY_SQUARE.getActionCommand()){
			//TODO
		}else{
			windowBuilder.printMsgDisplay("Mensagem inválida");
		}
	}
	
	private void startAction(Action action){
		switch (action) {
			case START_SERVER:
				startServer();
				break;
				
			case START_CLIENT:
				startClient();
				break;
			
			case CLOSE_COMMUNICATION:
				closeCommunication();
				break;
		}
	}
	
	private void startServer(){
		closeCommunication();
		
		windowBuilder.printMsgDisplay("Servidor aberto\nAguardando conexao...\n");

		new Thread(){
			@Override
			public void run() {
				super.run();
				
				server = new SocketServer();
				Socket socket = server.startServer(PORT);
				
				if(socket != null){
					configCommunication(socket);
				}else{
					windowBuilder.confEnableButtonsMenu(true);
					windowBuilder.printMsgDisplay("Tempo de espera por conexão ultrapassado\nTente novamente");
				}
			}
		}.start();
	}
	
	private void startClient(){
		if(!WindowBuilder.host.equals("")){
			closeCommunication();
			windowBuilder.printMsgDisplay("Cliente iniciado...\n");

			client = new SocketClient();
			Socket socket = client.startClient(WindowBuilder.host, PORT);
			
			if(socket != null){
				configCommunication(socket);
			}else{
				windowBuilder.confEnableButtonsMenu(true);
				windowBuilder.printMsgDisplay("Erro ao tenta se conectar com o servidor\nVerifique se o host digitado está correto");
			}
		}else{
			windowBuilder.printMsgDisplay("Digite o valor do host");
		}
	}
	
	private void closeCommunication(){
		if(server != null){
			server.stopServer();
		}
		
		if(client != null){
			client.stopClient();
		}
	}

	public void configCommunication(Socket socket){
		communication = new SocketCommunication(socket, callback);
		communication.start();
	}
	
}