package com.ugiveme.entity;

import java.awt.*;

public abstract class Entity {
	public float x;
	public float y;
	public int width;
	public int height;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public boolean contains(Point p) {
		return getRect().contains(p);
	}
	
	public boolean contains(Rectangle r) {
		return getRect().contains(r);
	}
	
	public boolean intersects(Rectangle r) {
		return getRect().intersects(r);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public Point getPosPoint() {
		return new Point((int) x, (int) y);
	}
	
//	public float getX() {
//		return x;
//	}
//	
//	public float getY() {
//		return y;
//	}
//	
//	public int getWidth() {
//		return width;
//	}
//	
//	public int getHeight() {
//		return height;
//	}
}
