package com.battleship.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.battleship.components.Aircraftcarrier;
import com.battleship.components.Battleship;
import com.battleship.components.Component;
import com.battleship.components.Cruiser;
import com.battleship.components.Seaplane;
import com.battleship.components.Submarine;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 20:01:57 04/02/2013
 */
public class Board {
	
	private final int ROWS = 15;
	private final int COLS = 15;
	
	private Component[] component = {new Aircraftcarrier(),
									 new Battleship(),
									 new Cruiser(),
									 new Seaplane(),
									 new Submarine()};
	
	private boolean area[][] = { {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
								 {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}};
	
	public JPanel paintBoard(){
		JPanel board = new JPanel();
		board.setBorder(new TitledBorder("Seu jogo"));
		board.setLayout(new GridLayout(15, 15, 0, 0));
		
		Square[][] square = new Square[15][15];
		
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				square[row][column] = new Square(row, column, Color.BLACK, area[row][column], true);
				board.add(square[row][column]);
			}
		}
		
		return board;
	}
	
	public void setArea(int rows, int cols, boolean value){
		if(rows <= ROWS && cols <= COLS){
			area[rows][cols] = value;
		}
	}
	
	public boolean[][] getArea(){
		return area;
	}
	
}