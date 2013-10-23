package com.ugiveme.logic.gui.component.receive;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.receive.Light;
import com.ugiveme.logic.gui.InputRenderer;
import com.ugiveme.logic.gui.LogicElementRenderer;

public class LightRenderer extends LogicElementRenderer{

	private Light light;
	
	public LightRenderer(DragHandler dragHandler, int x, int y) {
		super("Light", dragHandler, x, y, 40, 40);
		
		light = new Light();
		
		input = new InputRenderer[] {
			new InputRenderer(light, 0, this, -IOSIZE, (height-IOSIZE)/2)	
		};
	}
	
	@Override
	public void render(Graphics g) {
		renderIO(g);
		
		 if (light.isPowered()) {
             ((Graphics2D) g).setPaint(new RadialGradientPaint(x + (width/2), y + (height/2), width, new float[] {0.0f, 0.5f}, new Color[] {new Color(255, 255, 150), new Color(255, 200, 40)}, CycleMethod.NO_CYCLE));
		 } else {
             ((Graphics2D) g).setPaint(new RadialGradientPaint(x + (width/2), y + (height/2), width, new float[] {0.0f, 0.5f}, new Color[] {new Color(150, 150, 150), new Color(50, 50, 50)}, CycleMethod.NO_CYCLE));
		 }
		 ((Graphics2D) g).fillOval((int) x, (int) y, width, height);
     
		 g.setColor(Color.BLACK);
		 g.drawOval((int) x + 1, (int) y + 1, width - 2, height - 2);
     
    	 input[0].render(g);
	}
}
