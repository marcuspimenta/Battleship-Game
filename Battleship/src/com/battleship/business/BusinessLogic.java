package com.battleship.business;

import java.awt.Color;
import java.net.Socket;

import com.battleship.components.Component;
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
	//private final int NUMBER_MOVES = 3;
	//private final int NUMBER_RESPONSES = 3;
	
	//private int moves_sent = 0;
	//private int responses_receiver = 0;
	
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
		public void onSendMessageChat(String msg) {
			sendCommand(command.formCommand(ActionCommand.MSG_CHAT.getActionCommand(), msg.getBytes()));
		}

		@Override
		public void onSendCoordinateSquare(int row, int column) {
			sendCommand(command.formCommand(ActionCommand.XY_SQUARE.getActionCommand(), new byte[]{(byte)row, (byte)column}));			
		}
	};
	
	private SocketCallback callback = new SocketCallback() {
		
		@Override
		public void onMessageListener(byte[] msgReceiver) {
			manageCommand(msgReceiver);
		}

		@Override
		public void onPrintMsgConsole(String msg) {
			windowBuilder.printMsgDisplay(msg);
		}
	};
	
	public void manageCommand(byte[] msgReceiver){
		int actionCommand = command.getActionCommand(msgReceiver);
		
		if(actionCommand == ActionCommand.MSG_CHAT.getActionCommand()){
			byte[] content = command.getContentCommand(msgReceiver);
			
			if(content != null){
				windowBuilder.printMsgDisplay("Adversário: " + new String(content));
			}
			
		}else if(actionCommand == ActionCommand.XY_SQUARE.getActionCommand()){
			byte[] content = command.getContentCommand(msgReceiver);
			
			if(windowBuilder.getBoard().getValueSquare(content[0], content[1])){
				Component component = windowBuilder.getBoard().getComponent(content[0], content[1]);
				
				windowBuilder.getBoard().setValueSquare(content[0], content[1], false);
				windowBuilder.getBoard().setColorSquare(content[0], content[1], Color.RED);
				sendCommand(command.formCommand(ActionCommand.RESPONSE_XY.getActionCommand(), new byte[]{1, content[0], content[1]}, component.getName().getBytes()));
			}else{
				windowBuilder.getBoard().setColorSquare(content[0], content[1], Color.BLUE);
				sendCommand(command.formCommand(ActionCommand.RESPONSE_XY.getActionCommand(), new byte[]{0, content[0], content[1]}));
			}
			
		}else if(actionCommand == ActionCommand.RESPONSE_XY.getActionCommand()){
			byte[] content = command.getContentCommand(msgReceiver);
			
			if(content[0] == 1){
				windowBuilder.printMsgDisplay("Você acertou a peça: " + new String(command.getNameComponent(msgReceiver)));
				windowBuilder.setColorSquare(content[1], content[2], Color.RED);
			}else{
				windowBuilder.setColorSquare(content[1], content[2], Color.BLUE);
			}
			
		}else{
			windowBuilder.printMsgDisplay("Mensagem inválida");
		}
	}
	
	public void sendCommand(byte[] command){
		if(communication != null){
			communication.sendMsg(command);
		}else{
			windowBuilder.printMsgDisplay("Sem conexão");
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
		if(communication != null){
			communication.stopComunication();
			communication = null;
		}
		
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