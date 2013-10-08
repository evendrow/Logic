package com.ugiveme.logic.component.misc;

import java.awt.Color;
import java.awt.Graphics;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.Input;
import com.ugiveme.logic.component.Output;

public class Not extends LogicElement{

	public Not(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, 50, 50, "Not");
		
		input = new Input[] {
				new Input(-IOSIZE, (int) ((height - IOSIZE)/2), IOSIZE, IOSIZE, this)
		};
		
		output = new Output((int) width - IOSIZE, (int) ((height - IOSIZE)/2), IOSIZE, IOSIZE, this);

	}
	
	public void tick() {
		super.tick();
		
		if (input[0].linked()) {
			setPowered(!input[0].isPowered());
		} else {
			setPowered(true);
		}
		
		for (int i=0;i<input.length;i++) {
			input[i].tick();
		}
		output.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(200, 200, 200));
		g.fillPolygon(new int[] {(int) x, (int) x + width, (int) x}, new int[] {(int) y, (int) y + (height/2), (int) y + height}, 3);
		g.setColor(Color.BLACK);
		g.drawPolygon(new int[] {(int) x, (int) x + width, (int) x}, new int[] {(int) y, (int) y + (height/2), (int) y + height}, 3);
		g.drawString(getType(), (int) x + 5, (int) y + 30);
		
		input[0].render(g);
		output.render(g);
	}
}
