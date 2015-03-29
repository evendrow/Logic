package com.edwardv.logicRunner;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

	private Point mousePos;
	
	private boolean dragging;
	private boolean startedDragging;
	
	private boolean mouseClicked;
	private Point clickPoint;
	
	public MouseHandler(Game game) {
		mousePos = new Point(0, 0);
		dragging = false;
		startedDragging = false;
		
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		dragging = false;
		mouseClicked = true;
		clickPoint = new Point(e.getX(), e.getY());
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
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

}
