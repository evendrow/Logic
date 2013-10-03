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
		this.linkPoint1 = l1;
		this.linkPoint2 = l2;
		
		this.active = true;
	}
	
	public void tick() {
		if (linkPoint1.isPowered()) {
			linkPoint2.power();
		} else {
			linkPoint2.unPower();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		if (linkPoint1.isPowered()) {
			g.setColor(Color.GREEN);
		}
		
		g.drawLine(linkPoint1.x + (linkPoint1.width/2), linkPoint1.y + (linkPoint1.height/2), linkPoint2.x + (linkPoint2.width/2), linkPoint2.y + (linkPoint2.height/2));
	}
	
	public void destroy() {
		active = false;
	}
	
	public boolean isDestroyed() {
		return !active;
	}
}
