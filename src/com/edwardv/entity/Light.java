package com.edwardv.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.edwardv.logicRunner.MouseHandler;

public class Light extends DADEntity{

	public static final int LIGHTSIZE = 30;
	
	private boolean powered;
	
	public Light(MouseHandler mouseHandler, int x, int y) {
		super(mouseHandler, x, y, Light.LIGHTSIZE, Light.LIGHTSIZE);
		
		powered = false;
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		if (powered) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.GRAY);
		}
		
		g.fillOval((int) x + 3, (int) y + 3, LIGHTSIZE - 6, LIGHTSIZE - 6);
	}
	
	public void power() {
		powered = true;
	}
	
	public void unPower() {
		powered = false;
	}
	
	public boolean isPowered() {
		return powered;
	}
}
