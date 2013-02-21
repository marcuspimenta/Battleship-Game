package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 21:18:43 30/01/2013
 */
public abstract class Component {
	
	protected Piece[] pieces;
	protected Position position;
	
	public Component(){
		position = new Position(0,0);
	}
	
	public Component(int row, int column){
		position = new Position(row,column);
	}

	public abstract String getName();
	
	public abstract void initializePieces();

	public abstract void updatePositonPieces();
	
	public Piece[] getPieces() {
		return pieces;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public int getHeight() {
		int height = 1, highest = 0;
		
		for (Piece piece : pieces) {
			if(piece.getPosition().getRow() >  highest){
				highest = piece.getPosition().getRow();
				height++;
			}
		}
		return height;
	}

	public int getWidth() {
		int width = 1, highest = 0;
		
		for (Piece piece : pieces) {
			if(piece.getPosition().getColumn() >  highest){
				highest = piece.getPosition().getRow();
				width++;
			}
		}
		return width;
	}
	
}