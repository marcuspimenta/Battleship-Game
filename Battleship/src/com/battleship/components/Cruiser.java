package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:18:46 04/02/2013
 */
public class Cruiser implements Component{

	private boolean[][] cruiser = {{false, true, true, false, false},
		     					   {false, false, false, false, false}};
	
	@Override
	public boolean[][] getArea() {
		return cruiser;
	}

}
