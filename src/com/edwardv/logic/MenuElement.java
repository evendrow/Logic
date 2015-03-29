package com.edwardv.logic;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.edwardv.entity.draggable.DragHandler;
import com.edwardv.logic.gui.LogicElementRenderer;

public class MenuElement extends Rectangle implements Renderable, Tickable{
	
	private String elementName;
	
	private LogicElementRenderer element;
	private DragHandler dragHandler;
	
	private boolean partOfMenu;
	
	private int[] args;
	
	public MenuElement(DragHandler dragHandler, int x, int y, String elementName, int... args) {
		setBounds(x, y, 60, 60);
		
		this.elementName = elementName;
		this.dragHandler = dragHandler;
		
		this.partOfMenu = true;
		
		this.element = Logic.getLogicElementRenderer(elementName, x, y, args);
		this.args = args;
	}
	
	public void setNewElement() {
		element = Logic.getLogicElementRenderer(elementName, x, y, args);
	}
	
	public void tick() {
		element.tick();
		if (!element.getPosPoint().equals(this.getPosPoint())) {
			partOfMenu = false;
		}
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.BLACK);
//		g.drawRect(x, y, width, height);
//		g.drawString(elementName, x + 20, y + 20);
		element.render(g);
	}
	
	public Point getPosPoint() {
		return new Point(x, y);
	}
	
	public LogicElementRenderer getElement() {
		return element;
	}
	
	public boolean isPartOfMenu() {
		return partOfMenu;
	}
	
	public void setPartOfMenu(boolean partOfMenu) {
		this.partOfMenu = partOfMenu;
	}
}
