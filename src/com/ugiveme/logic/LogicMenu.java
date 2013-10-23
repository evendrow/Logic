package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.gui.LogicElementRenderer;
import com.ugiveme.logic.save.Save;
import com.ugiveme.logicRunner.Game;

public class LogicMenu extends Rectangle implements Renderable{
	
	public static final Rectangle FPS = new Rectangle(0, 0, 200, 40);
	public static final Rectangle EXPORT = new Rectangle(0, 40, 200, 40);
	public static final Rectangle IMPORT = new Rectangle(0, 80, 200, 40);	
	
	private ArrayList<MenuElement> menuElements;
	
	private ArrayList<LogicElementRenderer> logicElements;
	
	public LogicMenu(DragHandler dragHandler, ArrayList<LogicElementRenderer> logicElements, int x, int y) {
		setBounds(x, y, 200, Game.size.height);
		
		this.logicElements = logicElements;
		
		this.menuElements = new ArrayList<MenuElement>();
		
		this.menuElements.add(new MenuElement(dragHandler, 20, 140, "or", 2));
		this.menuElements.add(new MenuElement(dragHandler, 110, 140, "nor", 2));
		this.menuElements.add(new MenuElement(dragHandler, 20, 230, "and", 2));
		this.menuElements.add(new MenuElement(dragHandler, 110, 230, "nand", 2));
		this.menuElements.add(new MenuElement(dragHandler, 10, 320, "xor", 2));
		this.menuElements.add(new MenuElement(dragHandler, 110, 320, "xnor", 2));
		this.menuElements.add(new MenuElement(dragHandler, 20, 410, "not", 2));
		this.menuElements.add(new MenuElement(dragHandler, 140, 430, "light"));
		this.menuElements.add(new MenuElement(dragHandler, 20, 500, "switch"));
		this.menuElements.add(new MenuElement(dragHandler, 90, 500, "LEDMatrix", 5, 7, 0));
		
	}
	
	public void click(Point p) {
		if (EXPORT.contains(p)) {
			//Save.exportSave(LogicHandler.logicElements, LogicHandler.links);
		} else if (IMPORT.contains(p)) {
			String importString = JOptionPane.showInputDialog("Enter Info Here:");
			if (importString != null)
				LogicHandler.addLogic(Save.importSave(importString));
		}
	}
	
	public void tick() {
		for (int i=0;i<menuElements.size();i++) {
			menuElements.get(i).tick();
			if (!menuElements.get(i).isPartOfMenu()) {
				logicElements.add(menuElements.get(i).getElement());
				menuElements.get(i).setPartOfMenu(true);
				menuElements.get(i).setNewElement();
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(130, 130, 130));
		g.fillRect(0, 0, width, Game.frame.getHeight());
		
		for (int i=0;i<menuElements.size();i++) {
			menuElements.get(i).render(g);
		}
		
		g.setColor(new Color(150, 190, 255));
		g.fillRect(FPS.x, FPS.y, width, FPS.height);
		
		g.setColor(new Color(190, 255, 150));
		g.fillRect(EXPORT.x, EXPORT.y, width, EXPORT.height);
		
		g.setColor(new Color(255, 190, 150));
		g.fillRect(IMPORT.x, IMPORT.y, width, IMPORT.height);
		
		g.setColor(Color.BLACK);
		g.drawString("Export", EXPORT.x + 75, EXPORT.y + 25);
		g.drawString("Import", IMPORT.x + 75, IMPORT.y + 25);
		g.drawLine(FPS.width/2, FPS.y, FPS.width/2, FPS.height-1);
		g.drawString("Entities: " + LogicHandler.logicElements.size(), 120, 25);
	}
}
