package com.ugiveme.logic.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.link.Link;

public class Output extends Rectangle{

	private ArrayList<Link> links;
	
	private boolean powered;
	
	private LogicElement logicElement;
	
	private int xOffset;
	private int yOffset;
	
	public Output(int xOffset, int yOffset, int width, int height, LogicElement logicElement) {
		setBounds((int) logicElement.x + xOffset, (int) logicElement.y + yOffset, width, height);
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		this.logicElement = logicElement;
		
		this.links = new ArrayList<Link>();
		
		this.powered = false;
		
	}
	
	public void destroy() {
		for (int i=0;i<links.size();i++) {
			links.get(i).destroy();
		}
		
	}
	
	public void tick() {
		x = (int) logicElement.x + xOffset;
		y = (int) logicElement.y + yOffset;
		
		for (int i=0;i<links.size();i++) {
			if (links.get(i).isDestroyed()) {
				links.remove(i);
			}
		}
	}
	
	public void render(Graphics g) {
		if (isPowered()) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(x, y + (width/2) - 1, width, 4);
//		g.fillRect(x, y, width, height);
//		
//		g.setColor(Color.BLACK);
//		g.drawRect(x, y, width, height);
	}
	
	public void link(Link link) {
		links.add(link);
	}
	
	public boolean isPowered() {
		return powered;
	}
	
	public void setPowered(boolean powered) {
		this.powered = powered;
	}
}
