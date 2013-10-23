package com.ugiveme.logic.gui.component;

import java.awt.Color;
import java.awt.Graphics;

import com.ugiveme.logic.Renderable;
import com.ugiveme.logic.Tickable;
import com.ugiveme.logic.component.Link;
import com.ugiveme.logic.gui.InputRenderer;
import com.ugiveme.logic.gui.OutputRenderer;

public class LinkRenderer implements Tickable, Renderable{

	private Link link;
	
	private OutputRenderer linkPoint1;
	private InputRenderer linkPoint2;
	
	private boolean destroyed;
	
	public LinkRenderer(OutputRenderer linkPoint1, InputRenderer linkPoint2) {
		this.linkPoint1 = linkPoint1;
		this.linkPoint2 = linkPoint2;
		
		this.link = new Link(linkPoint1.getOutput(), linkPoint2.getInput(), linkPoint1.getOutputIndex(), linkPoint2.getInputIndex());
	
		this.destroyed = false;
	}

	@Override
	public void tick() {
		link.tick();
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		if (linkPoint1.isPowered()) {
			g.setColor(Color.GREEN);
		}
		
		//g.drawLine(linkPoint1.x + (linkPoint1.width/2), linkPoint1.y + (linkPoint1.height/2), linkPoint2.x + (linkPoint2.width/2), linkPoint2.y + (linkPoint2.height/2));
		
		g.drawLine(linkPoint1.getX() + (linkPoint1.getWidth()/2), linkPoint1.getY() + (linkPoint1.getHeight()/2), linkPoint1.getX() + (linkPoint1.getWidth()/2) + ((linkPoint2.getX() - linkPoint1.getX())/2), linkPoint1.getY() + (linkPoint1.getHeight()/2));
		g.drawLine(linkPoint1.getX() + (linkPoint1.getWidth()/2) + ((linkPoint2.getX() - linkPoint1.getX())/2), linkPoint1.getY() + (linkPoint1.getHeight()/2), linkPoint1.getX() + (linkPoint1.getWidth()/2) + ((linkPoint2.getX() - linkPoint1.getX())/2), (linkPoint2.getHeight()/2) + linkPoint2.getY());
		g.drawLine(linkPoint1.getX() + (linkPoint1.getWidth()/2) + ((linkPoint2.getX() - linkPoint1.getX())/2), linkPoint2.getY() + (linkPoint2.getHeight()/2), linkPoint2.getX() + (linkPoint2.getWidth()/2), linkPoint2.getY() + (linkPoint2.getHeight()/2));
	}
	
	public void destroy() {
		this.destroyed = true;
		linkPoint2.getInput().setBit(false, linkPoint2.getInputIndex());
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	public OutputRenderer getOutput() {
		return linkPoint1;
	}
	
	public InputRenderer getInput() {
		return linkPoint2;
	}
}
