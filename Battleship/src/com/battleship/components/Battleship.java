package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:17:06 04/02/2013
 */
public class Battleship implements Component{
	
	private boolean[][] battleship = {{true, true, true, true, false},
			   						  {false, false, false, false, false}};

	@Override
	public boolean[][] getArea() {
		return battleship;
	}

}
