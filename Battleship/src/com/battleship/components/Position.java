package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:03:26 20/02/2013
 */
public class Position {

	private int row;
	private int column;
	
	public Position(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setCoordinatePosition(int row, int column){
		this.row = row;
		this.column = column;
	}
	
}