package com.edwardv.logic.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.edwardv.logic.Renderable;
import com.edwardv.logic.misc.DataReceiver;

public class InputRenderer implements Renderable {

	private DataReceiver input;
	private int index;
	
	private LogicElementRenderer logicElement;
	
	private int width;
	private int height;
	
	private int xOffset;
	private int yOffset;
	
	public InputRenderer(DataReceiver input, int index, LogicElementRenderer logicElement, int xOffset, int yOffset) {
		this.input = input;
		this.index = index;
		
		this.logicElement = logicElement;
		
		this.width = 15;
		this.height = 15;
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public InputRenderer(DataReceiver input, int index, LogicElementRenderer logicElement, int xOffset, int yOffset, int width, int height) {
		this.input = input;
		this.index = index;
		
		this.logicElement = logicElement;
		
		this.width = width;
		this.height = height;
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
			
		g.fillRect((int) logicElement.x + xOffset, (int) logicElement.y + yOffset + (width/2) - 1, width, 3);
		
//		g.fillRect(x, y, width, height);
//		
//		g.setColor(Color.BLACK);
//		g.drawRect(x, y, width, height);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) logicElement.x + xOffset, (int) logicElement.y + yOffset, width, height);
	}

	public DataReceiver getInput() {
		return input;
	}
	
	public int getInputIndex() {
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
	
	public int getLogicElementId() {
		return logicElement.getId();
	}
}
