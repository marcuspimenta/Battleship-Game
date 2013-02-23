package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 21:19:17 30/01/2013
 */
public class Aircraftcarrier extends Component{

	public Aircraftcarrier(){
		super();
		initializePieces();
	}
	
	public Aircraftcarrier(int row, int column){
		super(row, column);
		initializePieces();
	}
	
	@Override
	public String getName() {
		return "Aircraft carrier";
	}
	
	@Override
	public void initializePieces() {
		pieces = new Piece[]{new Piece(position.getRow(), position.getColumn()),
				 new Piece(position.getRow(), position.getColumn() + 1),
				 new Piece(position.getRow(), position.getColumn() + 2),
				 new Piece(position.getRow(), position.getColumn() + 3),
				 new Piece(position.getRow(), position.getColumn() + 4)};
	}

	@Override
	public void updatePositonPieces() {
		pieces[0].setCoordinatePosition(position.getRow(), position.getColumn());
		pieces[1].setCoordinatePosition(position.getRow(), position.getColumn() + 1);
		pieces[2].setCoordinatePosition(position.getRow(), position.getColumn() + 2);
		pieces[3].setCoordinatePosition(position.getRow(), position.getColumn() + 3);
		pieces[4].setCoordinatePosition(position.getRow(), position.getColumn() + 4);
	}

}