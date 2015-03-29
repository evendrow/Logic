package com.edwardv.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.edwardv.entity.draggable.DragHandler;
import com.edwardv.logic.gui.InputRenderer;
import com.edwardv.logic.gui.LogicElementRenderer;
import com.edwardv.logic.gui.OutputRenderer;
import com.edwardv.logic.gui.component.LinkRenderer;

public class LinkHandler implements Tickable, Renderable{

	private DragHandler dragHandler;
	
	private ArrayList<LogicElementRenderer> logicElements;
	private ArrayList<LinkRenderer> links;
	
	private boolean linkStarted;
	private OutputRenderer linkOutput;
	
	public LinkHandler(DragHandler dragHandler, ArrayList<LogicElementRenderer> logicElements, ArrayList<LinkRenderer> links) {
		this.dragHandler = dragHandler;
		
		this.logicElements = logicElements;
		this.links = links;
		
		linkStarted = false;
		linkOutput = null;
	}

	@Override
	public void tick() {
		if (dragHandler.mouseClicked()) {
			for (int i=0;i<logicElements.size();i++) {
				for (int k=0;k<logicElements.get(i).getInput().length;k++) {
					if (logicElements.get(i).getInput()[k].getRect().contains(dragHandler.getMouseClickPoint())) {
						if (linkStarted) {
							InputRenderer linkInput = logicElements.get(i).getInput()[k];
							LinkRenderer newLink = new LinkRenderer(linkOutput, linkInput);
///							logicElements.get(i).getInputAtIndex(k).link(newLink);
///							linkStartOutput.link(newLink);
							links.add(newLink);
							linkStarted = false;
						} else {
							//logicElements.get(i).getInput()[k].unLink();
							for (int j=0;j<links.size();j++) {
								if (links.get(j).getInput().getLogicElementId() == logicElements.get(i).getId() && links.get(j).getInput().getInputIndex() == logicElements.get(i).getInput()[k].getInputIndex()) {
									links.get(j).destroy();
								}
							}
						}
						dragHandler.setMouseClicked(false);
					}
				}
				
				if (logicElements.get(i).getOutput() != null) {
					for (int k=0;k<logicElements.get(i).getOutput().length;k++) {
						if (logicElements.get(i).getOutput()[k].getRect().contains(dragHandler.getMouseClickPoint())) {
							if (linkStarted) {
								linkStarted = false;
							} else {
								linkStarted = true;
								linkOutput = logicElements.get(i).getOutput()[k];
							}
							dragHandler.setMouseClicked(false);
						}
					}
					
				}
			}
		}
		
		for (int i=0;i<links.size();i++) {
			if (links.get(i).isDestroyed()) {
				links.set(i, null);
				links.remove(i);
			} else {
				links.get(i).tick();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		for (LinkRenderer l : links) {
			l.render(g);
		}
		
		if (linkStarted) {
			g.setColor(Color.ORANGE);
			if (linkOutput.isPowered()) {
				g.setColor(Color.GREEN);
			}
			g.drawLine(linkOutput.getX() + (linkOutput.getWidth()/2), linkOutput.getY() + (linkOutput.getHeight()/2), dragHandler.getMousePos().x, dragHandler.getMousePos().y);
		}
	}
}
