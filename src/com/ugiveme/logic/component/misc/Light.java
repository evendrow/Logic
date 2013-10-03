package com.ugiveme.logic.component.misc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.Input;

public class Light extends LogicElement{
	
	public Light(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, 40, 40, "Light");
		
		input = new Input[] {
			new Input(-IOSIZE, (height - IOSIZE)/2, IOSIZE, IOSIZE, this)
		};
	}
	
	public void tick() {
		super.tick();
		
		input[0].tick();
		
		if (input[0].isPowered()) {
			setPowered(true);
		} else {
			setPowered(false);
		}
	}
	
	public void render(Graphics g) {
		if (isPowered()) {
			((Graphics2D) g).setPaint(new RadialGradientPaint(x + (width/2), y + (height/2), width, new float[] {0.0f, 0.5f}, new Color[] {new Color(255, 255, 150), new Color(255, 200, 40)}, CycleMethod.NO_CYCLE));
		} else {
			((Graphics2D) g).setPaint(new RadialGradientPaint(x + (width/2), y + (height/2), width, new float[] {0.0f, 0.5f}, new Color[] {new Color(150, 150, 150), new Color(50, 50, 50)}, CycleMethod.NO_CYCLE));
		}
		((Graphics2D) g).fillOval((int) x, (int) y, width, height);
		
		g.setColor(Color.BLACK);
//		g.drawOval((int) x, (int) y, width, height);
		g.drawOval((int) x + 1, (int) y + 1, width - 2, height - 2);
		
		input[0].render(g);
	}
}
