package com.ugiveme.logic.component.link;

import java.awt.Point;

import com.ugiveme.logic.component.gate.Gate;

public class LinkPoint {

	private Gate gate;
	private Point point;
	
	private Point oldGatePos;
	
	private boolean powered;
	
	public LinkPoint(Gate gate, Point point) {
		this.gate = gate;
		this.point = point;
		this.oldGatePos = new Point((int) gate.x, (int) gate.y);
		
		this.powered = false;
	}
	
	public LinkPoint() {
		this.gate = null;
		this.point = null;
	}
	
	public void tick() {
		if (!gate.getPosPoint().equals(oldGatePos)) {
			point = new Point(point.x + (int) gate.x - oldGatePos.x, point.y + (int)   gate.y - oldGatePos.y);
			oldGatePos = gate.getPosPoint();
		}
		
		if (gate.isPowered()) {
			power();
		} else {
			unPower();
		}
	}
	
	public Gate getGate() {
		return gate;
	}
	
	public Point getPoint() {
		return point;
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
	
	public int getX() {
		return point.x;
	}
	
	public int getY() {
		return point.y;
	}
}
