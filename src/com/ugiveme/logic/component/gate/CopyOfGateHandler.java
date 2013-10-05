package com.ugiveme.logic.component.gate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.Output;
import com.ugiveme.logic.component.gate.gateTypes.AO;
import com.ugiveme.logic.component.gate.gateTypes.AndGate;
import com.ugiveme.logic.component.gate.gateTypes.EOrGate;
import com.ugiveme.logic.component.gate.gateTypes.NandGate;
import com.ugiveme.logic.component.gate.gateTypes.NorGate;
import com.ugiveme.logic.component.gate.gateTypes.OrGate;

import com.ugiveme.logic.component.link.Link;

public class CopyOfGateHandler {

	private DragHandler dragHandler;
	
	private ArrayList<Gate> gates;
	private ArrayList<Link> gateLinks;

	private boolean linkStarted;
	private Output linkStartOutput;
	
	public CopyOfGateHandler(DragHandler dragHandler) {
		this.dragHandler = dragHandler;
		
		
		this.gates = new ArrayList<Gate>();
		this.gateLinks = new ArrayList<Link>();
		
		this.linkStarted = false;
		this.linkStartOutput = null;
	}
	
	public void tick() {
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
//							gateLinks.add(newLink);
//							linkStarted = false;
//						}
//					}
//				}
//			}
			
			for (int i=0;i<gates.size();i++) {
				for (int k=0;k<gates.get(i).getInputLength();k++) {
					if (gates.get(i).getInputAtIndex(k).contains(dragHandler.getMouseClickPoint())) {
						if (linkStarted) {
							Link newLink = new Link(linkStartOutput, gates.get(i).getInputAtIndex(k));
							gates.get(i).getInputAtIndex(k).link(newLink);
							gateLinks.add(newLink);
							linkStarted = false;
						} else {
							gates.get(i).getInputAtIndex(k).unLink();
						}
					}
				}
				
				if (gates.get(i).getOutput().contains(dragHandler.getMouseClickPoint())) {
					if (linkStarted) {
						linkStarted = false;
					} else {
						linkStarted = true;
						linkStartOutput = gates.get(i).getOutput();
					}
				}
				
			}
			
			dragHandler.setMouseClicked(false);
		}
		
		for (Gate gate : gates) {
			gate.tick();
		}
		
		for (int i=0;i<gateLinks.size();i++) {
			if (gateLinks.get(i).isDestroyed()) {
				gateLinks.remove(i);
			} else {
				gateLinks.get(i).tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for (Gate gate : gates) {
			gate.render(g);
		}
		
		for (Link l : gateLinks) {
			l.render(g);
		}
		
		if (linkStarted) {
			g.setColor(Color.ORANGE);
			if (linkStartOutput.isPowered()) {
				g.setColor(Color.GREEN);
			}
			g.drawLine(linkStartOutput.x + (linkStartOutput.width/2), linkStartOutput.y + (linkStartOutput.height/2), dragHandler.getMousePos().x, dragHandler.getMousePos().y);
		}
	}
	
	public void addGate(String gateType, int x, int y) {
		if (gateType.equalsIgnoreCase("AO")) {
			gates.add(new AO(dragHandler, x, y));
		} else if (gateType.equalsIgnoreCase("Or")) {
			gates.add(new OrGate(dragHandler, x, y));
		} else if (gateType.equalsIgnoreCase("And")) {
			gates.add(new AndGate(dragHandler, x, y));
		} else if (gateType.equalsIgnoreCase("Eor")) {
			gates.add(new EOrGate(dragHandler, x, y));
		} else if (gateType.equalsIgnoreCase("Nor")) {
			gates.add(new NorGate(dragHandler, x, y));
		} else if (gateType.equalsIgnoreCase("Nand")) {
			gates.add(new NandGate(dragHandler, x, y));
		} else {
			gates.add(new Gate(dragHandler, x, y, "null"));
		}
	}
}
