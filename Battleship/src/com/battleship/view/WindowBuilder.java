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


/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 16:48:34 30/01/2013
 */
@SuppressWarnings("serial")
public class WindowBuilder extends JFrame implements ActionListener{

	private Container c;
	
	private JButton send;
	private JTextArea message;
	private JTextArea displayAreaMsg;
	private JMenuItem menuAbout, menuExit;
		
	public void printTabuleiro(){
		
		c = getContentPane();
		
		//create game board
		JPanel boardMain = new JPanel();
		boardMain.setBorder(new TitledBorder("Seu jogo"));
		boardMain.setLayout(new GridLayout(15, 15, 0, 0));
		
		Square[][] square = new Square[15][15];
		
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				square[row][column] = new Square(row, column, Color.WHITE);
				square[row][column].addMouseListener(mouseAdapter);
				boardMain.add(square[row][column]);
			}
		}
		
		JPanel boardSecondary = new JPanel();
		boardSecondary.setBorder(new TitledBorder("Jogo do Adversário"));
		boardSecondary.setLayout(new GridLayout(15, 15, 0, 0));
		
		Square[][] squareSecondary = new Square[15][15];
		
		for (int row = 0; row < squareSecondary.length; row++) {
			for (int column = 0; column < squareSecondary[row].length; column++) {
				squareSecondary[row][column] = new Square(row, column, Color.WHITE);
				squareSecondary[row][column].addMouseListener(mouseAdapter);
				boardSecondary.add(squareSecondary[row][column]);
			}
		}
		
		displayAreaMsg = new JTextArea(10, 30);
		displayAreaMsg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		displayAreaMsg.setLineWrap(true);
		displayAreaMsg.setEditable(false);
		
		JScrollPane scrollpane = new JScrollPane(displayAreaMsg);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		message = new JTextArea(1, 25);
		message.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		message.setLineWrap(true);
		message.setEditable(true);
		
		send = new JButton("Enviar");
		send.addActionListener(this);
		
		JPanel panelTextButton = new JPanel();
		panelTextButton.setLayout(new BorderLayout());
		panelTextButton.add(message, BorderLayout.WEST);
		panelTextButton.add(send, BorderLayout.EAST);
		
		JPanel panelChat = new JPanel();
		//panelChat.setLayout(new BorderLayout());
		panelChat.add(scrollpane, BorderLayout.CENTER);
		panelChat.add(panelTextButton, BorderLayout.SOUTH);
		panelChat.setBorder(new TitledBorder("Chat"));
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(boardMain, BorderLayout.CENTER);
		mainPanel.add(boardSecondary, BorderLayout.CENTER);
		mainPanel.setBorder(new TitledBorder("Jogadores"));
		
		JMenu optionMenu = new JMenu("Opções");
		
		JMenuItem menutServer = new JMenuItem("Iniciar como servidor");
		
		JMenuItem menuClient = new JMenuItem("Iniciar como cliente");
		
		menuAbout = new JMenuItem("Sobre");
		menuAbout.addActionListener(this);
		
		menuExit = new JMenuItem("Sair");
		menuExit.addActionListener(this);
		
		optionMenu.add(menutServer);
		optionMenu.add(menuClient);
		optionMenu.add(menuAbout);
		optionMenu.add(menuExit);

		JMenuBar menu = new JMenuBar();
		menu.add(optionMenu);
		
		setJMenuBar(menu);
		add(mainPanel, BorderLayout.NORTH);
		add(panelChat, BorderLayout.CENTER);
		setSize(600, 600);
		setTitle("Battleship - by Marcus Pimenta");
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == message){
			if(message.getText().length() > 0){
				if(event.getSource().equals(send)){
					displayAreaMsg.append( message.getText() +"\n");
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
			System.exit(0);
		}
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		
		public void mouseReleased(MouseEvent e){
			Square square = (Square)(e.getSource());
			System.out.println("(x,y) - (" + square.getColumn() + "," + square.getRow() + ")");
		}
		
	};
	
}