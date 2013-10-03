package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logicRunner.Game;

public class LogicMenu extends Rectangle{

	private LogicHandler logicScreen;
	
	private ArrayList<MenuElement> menuElements;
	
	public LogicMenu(DragHandler dragHandler, int x, int y, LogicHandler logicScreen) {
		setBounds(x, y, 150, Game.size.height);
		
		this.logicScreen = logicScreen;
		this.menuElements = new ArrayList<MenuElement>();
		
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 10, 10, "or"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 80, 10, "nor"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 10, 130, "and"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 80, 130, "nand"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 10, 250, "eor"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 80, 250, "enor"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 20, 380, "not"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 100, 385, "light"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 10, 500, "switch"));
		this.menuElements.add(new MenuElement(this.logicScreen, dragHandler, 80, 500, "SSD"));

		
	}
	
	public void tick() {
		for (int i=0;i<menuElements.size();i++) {
			menuElements.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		
		for (int i=0;i<menuElements.size();i++) {
			menuElements.get(i).render(g);
		}
	}
}
