package com.ugiveme.logic.component.link;

import java.awt.Color;
import java.awt.Graphics;

import com.ugiveme.logic.component.Input;
import com.ugiveme.logic.component.Output;

public class Link {
	
	private boolean active;
	
	private Output linkPoint1;
	private Input linkPoint2;
	
	public Link(Output l1, Input l2) {
		l1.link(this);
		l2.link(this);
		
		this.linkPoint1 = l1;
		this.linkPoint2 = l2;
		
		this.active = true;
	}
	
	public void tick() {
		if (active) {
			if (linkPoint1.isPowered()) {
				linkPoint2.power();
			} else {
				linkPoint2.unPower();
			}
		}
	}
	
	public void render(Graphics g) {
		if (active) {
			g.setColor(Color.RED);
			if (linkPoint1.isPowered()) {
				g.setColor(Color.GREEN);
			}
			
			//g.drawLine(linkPoint1.x + (linkPoint1.width/2), linkPoint1.y + (linkPoint1.height/2), linkPoint2.x + (linkPoint2.width/2), linkPoint2.y + (linkPoint2.height/2));
			
			g.drawLine(linkPoint1.x + (linkPoint1.width/2), linkPoint1.y + (linkPoint1.height/2), linkPoint1.x + (linkPoint1.width/2) + ((linkPoint2.x - linkPoint1.x)/2), linkPoint1.y + (linkPoint1.height/2));
			g.drawLine(linkPoint1.x + (linkPoint1.width/2) + ((linkPoint2.x - linkPoint1.x)/2), linkPoint1.y + (linkPoint1.height/2), linkPoint1.x + (linkPoint1.width/2) + ((linkPoint2.x - linkPoint1.x)/2), linkPoint1.y + (linkPoint1.height/2) + linkPoint2.y - linkPoint1.y);
			g.drawLine(linkPoint1.x + (linkPoint1.width/2) + ((linkPoint2.x - linkPoint1.x)/2), linkPoint2.y + (linkPoint2.height/2), linkPoint2.x + (linkPoint2.width/2), linkPoint2.y + (linkPoint2.height/2));
	
		}
	}
	
	public Output getOutput() {
		return linkPoint1;
	}
	
	public Input getInput() {
		return linkPoint2;
	}
	
	public void destroy() {
		active = false;
	}
	
	public boolean isDestroyed() {
		return !active;
	}
}
