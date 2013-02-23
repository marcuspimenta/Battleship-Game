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
	private boolean fill;
	private boolean show;
	private Color squareColor;
	
	public Square(int row, int column, Color squareColor, boolean fill, boolean show){
		this.row = row;
		this.column = column;
		this.squareColor = squareColor;
		this.fill = fill;
		this.show = show;
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
	
	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(17, 17);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		
		if(show){
			if(fill){
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, 16, 16);
				g.setColor(Color.WHITE);
				g.fillRect(1, 1, 15, 15);
				g.setColor(squareColor);
				g.fillRect(2, 2, 13, 13);
			}else{
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, 16, 16);
				g.setColor(Color.GRAY);
				g.fillRect(1, 1, 15, 15);
			}
		}
	}
}