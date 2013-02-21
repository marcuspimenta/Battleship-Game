package com.battleship.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import com.battleship.business.Action;
import com.battleship.business.ActionsCallback;
import com.battleship.components.Aircraftcarrier;
import com.battleship.components.Battleship;
import com.battleship.components.Component;
import com.battleship.components.Cruiser;
import com.battleship.components.Piece;
import com.battleship.components.Seaplane;
import com.battleship.components.Submarine;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 16:48:34 30/01/2013
 */
@SuppressWarnings("serial")
public class WindowBuilder extends JFrame implements ActionListener{

	public static String host;
	
	private Container c;
	private Square[][] squareSecondary;
	
	private Board board;
	private JButton send;
	private JTextArea message;
	private JPanel boardSecondary;
	private JTextArea displayAreaMsg;
	private JScrollPane scrollpane;
	private JMenuItem menuAbout, menuExit, menuServer, menuClient, menuRepaint, menuQuiGame;
	
	private ActionsCallback actionsCallback;

	public WindowBuilder(ActionsCallback actionsCallback){
		this.actionsCallback = actionsCallback;
	}
	
	public void printTabuleiro(){
		
		c = getContentPane();
		
		board = new Board();
		
		boardSecondary = new JPanel();
		boardSecondary.setBorder(new TitledBorder("Jogo do Adversário"));
		boardSecondary.setLayout(new GridLayout(15, 15, 0, 0));
		
		squareSecondary = new Square[15][15];
		createCleanBoard();

		displayAreaMsg = new JTextArea(10, 30);
		displayAreaMsg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		displayAreaMsg.setLineWrap(true);
		displayAreaMsg.setEditable(false);
		
		scrollpane = new JScrollPane(displayAreaMsg);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		message = new JTextArea(1, 25);
		message.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		message.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		message.setEditable(true);
		
		send = new JButton("Enviar");
		send.addActionListener(this);
		
		JPanel panelTextButton = new JPanel();
		panelTextButton.setLayout(new BorderLayout());
		panelTextButton.add(message, BorderLayout.WEST);
		panelTextButton.add(send, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Armas"));
		panel.setLayout(new GridLayout(3, 2));
		panel.add(showPiecesGame("Hidroavião", 3, 5, (new Seaplane(1, 1))));
		panel.add(showPiecesGame("Submarino", 3, 5, (new Submarine(1, 2))));
		panel.add(showPiecesGame("Cruzador", 3, 5, (new Cruiser(1, 1))));
		panel.add(showPiecesGame("Encouraçado", 3, 5, (new Battleship(1, 0))));
		panel.add(showPiecesGame("Porta-avião", 3, 5, (new Aircraftcarrier(1, 0))));
		
		JPanel panelChat = new JPanel();
		panelChat.add(scrollpane, BorderLayout.CENTER);
		panelChat.add(panelTextButton, BorderLayout.SOUTH);
		panelChat.setBorder(new TitledBorder("Chat"));
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(board.paintBoard(), BorderLayout.CENTER);
		mainPanel.add(boardSecondary, BorderLayout.CENTER);
		mainPanel.setBorder(new TitledBorder("Jogadores"));
		
		JMenu optionMenu = new JMenu("Opções");
		
		menuServer = new JMenuItem("Iniciar como servidor");
		menuServer.addActionListener(this);
		
		menuClient = new JMenuItem("Iniciar como cliente");
		menuClient.addActionListener(this);
		
		menuQuiGame = new JMenuItem("Abandonar jogo");
		menuQuiGame.addActionListener(this);
		
		menuRepaint = new JMenuItem("Novo tabuleiro");
		menuRepaint.addActionListener(this);
		
		menuAbout = new JMenuItem("Sobre");
		menuAbout.addActionListener(this);
		
		menuExit = new JMenuItem("Sair");
		menuExit.addActionListener(this);
		
		optionMenu.add(menuServer);
		optionMenu.add(menuClient);
		optionMenu.add(menuQuiGame);
		optionMenu.add(menuRepaint);
		optionMenu.add(menuAbout);
		optionMenu.add(menuExit);

		JMenuBar menu = new JMenuBar();
		menu.add(optionMenu);
		
		setJMenuBar(menu);
		add(mainPanel, BorderLayout.NORTH);
		add(panelChat, BorderLayout.CENTER);
		add(panel, BorderLayout.WEST);
		setSize(600, 630);
		setTitle("Battleship Game - by Marcus Pimenta");
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		confEnableButtonsMenu(true);
	}
	
	public JPanel showPiecesGame(String title, int rows, int cols, Component component){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(title));
		panel.setLayout(new GridLayout(rows, cols));
		
		boolean area[][] = new boolean[rows][cols];
		
		for (Piece piece : component.getPieces()) {
			area[piece.getPosition().getRow()][piece.getPosition().getColumn()] = true;
		}
		
		Square[][] square = new Square[rows][cols];
		
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				if(area[row][column]){
					square[row][column] = new Square(row, column, Color.BLACK, area[row][column], area[row][column]);
				}else{
					square[row][column] = new Square(row, column, Color.BLACK, false, false);
				}
				panel.add(square[row][column]);
			}
		}
		
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == send){
			if(message.getText().length() > 0){
				if(event.getSource().equals(send)){
					printMsgDisplay("Você: " + message.getText());
		   			actionsCallback.onSendMessageChat(message.getText());

		   			message.setText("");
				}
			}
			
		}else if(event.getSource() == menuAbout){
			JOptionPane.showMessageDialog(c,
					"BattleShip Game\n\n" +
					"Autor:        Marcus Pimenta\n" +
					"email:        mvinicius.pimenta@gmail.com\n" +
					"Data: 		         jan 31, 2013",
					"Sobre BattleShip", JOptionPane.PLAIN_MESSAGE);
			
		}else if(event.getSource() == menuExit){
			actionsCallback.onActionSelected(Action.CLOSE_COMMUNICATION);
			System.exit(0);
			
		}else if(event.getSource() == menuRepaint){
			board.repaintBoard();
			
		}else if(event.getSource() == menuClient){
			confEnableButtonsMenu(false);
			displayAreaMsg.setText("");
			
			host = JOptionPane.showInputDialog(c.getParent(),
											   "Digite o host do servidor:", "Configuração do host do servidor",
											   JOptionPane.QUESTION_MESSAGE);
			
			actionsCallback.onActionSelected(Action.START_CLIENT);
			
		}else if(event.getSource() == menuServer){
			confEnableButtonsMenu(false);
			displayAreaMsg.setText("");
			
			actionsCallback.onActionSelected(Action.START_SERVER);
			
		}else if(event.getSource() == menuQuiGame){
			confEnableButtonsMenu(true);
			displayAreaMsg.setText("");
			
			reseatBoard();
			board.repaintBoard();
			
			printMsgDisplay("Partida abandonada");
			actionsCallback.onActionSelected(Action.CLOSE_COMMUNICATION);
		}
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		
		public void mouseReleased(MouseEvent e){
			Square square = (Square)(e.getSource());
			System.out.println("(x,y) - (" + square.getColumn() + "," + square.getRow() + ")");
			
			actionsCallback.onSendCoordinateSquare(square.getRow(), square.getColumn());
		}
		
	};
	
	public void printMsgDisplay(String message){
		displayAreaMsg.append(message +"\n");
		displayAreaMsg.setCaretPosition(displayAreaMsg.getDocument().getLength());
	}
	
	public void confEnableButtonsMenu(boolean value){
		menuClient.setEnabled(value);
		menuServer.setEnabled(value);
		menuRepaint.setEnabled(value);
		menuQuiGame.setEnabled(!value);
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void setColorSquare(int x, int y, Color color){
		squareSecondary[x][y].setFill(true);
		squareSecondary[x][y].setSquareColor(color);
		squareSecondary[x][y].repaint();
	}
	
	public void createCleanBoard(){
		for (int row = 0; row < squareSecondary.length; row++) {
			for (int column = 0; column < squareSecondary[row].length; column++) {
				squareSecondary[row][column] = new Square(row, column, Color.BLACK, false, true);
				squareSecondary[row][column].addMouseListener(mouseAdapter);
				boardSecondary.add(squareSecondary[row][column]);
			}
		}
	}
	
	public void reseatBoard(){
		for (int row = 0; row < squareSecondary.length; row++) {
			for (int column = 0; column < squareSecondary[row].length; column++) {
				squareSecondary[row][column].setFill(false);
				squareSecondary[row][column].repaint();
			}
		}
	}
	
}