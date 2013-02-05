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

import com.battleship.components.Aircraftcarrier;
import com.battleship.components.Battleship;
import com.battleship.components.Cruiser;
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

	private Container c;
	
	private Square[][] squareSecondary;
	
	private Board board;
	
	private JButton send;
	private JTextArea message;
	private JTextArea displayAreaMsg;
	private JMenuItem menuAbout, menuExit, menuServer, menuClient, menuRepaint;
	
	public void printTabuleiro(){
		
		c = getContentPane();
		
		board = new Board();
		
		JPanel boardSecondary = new JPanel();
		boardSecondary.setBorder(new TitledBorder("Jogo do Adversário"));
		boardSecondary.setLayout(new GridLayout(15, 15, 0, 0));
		
		squareSecondary = new Square[15][15];
		
		for (int row = 0; row < squareSecondary.length; row++) {
			for (int column = 0; column < squareSecondary[row].length; column++) {
				squareSecondary[row][column] = new Square(row, column, Color.BLACK, false, true);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Armas"));
		panel.setLayout(new GridLayout(3, 2));
		panel.add(showPiecesGame("Hidroavião", 3, 5, (new Seaplane()).getArea()));
		panel.add(showPiecesGame("Submarino", 3, 5, (new Submarine()).getArea()));
		panel.add(showPiecesGame("Cruzador", 3, 5, (new Cruiser()).getArea()));
		panel.add(showPiecesGame("Encouraçado", 3, 5, (new Battleship()).getArea()));
		panel.add(showPiecesGame("Porta-avião", 3, 5, (new Aircraftcarrier()).getArea()));
		
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
		menuRepaint = new JMenuItem("Novo tabuleiro");
		menuRepaint.addActionListener(this);
		menuAbout = new JMenuItem("Sobre");
		menuAbout.addActionListener(this);
		menuExit = new JMenuItem("Sair");
		menuExit.addActionListener(this);
		
		optionMenu.add(menuServer);
		optionMenu.add(menuClient);
		optionMenu.add(menuRepaint);
		optionMenu.add(menuAbout);
		optionMenu.add(menuExit);

		JMenuBar menu = new JMenuBar();
		menu.add(optionMenu);
		
		setJMenuBar(menu);
		add(mainPanel, BorderLayout.NORTH);
		add(panelChat, BorderLayout.CENTER);
		add(panel, BorderLayout.WEST);
		setSize(600, 600);
		setTitle("Battleship - by Marcus Pimenta");
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public JPanel showPiecesGame(String title, int rows, int cols, boolean[][] paint){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(title));
		panel.setLayout(new GridLayout(rows, cols));
		
		Square[][] square = new Square[rows][cols];
		
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				if(paint != null){
					square[row][column] = new Square(row, column, Color.BLACK, paint[row][column], paint[row][column]);
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
		}else if(event.getSource() == menuRepaint){
			board.repaintBoard();
		}else if(event.getSource() == menuClient){
			JOptionPane.showInputDialog(c.getParent(),
										"Digite o host do servidor:", "Configuração do host do servidor",
										JOptionPane.QUESTION_MESSAGE);
		
		}else if(event.getSource() == menuServer){
			
		}
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		
		public void mouseReleased(MouseEvent e){
			Square square = (Square)(e.getSource());
			
			System.out.println("(x,y) - (" + square.getColumn() + "," + square.getRow() + ")");
		}
		
	};
	
}