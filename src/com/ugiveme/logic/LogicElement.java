package com.ugiveme.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.entity.draggable.DraggableEntity;
import com.ugiveme.logic.component.Input;
import com.ugiveme.logic.component.Output;

public class LogicElement extends DraggableEntity{
	public static final int IOSIZE = 15;
	
	private boolean destroyed;
	
	private boolean powered;
	private String elementType;
	
	public Output output;
	public Input[] input;
	
	private boolean partOfMenu;
	
	public LogicElement(DragHandler dragHandler, int x, int y, int width, int height, String elementType) {
		super(dragHandler, x, y, width, height);
		
		this.destroyed = false;
		
		this.powered = false;
		this.elementType = elementType;
		
		this.partOfMenu = false;
	}
	
	public void destroy() {
		destroyed = true;
		
		if (input != null) {
			for (int i=0;i<input.length;i++) {
				input[i].destroy();
			}
		}
		
		if (output != null) {
			output.destroy();
		}
	}
	
	public LogicElement(DragHandler dragHandler, int x, int y, int width, int height, String elementType, boolean partOfMenu) {
		super(dragHandler, x, y, width, height);
		
		this.powered = false;
		this.elementType = elementType;
		
		this.partOfMenu = partOfMenu;
	}
	
	public void click(Point clickPoint) { }
	
	public void tick() {
		if (!destroyed) {
			super.tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	public boolean isPowered() {
		return powered;
	}
	
	public void setPowered(boolean powered) {
		this.powered = powered;
		if (output != null) {
			output.setPowered(powered);
		}
	}
	
	public void togglePower() {
		this.powered = !powered;
		if (output != null) {
			output.setPowered(powered);
		}
	}
	
	public Input[] getInput() {
		if (input != null) {
			return input;
		}
		return null;
	}
	
	public int getInputLength() {
		if (input == null) {
			return 0;
		} else {
			return input.length;
		}
	}
	
	public Input getInputAtIndex(int index) {
		if (index < input.length) {
			return input[index];
		}
		return null;
	}
	
	public Output getOutput() {
		if (output != null) {
			return output;
		}
		return null;
	}
	
	public String getType() {
		return elementType;
	}
	
	public boolean isPartOfMenu() {
		return partOfMenu;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
}
