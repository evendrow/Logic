package com.ugiveme.entity.draggable;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.ugiveme.logicRunner.Game;

public class DragHandler implements MouseListener, MouseMotionListener{

	private Point mousePos;
	
	private boolean dragging;
	private boolean startedDragging;
	
	private boolean mouseClicked;
	private Point clickPoint;
	
	private boolean updated;
	
	public DragHandler(Game game) {
		mousePos = new Point(0, 0);
		clickPoint = new Point(0, 0);
		
		dragging = false;
		startedDragging = false;
		
		updated = false;
		
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos = new Point(e.getX(), e.getY());
		updated = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = new Point(e.getX(), e.getY());
		updated = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		dragging = false;
		mouseClicked = true;
		clickPoint = new Point(e.getX(), e.getY());
		updated = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		dragging = true;
		startedDragging = true;
		mouseClicked = false;
		updated = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
		updated = true;
	}
	
	
	public Point getMousePos() {
		return mousePos;
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	public boolean startedDragging() {
		return startedDragging;
	}
	
	public void setStartedDragging(boolean s) {
		startedDragging = s;
	}
	
	public boolean mouseClicked() {
		return mouseClicked;
	}
	
	public void setMouseClicked(boolean b) {
		mouseClicked = b;
	}
	
	public Point getMouseClickPoint() {
		return clickPoint;
	}
	
	public boolean isUpdated() {
		return updated;
	}
	
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

}
