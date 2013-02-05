package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:24:04 04/02/2013
 */
public class Seaplane implements Component{

	private boolean[][] seaplane = {{false, false, false, false, false},
									{false, false, true, false, false},
			 						{false, true, false, true, false}};
	
	@Override
	public boolean[][] getArea() {
		return seaplane;
	}

}