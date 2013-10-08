package com.ugiveme.logic.component.misc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.Input;

public class SSD extends LogicElement{

	Rectangle[] segs = new Rectangle[] {
		new Rectangle(3, 8, 2, 17),
		new Rectangle(3, 3, 33, 2),
		new Rectangle(33, 8, 2, 17),
		new Rectangle(3, 28, 33, 2),
		new Rectangle(3, 32, 2, 17),
		new Rectangle(3, 54, 33, 2),
		new Rectangle(33, 32, 2, 17),
		new Rectangle(36, 57, 2, 2),
	};
	
	public SSD(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, 39, 60, "SSD");
		
		input = new Input[] {
				new Input(0, -8, 8, 8, this),
				new Input(10, -8, 8, 8, this),
				new Input(20, -8, 8, 8, this),
				new Input(30, -8, 8, 8, this),
				new Input(0, 60, 8, 8, this),
				new Input(10, 60, 8, 8, this),
				new Input(20, 60, 8, 8, this),
				new Input(30, 60, 8, 8, this)
		};
	}
	
	public void tick() {
		super.tick();
		
		for (int i=0;i<input.length;i++) {
			input[i].tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(200, 200, 200));
		g.fillRect((int) x, (int) y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRect((int) x, (int) y, width, height);
		
		for (int i=0;i<input.length;i++) {
			input[i].render(g);
		}
		g.setColor(Color.BLACK);
		for (int i=0;i<segs.length;i++) {
			if (input[i].isPowered()) {
				g.setColor(Color.YELLOW);
			}
			g.fillRect(segs[i].x + (int) x, segs[i].y + (int) y, segs[i].width, segs[i].height);
			g.setColor(Color.BLACK);
		}
	}
}
