package com.battleship.components;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 21:19:17 30/01/2013
 */
public class Aircraftcarrier implements Component{
	
	private boolean[][] aircraftcarrier = {{false, false, false, false, false},
										   {true, true, true, true, true},
            							   {false, false, false, false, false}};

	@Override
	public boolean[][] getArea() {
		return aircraftcarrier;
	}

}