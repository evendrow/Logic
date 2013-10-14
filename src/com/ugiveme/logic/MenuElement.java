package com.ugiveme.logic;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.ugiveme.entity.draggable.DragHandler;

public class MenuElement extends Rectangle{
	
	private String elementName;
	
	private LogicElement element;
	
	public MenuElement(DragHandler dragHandler, int x, int y, String elementName) {
		setBounds(x, y, 60, 60);
		
		this.elementName = elementName;
		
		this.element = LogicHandler.addMenuElement(elementName, x, y);
	}
	
	public void tick() {
		if (!element.getPosPoint().equals(this.getPosPoint())) {
			element.setPartOfMenu(false);
			element = LogicHandler.addMenuElement(elementName, x, y);
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
