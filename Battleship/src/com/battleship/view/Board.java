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
import com.battleship.components.Piece;
import com.battleship.components.Seaplane;
import com.battleship.components.Submarine;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 20:01:57 04/02/2013
 */
public class Board {
	
	private Square[][] square;
	
	private boolean area[][] = new boolean[15][15];
	private ArrayList<Component> componentes = new ArrayList<Component>();
	
	public JPanel paintBoard(){
		JPanel board = new JPanel();
		board.setBorder(new TitledBorder("Your game"));
		board.setLayout(new GridLayout(15, 15, 0, 0));
		
		resetBoard();
		shufflingPieces();
		
		square = new Square[15][15];
		
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				square[row][column] = new Square(row, column, Color.BLACK, area[row][column], true);
				board.add(square[row][column]);
			}
		}
		
		return board;
	}
	
	public void repaintBoard(){
		componentes.clear();
		resetBoard();
		resetSquaresBoard();
		shufflingPieces();
		repainSquaresBoard();
	}
	
	public void shufflingPieces(){
		ArrayList<Component> listComponents = new ArrayList<Component>();
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
		listComponents.add(new Aircraftcarrier());
		
		while(listComponents.size() > 0){
			int randomIndexList = (new Random()).nextInt(listComponents.size());
			int randomRow = (new Random()).nextInt(15);
			int randomColumn = (new Random()).nextInt(15);
			
			if(verifyAreaComponentSupport(listComponents.get(randomIndexList), randomRow, randomColumn) && !verifyComponentArea(listComponents.get(randomIndexList), randomRow, randomColumn)){
				Component component = listComponents.get(randomIndexList);
				component.getPosition().setCoordinatePosition(randomRow, randomColumn);
				component.updatePositonPieces();
				
				componentes.add(component);
				putComponentInBoard(component);
				
				listComponents.remove(randomIndexList);
			}
		}
	}
	
	public boolean verifyAreaComponentSupport(Component component, int row, int column){
		if(component.getHeight() + row <= 15 && component.getWidth() + column <= 15){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verifyComponentArea(Component component, int row, int column){
		for (Piece piece : component.getPieces()) {
			if(area[piece.getPosition().getRow() + row][piece.getPosition().getColumn() + column]){
				return true;
			}
		}
		
		return false;
	}

	public void putComponentInBoard(Component component){
		for (Piece piece : component.getPieces()) {
			area[piece.getPosition().getRow()][piece.getPosition().getColumn()] = true;
		}
	}
	
	public void resetBoard(){
		for (int row = 0; row < area.length; row++) {
			for (int column = 0; column < area[row].length; column++) {
				area[row][column] = false;
			}
		}
	}
	
	public void resetSquaresBoard(){
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				square[row][column].setSquareColor(Color.BLACK);
				square[row][column].setFill(false);
				square[row][column].repaint();
			}
		}
	}
	
	public void repainSquaresBoard(){
		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				square[row][column].setFill(area[row][column]);
				square[row][column].repaint();
			}
		}
	}
	
	public void setColorSquare(int row, int column, Color color){
		square[row][column].setFill(true);
		square[row][column].setSquareColor(color);
		square[row][column].repaint();
	}
	
	public Component getComponent(int row, int column){
		for (Component component : componentes) {
			for (Piece piece : component.getPieces()) {
				if(piece.getPosition().getRow() == row && piece.getPosition().getColumn() == column){
					return component;
				}
			}
		}
		
		return null;
	}
	
	public boolean verifyComponentKill(Component component){
		for (Piece piece : component.getPieces()) {
			if(piece.isStatus()){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean verifyComponentsSubmerged(){
		for (Component component : componentes) {
			for (Piece piece : component.getPieces()) {
				if(piece.isStatus()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean getValueSquare(int x, int y){
		if(x <= area.length && y <= area.length){
			return area[x][y];
		}else{
			return false;
		}
	}
	
	public void immersePiece(int row, int column, boolean value){
		setValueSquare(row, column, value);
		setStatusValuePieceComponent(row, column, value);
	}
	
	public void setValueSquare(int row, int column, boolean value){
		if(row <= area.length && column <= area.length){
			area[row][column] = value;
		}
	}
	
	public void setStatusValuePieceComponent(int row, int column, boolean value){
		for (Component component : componentes) {
			for (Piece piece : component.getPieces()) {
				if(piece.getPosition().getRow() == row && piece.getPosition().getColumn() == column){
					piece.setStatus(value);
				}
			}
		}
	}
	
	public boolean[][] getArea(){
		return area;
	}
	
}