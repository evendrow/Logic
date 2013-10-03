package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.ugiveme.entity.draggable.DragHandler;

public class MenuElement extends Rectangle{
	
	private LogicHandler logicScreen;
	private DragHandler dragHandler;
	
	private String elementName;
	
	private LogicElement element;
	
	public MenuElement(LogicHandler logicScreen, DragHandler dragHandler, int x, int y, String elementName) {
		setBounds(x, y, 60, 60);
		
		this.logicScreen = logicScreen;
		this.dragHandler = dragHandler;
		
		this.elementName = elementName;
		
		this.element = this.logicScreen.addGate(elementName, x, y);
	}
	
	public void tick() {
		if (!element.getPosPoint().equals(this.getPosPoint())) {
			element = logicScreen.addGate(elementName, x, y);
		}
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.BLACK);
//		g.drawRect(x, y, width, height);
//		g.drawString(elementName, x + 20, y + 20);
	}
	
	public Point getPosPoint() {
		return new Point(x, y);
	}
}
