package com.ugiveme.logic.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.link.Link;

public class Input extends Rectangle{

	private boolean linked;
	private Link link;
	
	private boolean powered;
	
	private LogicElement logicElement;
	
	private int xOffset;
	private int yOffset;
	
	public Input(int xOffset, int yOffset, int width, int height, LogicElement logicElement) {
		setBounds((int) logicElement.x + xOffset, (int) logicElement.y + yOffset, width, height);
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		linked = false;
		link = null;
		
		powered = false;
		
		this.logicElement = logicElement;
	}
	
	public void destroy() {
		if (link != null) {
			link.destroy();
		}
	}
	
	public void tick() {
		x = (int) logicElement.x + xOffset;
		y = (int) logicElement.y + yOffset;
		
		if (link != null) {
			if (link.isDestroyed()) {
				unLink();
			}
		}
	}
	
	public void render(Graphics g) {
		if (isPowered()) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(x, y + (width/2) - 1, width, 3);
		
//		g.fillRect(x, y, width, height);
//		
//		g.setColor(Color.BLACK);
//		g.drawRect(x, y, width, height);
	}
	
	public void link(Link newLink) {
		this.linked = true;
		
		if (this.link != null) {
			this.link.destroy();
		}
		this.link = newLink;
	}
	
	public void unLink() {
		if (link != null) {
			link.destroy();
		}
		powered = false;
	}
	
	public boolean linked() {
		return linked;
	}
	
	public Link getLink() {
		if (link != null) {
			return link;
		}
		return null;
	}
	
	public boolean isPowered() {
		return powered;
	}
	
	public void power() {
		powered = true;
	}
	
	public void unPower() {
		powered = false;
	}
}
