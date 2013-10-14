package com.ugiveme.logic.component.misc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.Output;

public class OnOffSwitch extends LogicElement{

	public OnOffSwitch(int id, DragHandler dragHandler, int x, int y) {
		super(id, dragHandler, x, y, 30, 60, "OOSwitch");
		
		output = new Output(width, (height - IOSIZE)/2, IOSIZE, IOSIZE, this, 0);
	}
	
	public void click(Point p) {
		if (contains(p)) {
			togglePower();
		}
	}
	
	public void tick() {
		super.tick();
		
		output.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(80, 80, 80));
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.GREEN);
		g.fillRect((int) x + 4, (int) y + 8, width - 8, (height/2) - 8);
		g.setColor(Color.RED);
		g.fillRect((int) x + 4, (int) y + (height/2), width - 8, (height/2) - 8);
		
		g.setColor(Color.GRAY);
		g.fillRect((int) x + 4, (int) y + (isPowered() ? (height/2) : 8), width - 8, (height/2) - 8);
		
		
		output.render(g);
	}
}
