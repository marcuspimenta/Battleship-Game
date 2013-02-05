package com.battleship.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

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
		
		shufflingPieces();
		
		Square[][] square = new Square[15][15];
		
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				square[row][column] = new Square(row, column, Color.BLACK, area[row][column], true);
				board.add(square[row][column]);
			}
		}
		
		return board;
	}
	
	public void shufflingPieces(){
		ArrayList<Component> listComponents = new ArrayList<Component>();
		listComponents.add(new Aircraftcarrier());
		listComponents.add(new Submarine());
		listComponents.add(new Submarine());
		listComponents.add(new Submarine());
		listComponents.add(new Submarine());
		listComponents.add(new Seaplane());
		listComponents.add(new Seaplane());
		listComponents.add(new Seaplane());
		listComponents.add(new Cruiser());
		listComponents.add(new Cruiser());
		listComponents.add(new Cruiser());
		listComponents.add(new Battleship());
		listComponents.add(new Battleship());
		
		ArrayList<Integer[]> listQuadrants = new ArrayList<Integer[]>();
		listQuadrants.add(new Integer[]{0,0});
		listQuadrants.add(new Integer[]{0,5});
		listQuadrants.add(new Integer[]{0,10});
		listQuadrants.add(new Integer[]{3,0});
		listQuadrants.add(new Integer[]{3,5});
		listQuadrants.add(new Integer[]{3,10});
		listQuadrants.add(new Integer[]{6,0});
		listQuadrants.add(new Integer[]{6,5});
		listQuadrants.add(new Integer[]{6,10});
		listQuadrants.add(new Integer[]{9,0});
		listQuadrants.add(new Integer[]{9,5});
		listQuadrants.add(new Integer[]{9,10});
		listQuadrants.add(new Integer[]{12,0});
		listQuadrants.add(new Integer[]{12,5});
		listQuadrants.add(new Integer[]{12,10});
		
		while(listComponents.size() > 0){
			int randomIndexListComponents = (new Random()).nextInt(listComponents.size());
			int randomIndexListQuadrants = (new Random()).nextInt(listQuadrants.size());
			
			paintComponent(listQuadrants.get(randomIndexListQuadrants)[0], 
						   listQuadrants.get(randomIndexListQuadrants)[1], 
						   listComponents.get(randomIndexListComponents));
			
			listComponents.remove(randomIndexListComponents);
			listQuadrants.remove(randomIndexListQuadrants);
		}
	}
	
	public void paintComponent(int row, int column, Component component){
		for (int x = row, a = 0; x < row + 3; x++, a++) {
			for (int y = column, b = 0; y < column + 5; y++, b++) {
				area[x][y] = component.getArea()[a][b];
			}
		}
	}
	
	public boolean[][] getArea(){
		return area;
	}
	
}