package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:21:59 04/02/2013
 */
public class Submarine implements Component{
	
	private boolean[][] submarine = {{false, false, false, false, false},
									 {false, false, true, false, false},
			 						 {false, false, false, false, false}};

	@Override
	public boolean[][] getArea() {
		return submarine;
	}

}