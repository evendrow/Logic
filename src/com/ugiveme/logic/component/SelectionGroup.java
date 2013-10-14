package com.ugiveme.logic.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.LogicHandler;
import com.ugiveme.logic.component.link.Link;
import com.ugiveme.logic.save.SaveObject;

public class SelectionGroup {

	private ArrayList<LogicElement> logicElements;
	private ArrayList<Point> logicElementsPos;
	
	public SelectionGroup(ArrayList<LogicElement> logicElements) {
		this.logicElements = logicElements;
		
		this.logicElementsPos = new ArrayList<Point>();
		for (int i=0;i<logicElements.size();i++) {
			logicElementsPos.add(new Point((int) logicElements.get(i).x, (int) logicElements.get(i).y));
		}
	}
	
	public int size() {
		return logicElements.size();
	}
	
	public SaveObject duplicate() {
		ArrayList<LogicElement> newElements = new ArrayList<LogicElement>();
		ArrayList<Link> newLinks = new ArrayList<Link>();
		
		for (int i=0;i<logicElements.size();i++) {
			newElements.add(LogicHandler.addGate(logicElements.get(i).getType(), (int) logicElements.get(i).x + 20, (int) logicElements.get(i).y + 20));
		}
		
		for (int i=0;i<logicElements.size();i++) {
			if (logicElements.get(i).getInputLength() > 0) {
				for (int k=0;k<logicElements.get(i).getInputLength();k++) {
					if (logicElements.get(i).getInputAtIndex(k).linked()) {
						for (int j=0;j<logicElements.size();j++) {
							if (logicElements.get(i).getInputAtIndex(k).getLink().getOutput() == logicElements.get(j).output) {
								newLinks.add(new Link(newElements.get(j).output, newElements.get(i).getInputAtIndex(k)));
							}
						}
					}
				}
			}
		}System.out.println("done2");
		
		
		return new SaveObject(newElements, newLinks);
	}
	
	
	public void tick() {
		int shiftX = 0;
		int shiftY = 0;
		
		int shiftIndex = 0;
		
		for (int i=0;i<logicElements.size();i++) {
			if (logicElements.get(i).isDestroyed()) {
				logicElements.remove(i);
				for (int k=0;k<logicElements.size();k++) {
					logicElements.get(k).destroy();
				}
				break;
			} else {
				if (logicElementsPos.get(i).x != (int) logicElements.get(i).x) {
					shiftX = (int) logicElements.get(i).x - logicElementsPos.get(i).x;
				}
				if (logicElementsPos.get(i).y != (int) logicElements.get(i).y) {
					shiftY = (int) logicElements.get(i).y - logicElementsPos.get(i).y;
				}
				if (shiftX != 0 || shiftY != 0) {
					shiftIndex = i;
					break;
				}
			}
		}
		
		if (shiftX != 0 || shiftY != 0) {
			//System.out.println("x: " + shiftX + " y: " + shiftY);
			for (int i=0;i<logicElements.size();i++) {
				if (i != shiftIndex) {
					logicElements.get(i).x += shiftX;
					logicElements.get(i).y += shiftY;
				}
				logicElementsPos.set(i, new Point((int) logicElements.get(i).x, (int) logicElements.get(i).y));
			}
			
			shiftX = 0;
			shiftY = 0;
			
			shiftIndex = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i=0;i<logicElements.size();i++) {
			g.drawRect((int) logicElements.get(i).x, (int) logicElements.get(i).y, logicElements.get(i).width, logicElements.get(i).height);
		}
	}
	
	public ArrayList<LogicElement> getLogicElements() {
		return logicElements;
	}

}