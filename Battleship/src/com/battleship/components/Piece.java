package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 17:06:52 19/02/2013
 */
public class Piece {
	
	private boolean status;
	private Position position;
	
	public Piece(int row, int column){
		this.status = true;
		this.position = new Position(row, column);
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setCoordinatePosition(int row, int column){
		this.position.setRow(row);
		this.position.setColumn(column);
	}
	
}