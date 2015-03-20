package com.ugiveme.entity.draggable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.ugiveme.entity.Entity;
import com.ugiveme.entity.draggable.DragHandler;

public class DraggableEntity extends Entity{

	public static boolean draggingEntity = false;
	
	private DragHandler dragHandler;
	private Point oldMousePoint;
	private boolean dragging;
	
	private boolean draggable;
	
	public DraggableEntity(DragHandler dragHandler, int x, int y, int width, int height) {
		super(x, y, width, height);
		
		if (dragHandler != null) {
			this.dragHandler = dragHandler;
		} else {
			this.dragHandler = null;
			setDraggable(false);
		}
		this.oldMousePoint = dragHandler.getMousePos();
		this.dragging = false;
		
		this.draggable = true;
	}
	
	public DraggableEntity(DragHandler dragHandler, int x, int y, int width, int height, boolean isDraggable) {
		super(x, y, width, height);
		
		if (dragHandler != null) {
			this.dragHandler = dragHandler;
		} else {
			this.dragHandler = null;
			setDraggable(false);
		}
		this.oldMousePoint = dragHandler.getMousePos();
		this.dragging = false;
		
		this.draggable = isDraggable;
	}

	@Override
	public void tick() {
		if (draggable) {
			if (dragHandler.isDragging()) {
				if (!dragging) {
					if (dragHandler.startedDragging() && contains(dragHandler.getMousePos())) {
						dragging = true;
						dragHandler.setStartedDragging(false);
						draggingEntity = true;
					}
				} 
				if (dragging) {
					x += dragHandler.getMousePos().x - oldMousePoint.x;
					y += dragHandler.getMousePos().y - oldMousePoint.y;
				}
			} else {
				dragging = false;
				draggingEntity = false;
			}
			oldMousePoint = dragHandler.getMousePos();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRect((int) x, (int) y, width, height);
	}
	
	public DragHandler getDragHandler() {
		return dragHandler;
	}
	
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}
}
