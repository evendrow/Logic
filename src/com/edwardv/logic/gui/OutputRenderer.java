package com.edwardv.logic.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.edwardv.logic.Renderable;
import com.edwardv.logic.misc.DataProvider;

public class OutputRenderer implements Renderable{

	private DataProvider output;
	private int index;
	
	private LogicElementRenderer logicElement;
	
	private int width;
	private int height;
	
	private int xOffset;
	private int yOffset;
	
	public OutputRenderer(DataProvider output, int index, LogicElementRenderer logicElement, int xOffset, int yOffset) {
		this.output = output;
		this.index = index;
		
		this.logicElement = logicElement;
		
		this.width = 15;
		this.height = 15;
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		
		g.fillRect((int) logicElement.x + xOffset, (int) logicElement.y + yOffset + (width/2) - 1, height, 4);
//		g.fillRect(x, y, width, height);
//		
//		g.setColor(Color.BLACK);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) logicElement.x + xOffset, (int) logicElement.y + yOffset, width, height);
	}
	
	public boolean isPowered() {
		return output.getBit(index);
	}

	public DataProvider getOutput() {
		return output;
	}
	
	public int getOutputIndex() {
		return index;
	}
	
	public int getX() {
		return (int) logicElement.x + xOffset;
	}
	
	public int getY() {
		return (int) logicElement.y + yOffset;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
