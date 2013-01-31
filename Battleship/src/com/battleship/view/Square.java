package com.battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 16:52:16 30/01/2013
 */
@SuppressWarnings("serial")
public class Square extends JPanel{
	
	private int row;
	private int column;
	private Color squareColor;
	
	public Square(int row, int column, Color squareColor){
		this.row = row;
		this.column = column;
		this.squareColor = squareColor;
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

	public Color getSquareColor() {
		return squareColor;
	}

	public void setSquareColor(Color squareColor) {
		this.squareColor = squareColor;
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(20, 20);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 19, 19);
	}
}
