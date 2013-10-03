package com.ugiveme.logic.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.ugiveme.logic.LogicElement;

public class Output extends Rectangle{

	private boolean powered;
	
	private LogicElement logicElement;
	
	private int xOffset;
	private int yOffset;
	
	public Output(int xOffset, int yOffset, int width, int height, LogicElement logicElement) {
		setBounds((int) logicElement.x + xOffset, (int) logicElement.y + yOffset, width, height);
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		this.logicElement = logicElement;
		
		this.powered = false;
	}
	
	public void tick() {
		x = (int) logicElement.x + xOffset;
		y = (int) logicElement.y + yOffset;
	}
	
	public void render(Graphics g) {
		if (isPowered()) {
			g.setColor(new Color(220, 220, 50));
		} else {
			g.setColor(Color.GRAY);
		}
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	
	public boolean isPowered() {
		return powered;
	}
	
	public void setPowered(boolean powered) {
		this.powered = powered;
	}
}
