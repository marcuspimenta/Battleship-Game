package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:17:06 04/02/2013
 */
public class Battleship extends Component{

	public Battleship(){
		super();
		initializePieces();
	}
	
	public Battleship(int row, int column){
		super(row, column);
		initializePieces();
	}
	
	@Override
	public String getName() {
		return "Battleship";
	}

	@Override
	public void initializePieces() {
		pieces = new Piece[]{new Piece(position.getRow(), position.getColumn()),
				 new Piece(position.getRow(), position.getColumn() + 1),
				 new Piece(position.getRow(), position.getColumn() + 2),
				 new Piece(position.getRow(), position.getColumn() + 3)};
	}
	
	@Override
	public void updatePositonPieces() {
		pieces[0].setCoordinatePosition(position.getRow(), position.getColumn());
		pieces[1].setCoordinatePosition(position.getRow(), position.getColumn() + 1);
		pieces[2].setCoordinatePosition(position.getRow(), position.getColumn() + 2);
		pieces[3].setCoordinatePosition(position.getRow(), position.getColumn() + 3);
	}

}