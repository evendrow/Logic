package com.edwardv.logic.gui.component.supply;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.edwardv.entity.draggable.DragHandler;
import com.edwardv.logic.component.supply.Switch;
import com.edwardv.logic.gui.LogicElementRenderer;
import com.edwardv.logic.gui.OutputRenderer;

public class SwitchRenderer extends LogicElementRenderer{

	private Switch switchElement;
	
	public SwitchRenderer(DragHandler dragHandler, int x, int y) {
		super("Switch", dragHandler, x, y, 30, 60);
		
		switchElement = new Switch();
		
		output = new OutputRenderer[] {
				new OutputRenderer(switchElement, 0, this, width, (height - IOSIZE)/2)
		};
	}
	
	@Override
	public void click(Point clickPoint) {
		if (this.contains(clickPoint)) {
			switchElement.togglePower();
		}
	}
	
	public void render(Graphics g) {
		renderIO(g);
		
		g.setColor(new Color(80, 80, 80));
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.GREEN);
		g.fillRect((int) x + 4, (int) y + 8, width - 8, (height/2) - 8);
		g.setColor(Color.RED);
		g.fillRect((int) x + 4, (int) y + (height/2), width - 8, (height/2) - 8);
		
		g.setColor(Color.GRAY);
		g.fillRect((int) x + 4, (int) y + (switchElement.getBit(0) ? (height/2) : 8), width - 8, (height/2) - 8);
	}
}
