package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.SelectionBox;
import com.ugiveme.logic.component.SelectionGroup;

import com.ugiveme.logic.gui.InputRenderer;
import com.ugiveme.logic.gui.LogicElementRenderer;
import com.ugiveme.logic.gui.OutputRenderer;
import com.ugiveme.logic.gui.component.LinkRenderer;
import com.ugiveme.logic.save.SaveObject;
import com.ugiveme.logicRunner.Game;

public class LogicHandler {
	
	private static final Rectangle TRASH = new Rectangle(730, 580, 70, 50);
	private static final Rectangle DUPLICATE = new Rectangle(650, 0, 150, 50);
	
	private static DragHandler dragHandler;
	
	private LogicMenu logicMenu;
	
	public static ArrayList<LogicElementRenderer> logicElements = new ArrayList<LogicElementRenderer>();
	public static ArrayList<LinkRenderer> links = new ArrayList<LinkRenderer>();
	
	public LinkHandler linkHandler;
	
	private boolean linkStarted;
	private OutputRenderer linkOutput;
	
	private SelectionBox sBox;
	private boolean duplicatePressed;
	private ArrayList<LogicElementRenderer> duplicateElements;
	
	private static int nextId = 0;
	
	public LogicHandler(DragHandler dragHandler) {
		LogicHandler.dragHandler = dragHandler;
		
		linkHandler = new LinkHandler(dragHandler, logicElements, links);
		
		this.linkStarted = false;
		this.linkOutput = null;
		
		this.logicMenu = new LogicMenu(dragHandler, logicElements, 0, 0);
		
		this.sBox = new SelectionBox(dragHandler, logicElements);
		this.duplicatePressed = false;
		this.duplicateElements = new ArrayList<LogicElementRenderer>();
	}
	
	public void tick() {
		
		linkHandler.tick();
		if (dragHandler.mouseClicked()) {
//			if (!linkStarted) {
//				for (int i=0;i<gates.size();i++) {
//					if (gates.get(i).outputContainsPoint(mouseHandler.getMouseClickPoint())) {
//						linkStarted = true;
//						linkStartOutput = gates.get(i).getOutput();
//					}
//				}
//			} else {
//				for (int i=0;i<gates.size();i++) {
//					for (int k=0;k<gates.get(i).getInputSize();k++) {
//						if (gates.get(i).inputNumContainsPoint(mouseHandler.getMouseClickPoint(), k)) {
//							Link newLink = new Link(linkStartOutput, gates.get(i).getInput(k));
//							gates.get(i).getInput(k).link(newLink);
//							links.add(newLink);
//							linkStarted = false;
//						}
//					}
//				}
//			}
			
//			boolean IOClicked = false;
			for (int i=0;i<logicElements.size();i++) {
//				for (int k=0;k<logicElements.get(i).getInput().length;k++) {
//					if (logicElements.get(i).getInput()[k].getRect().contains(dragHandler.getMouseClickPoint())) {
//						if (linkStarted) {
//							InputRenderer linkInput = logicElements.get(i).getInput()[k];
//							LinkRenderer newLink = new LinkRenderer(linkOutput, linkInput);
/////							logicElements.get(i).getInputAtIndex(k).link(newLink);
/////							linkStartOutput.link(newLink);
//							links.add(newLink);
//							linkStarted = false;
//						} else {
//							//logicElements.get(i).getInput()[k].unLink();
//						}
//					}
//				}
//				
//				if (logicElements.get(i).getOutput() != null) {
//					for (int k=0;k<logicElements.get(i).getOutput().length;k++) {
//						if (logicElements.get(i).getOutput()[k].getRect().contains(dragHandler.getMouseClickPoint())) {
//							if (linkStarted) {
//								linkStarted = false;
//							} else {
//								linkStarted = true;
//								linkOutput = logicElements.get(i).getOutput()[k];
//							}
//						}
//					}
//					
//				}
				logicElements.get(i).click(dragHandler.getMouseClickPoint());
			}
			
//			if (DUPLICATE.contains(dragHandler.getMouseClickPoint())) {
//				System.out.println("hi");
//				SaveObject toAdd = sBox.duplicate();
//				if (toAdd != null) {
//					duplicateElements = toAdd.getLogicElements();
//					duplicatePressed = true;
//					for (int i=0;i<toAdd.getLogicElements().size();i++) {
////						logicElements.add(toAdd.get(i));
//					}
//					for (int i=0;i<toAdd.getLinks().size();i++) {
//						links.add((Link) toAdd.getLinks().get(i));
//					}
//				}
//			}
			
			logicMenu.click(dragHandler.getMouseClickPoint());
			
			dragHandler.setMouseClicked(false);
		}
		
		for (int i=0;i<logicElements.size();i++) {
			logicElements.get(i).tick();
			
			if (logicElements.get(i).getRect().intersects(TRASH) || logicElements.get(i).isDestroyed()) {
				logicElements.set(i, null);
				logicElements.remove(i);
			}
		}
		logicMenu.tick();
		
		sBox.tick();
		if (duplicatePressed) {
			//sBox.selectionGroup = new SelectionGroup(duplicateElements);
			duplicatePressed = false;
		}
		
		TRASH.x = Game.frame.getWidth() - TRASH.width;
		DUPLICATE.x = Game.frame.getWidth() - DUPLICATE.width;
	}
	
	public synchronized void render(Graphics g) {
		logicMenu.render(g);
		linkHandler.render(g);
		
		for (int i=logicElements.size()-1;i>-1;i--) {
			logicElements.get(i).render(g);
		}
		
		//g.setColor(Color.BLACK);
		//g.drawString("Entitities: " + logicElements.size(), 500, 10);
		
		g.setColor(Color.RED);
		g.fillRect(TRASH.x, TRASH.y, TRASH.width, TRASH.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(TRASH.x, TRASH.y, TRASH.width, TRASH.height);
		g.drawString("Trash", TRASH.x+20, TRASH.y+30);
		
		g.setColor(new Color(150, 150, 255));
		g.fillRect(DUPLICATE.x, DUPLICATE.y, DUPLICATE.width, DUPLICATE.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(DUPLICATE.x, DUPLICATE.y, DUPLICATE.width, DUPLICATE.height);
		g.drawString("Dis button no work", DUPLICATE.x+20, DUPLICATE.y+30);
		
		sBox.render(g);
	}
	
//	public static LogicElement addGate(String elementType, int x, int y) {
//		LogicElement newElement = getGate(elementType, x, y);
//		
//		//logicElements.add(newElement);
//		return newElement;
//	}
//	
//	public static LogicElement getGate(String gateType, int x, int y) {
//		LogicElement newGate = null;
////		if (gateType.equalsIgnoreCase("AO")) {
////			newGate = new AO(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Or")) {
////			newGate = new OrGate(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("And")) {
////			newGate = new AndGate(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Xor")) {
////			newGate = new XOrGate(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("XNor")) {
////			newGate = new XNorGate(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Nor")) {
////			newGate = new NorGate(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Nand")) {
////			newGate = new NandGate(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Switch")) {
////			newGate = new OnOffSwitch(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Light")) {
////			newGate = new Light(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Not")) {
////			newGate = new Not(nextId++, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("SSD")) {
////			newGate = new SSD(nextId++, dragHandler, x, y);
////		} else {
////			newGate = new LogicElement(nextId++, dragHandler, x, y, 100, 100, "null");
////		}
//		
//		return newGate;
//	}
//	
//	public static LogicElement getGate(int id, String gateType, int x, int y) {
//		LogicElement newGate = null;
////		if (gateType.equalsIgnoreCase("AO")) {
////			newGate = new AO(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Or")) {
////			newGate = new OrGate(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("And")) {
////			newGate = new AndGate(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Xor")) {
////			newGate = new XOrGate(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("XNor")) {
////			newGate = new XNorGate(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Nor")) {
////			newGate = new NorGate(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Nand")) {
////			newGate = new NandGate(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Switch")) {
////			newGate = new OnOffSwitch(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Light")) {
////			newGate = new Light(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("Not")) {
////			newGate = new Not(id, dragHandler, x, y);
////		} else if (gateType.equalsIgnoreCase("SSD")) {
////			newGate = new SSD(id, dragHandler, x, y);
////		} else {
////			newGate = new LogicElement(id, dragHandler, x, y, 100, 100, "null");
////		}
//		
//		return newGate;
//	}
	
	public static void addLogic(SaveObject logic) {
		if (logic != null) {
			if (logic.getLogicElements().size() > 0) {
				for (int i=0;i<logic.getLogicElements().size();i++) {
					logic.getLogicElements().get(i).setId(nextId++);
					logicElements.add(logic.getLogicElements().get(i));
				}
			}
			if (logic.getLinks().size() > 0) {
				for (int i=0;i<logic.getLinks().size();i++) {
					//links.add(logic.getLinks().get(i));
				}
			}
		}
	}
}
