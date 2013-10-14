package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.Output;
import com.ugiveme.logic.component.SelectionBox;
import com.ugiveme.logic.component.SelectionGroup;
import com.ugiveme.logic.component.gate.gateTypes.AO;
import com.ugiveme.logic.component.gate.gateTypes.AndGate;
import com.ugiveme.logic.component.gate.gateTypes.NandGate;
import com.ugiveme.logic.component.gate.gateTypes.NorGate;
import com.ugiveme.logic.component.gate.gateTypes.OrGate;
import com.ugiveme.logic.component.gate.gateTypes.XNorGate;
import com.ugiveme.logic.component.gate.gateTypes.XOrGate;

import com.ugiveme.logic.component.link.Link;
import com.ugiveme.logic.component.misc.Light;
import com.ugiveme.logic.component.misc.Not;
import com.ugiveme.logic.component.misc.OnOffSwitch;
import com.ugiveme.logic.component.misc.SSD;
import com.ugiveme.logic.save.SaveObject;
import com.ugiveme.logicRunner.Game;

public class LogicHandler {
	
	private static final Rectangle TRASH = new Rectangle(730, 530, 70, 50);
	private static final Rectangle DUPLICATE = new Rectangle(650, 0, 150, 50);
	
	private static DragHandler dragHandler;
	
	private LogicMenu logicMenu;
	
	public static ArrayList<LogicElement> logicElements = new ArrayList<LogicElement>();
	public static ArrayList<LogicElement> menuElements = new ArrayList<LogicElement>();
	public static ArrayList<Link> links = new ArrayList<Link>();

	private boolean linkStarted;
	private Output linkStartOutput;
	
	private SelectionBox sBox;
	private boolean duplicatePressed;
	private ArrayList<LogicElement> duplicateElements;
	
	private static int nextId = 0;
	
	public LogicHandler(DragHandler dragHandler) {
		LogicHandler.dragHandler = dragHandler;
		
		this.linkStarted = false;
		this.linkStartOutput = null;
		
		this.logicMenu = new LogicMenu(dragHandler, 0, 0);
		
		this.sBox = new SelectionBox(dragHandler, logicElements);
		this.duplicatePressed = false;
		this.duplicateElements = new ArrayList<LogicElement>();
	}
	
	public void tick() {
		logicMenu.tick();
		
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
				for (int k=0;k<logicElements.get(i).getInputLength();k++) {
					if (logicElements.get(i).getInputAtIndex(k).contains(dragHandler.getMouseClickPoint())) {
						if (linkStarted) {
							Link newLink = new Link(linkStartOutput, logicElements.get(i).getInputAtIndex(k));
//							logicElements.get(i).getInputAtIndex(k).link(newLink);
//							linkStartOutput.link(newLink);
							links.add(newLink);
							linkStarted = false;
						} else {
							logicElements.get(i).getInputAtIndex(k).unLink();
						}
					}
				}
				
				if (logicElements.get(i).getOutput() != null) {
					if (logicElements.get(i).getOutput().contains(dragHandler.getMouseClickPoint())) {
						if (linkStarted) {
							linkStarted = false;
						} else {
							linkStarted = true;
							linkStartOutput = logicElements.get(i).getOutput();
						}
					}
				}
				logicElements.get(i).click(dragHandler.getMouseClickPoint());
			}
			
			if (DUPLICATE.contains(dragHandler.getMouseClickPoint())) {
				System.out.println("hi");
				SaveObject toAdd = sBox.duplicate();
				if (toAdd != null) {
					duplicateElements = toAdd.getLogicElements();
					duplicatePressed = true;
					for (int i=0;i<toAdd.getLogicElements().size();i++) {
//						logicElements.add(toAdd.get(i));
					}
					for (int i=0;i<toAdd.getLinks().size();i++) {
						links.add((Link) toAdd.getLinks().get(i));
					}
				}
			}
			
			logicMenu.click(dragHandler.getMouseClickPoint());
			
			dragHandler.setMouseClicked(false);
		}
		
		for (int i=0;i<logicElements.size();i++) {
			logicElements.get(i).tick();
//			if (!logicElements.get(i).isPartOfMenu()) {
//				if (logicElements.get(i).x < logicMenu.x + logicMenu.width) {
//					logicElements.remove(i);
//				}
//			}
			if (logicElements.get(i).getRect().intersects(TRASH)) {
				logicElements.get(i).destroy();
				logicElements.remove(i);
			} else if (logicElements.get(i).isDestroyed()) {
				logicElements.remove(i);
			}
		}
		
		for (int i=0;i<menuElements.size();i++) {
			menuElements.get(i).tick();
			if (!menuElements.get(i).isPartOfMenu()) {
				logicElements.add(menuElements.get(i));
				menuElements.remove(i);
			}
		}
		
		for (int i=0;i<links.size();i++) {
			if (links.get(i).isDestroyed()) {
				links.remove(i);
			} else {
				links.get(i).tick();
			}
		}
		
		sBox.tick();
		if (duplicatePressed) {
			sBox.selectionGroup = new SelectionGroup(duplicateElements);
			duplicatePressed = false;
		}
		
		TRASH.x = Game.frame.getWidth() - TRASH.width;
		DUPLICATE.x = Game.frame.getWidth() - DUPLICATE.width;
	}
	
	public synchronized void render(Graphics g) {
		logicMenu.render(g);
		
		for (int i=logicElements.size()-1;i>-1;i--) {
			logicElements.get(i).render(g);
		}
		
		for (LogicElement e : menuElements) {
			e.render(g);
		}
		
		for (Link l : links) {
			l.render(g);
		}
		
		if (linkStarted) {
			g.setColor(Color.ORANGE);
			if (linkStartOutput.isPowered()) {
				g.setColor(Color.GREEN);
			}
			g.drawLine(linkStartOutput.x + (linkStartOutput.width/2), linkStartOutput.y + (linkStartOutput.height/2), dragHandler.getMousePos().x, dragHandler.getMousePos().y);
		}
		
		g.setColor(Color.BLACK);
		g.drawString("Entitities: " + logicElements.size(), 500, 10);
		
		g.setColor(Color.RED);
		g.fillRect(TRASH.x, TRASH.y, TRASH.width, TRASH.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(TRASH.x, TRASH.y, TRASH.width, TRASH.height);
		g.drawString("Trash", TRASH.x+20, TRASH.y+30);
		
		g.setColor(new Color(150, 150, 255));
		g.fillRect(DUPLICATE.x, DUPLICATE.y, DUPLICATE.width, DUPLICATE.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(DUPLICATE.x, DUPLICATE.y, DUPLICATE.width, DUPLICATE.height);
		g.drawString("Press to Duplicate", DUPLICATE.x+20, DUPLICATE.y+30);
		
		sBox.render(g);
	}
	
	public static LogicElement addGate(String elementType, int x, int y) {
		LogicElement newElement = getGate(elementType, x, y);
		
		logicElements.add(newElement);
		return newElement;
	}
	
	public static LogicElement addMenuElement(String elementType, int x, int y) {
		LogicElement newElement = getGate(elementType, x, y);
		newElement.setPartOfMenu(true);
		
		menuElements.add(newElement);
		return newElement;
	}
	
	public static LogicElement getGate(String gateType, int x, int y) {
		LogicElement newGate;
		if (gateType.equalsIgnoreCase("AO")) {
			newGate = new AO(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Or")) {
			newGate = new OrGate(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("And")) {
			newGate = new AndGate(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Xor")) {
			newGate = new XOrGate(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("XNor")) {
			newGate = new XNorGate(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Nor")) {
			newGate = new NorGate(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Nand")) {
			newGate = new NandGate(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Switch")) {
			newGate = new OnOffSwitch(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Light")) {
			newGate = new Light(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Not")) {
			newGate = new Not(nextId++, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("SSD")) {
			newGate = new SSD(nextId++, dragHandler, x, y);
		} else {
			newGate = new LogicElement(nextId++, dragHandler, x, y, 100, 100, "null");
		}
		
		return newGate;
	}
	
	public static LogicElement getGate(int id, String gateType, int x, int y) {
		LogicElement newGate;
		if (gateType.equalsIgnoreCase("AO")) {
			newGate = new AO(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Or")) {
			newGate = new OrGate(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("And")) {
			newGate = new AndGate(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Xor")) {
			newGate = new XOrGate(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("XNor")) {
			newGate = new XNorGate(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Nor")) {
			newGate = new NorGate(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Nand")) {
			newGate = new NandGate(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Switch")) {
			newGate = new OnOffSwitch(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Light")) {
			newGate = new Light(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Not")) {
			newGate = new Not(id, dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("SSD")) {
			newGate = new SSD(id, dragHandler, x, y);
		} else {
			newGate = new LogicElement(id, dragHandler, x, y, 100, 100, "null");
		}
		
		return newGate;
	}
	
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
					links.add(logic.getLinks().get(i));
				}
			}
		}
	}
}
