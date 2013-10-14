package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.save.Save;
import com.ugiveme.logicRunner.Game;

public class LogicMenu extends Rectangle{
	
	public static final Rectangle FPS = new Rectangle(0, 0, 150, 40);
	public static final Rectangle EXPORT = new Rectangle(0, 40, 150, 40);
	public static final Rectangle IMPORT = new Rectangle(0, 80, 150, 40);	
	
	private ArrayList<MenuElement> menuElements;
	
	public LogicMenu(DragHandler dragHandler, int x, int y) {
		setBounds(x, y, 150, Game.size.height);
		
		this.menuElements = new ArrayList<MenuElement>();
		
		this.menuElements.add(new MenuElement(dragHandler, 10, 140, "or"));
		this.menuElements.add(new MenuElement(dragHandler, 80, 140, "nor"));
		this.menuElements.add(new MenuElement(dragHandler, 10, 230, "and"));
		this.menuElements.add(new MenuElement(dragHandler, 80, 230, "nand"));
		this.menuElements.add(new MenuElement(dragHandler, 10, 320, "xor"));
		this.menuElements.add(new MenuElement(dragHandler, 80, 320, "xnor"));
		this.menuElements.add(new MenuElement(dragHandler, 20, 410, "not"));
		this.menuElements.add(new MenuElement(dragHandler, 100, 415, "light"));
		this.menuElements.add(new MenuElement(dragHandler, 10, 500, "switch"));
		this.menuElements.add(new MenuElement(dragHandler, 80, 500, "SSD"));

		
	}
	
	public void click(Point p) {
		if (EXPORT.contains(p)) {
			Save.exportSave(LogicHandler.logicElements, LogicHandler.links);
		} else if (IMPORT.contains(p)) {
			String importString = JOptionPane.showInputDialog("Enter Info Here:");
			if (importString != null)
				LogicHandler.addLogic(Save.importSave(importString));
		}
	}
	
	public void tick() {
		for (int i=0;i<menuElements.size();i++) {
			menuElements.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(130, 130, 130));
		g.fillRect(0, 0, width, Game.frame.getHeight());
		
		for (int i=0;i<menuElements.size();i++) {
			//menuElements.get(i).render(g);
		}
		
		g.setColor(new Color(150, 190, 255));
		g.fillRect(FPS.x, FPS.y, width, FPS.height);
		
		g.setColor(new Color(190, 255, 150));
		g.fillRect(EXPORT.x, EXPORT.y, width, EXPORT.height);
		g.setColor(Color.BLACK);
		g.drawString("Export", EXPORT.x + 50, EXPORT.y + 25);
		
		g.setColor(new Color(255, 190, 150));
		g.fillRect(IMPORT.x, IMPORT.y, width, IMPORT.height);
		g.setColor(Color.BLACK);
		g.drawString("Import", IMPORT.x + 50, IMPORT.y + 25);
	}
}
