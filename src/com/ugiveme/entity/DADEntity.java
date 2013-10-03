package com.ugiveme.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.ugiveme.logicRunner.MouseHandler;

public class DADEntity extends Entity{

	private MouseHandler mouseHandler;
	private Point oldMousePoint;
	private boolean dragging;
	
	public DADEntity(MouseHandler mouseHandler, int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.mouseHandler = mouseHandler;
		this.oldMousePoint = mouseHandler.getMousePos();
		this.dragging = false;
	}

	@Override
	public void tick() {
		if (mouseHandler.isDragging()) {
			if (!dragging) {
				if (mouseHandler.startedDragging() && contains(mouseHandler.getMousePos())) {
					dragging = true;
					mouseHandler.setStartedDragging(false);
				}
			} 
			if (dragging) {
				x += mouseHandler.getMousePos().x - oldMousePoint.x;
				y += mouseHandler.getMousePos().y - oldMousePoint.y;
			}
		} else {
			dragging = false;
		}
		oldMousePoint = mouseHandler.getMousePos();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRect((int) x, (int) y, width, height);
	}
	
	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}
}
