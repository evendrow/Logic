package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.Output;
import com.ugiveme.logic.component.gate.gateTypes.AO;
import com.ugiveme.logic.component.gate.gateTypes.AndGate;
import com.ugiveme.logic.component.gate.gateTypes.ENor;
import com.ugiveme.logic.component.gate.gateTypes.EOrGate;
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

public class LogicHandler {
	
	private static final Rectangle TRASH = new Rectangle(730, 530, 70, 50);
	
	private DragHandler dragHandler;
	
	private LogicMenu logicMenu;
	
	private ArrayList<LogicElement> logicElements;
	private ArrayList<Link> links;

	private boolean linkStarted;
	private Output linkStartOutput;
	
	public LogicHandler(DragHandler dragHandler) {
		this.dragHandler = dragHandler;
		
		this.logicElements = new ArrayList<LogicElement>();
		this.links = new ArrayList<Link>();
		
		this.linkStarted = false;
		this.linkStartOutput = null;
		
		this.logicMenu = new LogicMenu(dragHandler, 0, 0, this);
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
							logicElements.get(i).getInputAtIndex(k).link(newLink);
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
			}
		}
		
		for (int i=0;i<links.size();i++) {
			if (links.get(i).isDestroyed()) {
				links.remove(i);
			} else {
				links.get(i).tick();
			}
		}
	}
	
	public synchronized void render(Graphics g) {
		logicMenu.render(g);
		
		for (LogicElement logicElement : logicElements) {
			logicElement.render(g);
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
	}
	
	public LogicElement addGate(String gateType, int x, int y) {
		LogicElement newGate = new LogicElement(dragHandler, x, y, 100, 100, "null");
		if (gateType.equalsIgnoreCase("AO")) {
			newGate = new AO(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Or")) {
			newGate = new OrGate(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("And")) {
			newGate = new AndGate(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Xor")) {
			newGate = new XOrGate(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("XNor")) {
			newGate = new XNorGate(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Nor")) {
			newGate = new NorGate(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Nand")) {
			newGate = new NandGate(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Switch")) {
			newGate = new OnOffSwitch(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Light")) {
			newGate = new Light(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("Not")) {
			newGate = new Not(dragHandler, x, y);
		} else if (gateType.equalsIgnoreCase("SSD")) {
			newGate = new SSD(dragHandler, x, y);
		}//else {
//			newGate = new LogicElement(dragHandler, x, y, 100, 100, "null");
//		}
		
		logicElements.add(newGate);
		return newGate;
	}
}
