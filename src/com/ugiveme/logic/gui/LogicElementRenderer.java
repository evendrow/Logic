package com.ugiveme.logic.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.entity.draggable.DraggableEntity;
import com.ugiveme.logic.Logic;
import com.ugiveme.logic.Renderable;
import com.ugiveme.logic.Tickable;

public class LogicElementRenderer extends DraggableEntity implements Renderable, Tickable{

	public static final int IOSIZE = 15;
	
	private String logicType;
	
	public InputRenderer[] input;
	public OutputRenderer[] output;
	
	private int id;
	
	private boolean destroyed;
	
	public LogicElementRenderer(String logicType, DragHandler dragHandler, int x, int y, int width, int height) {
		super(dragHandler, x, y, width, height);
		this.logicType = logicType;
		
		this.input = new InputRenderer[0];
		this.output = new OutputRenderer[0];
		
		this.id = Logic.getNextId();
		
		this.destroyed = false;
	}
	
	public LogicElementRenderer(int id, String logicType, DragHandler dragHandler, int x, int y, int width, int height) {
		super(dragHandler, x, y, width, height);
		this.logicType = logicType;
		
		this.input = null;
		this.output = null;
		
		this.id = id;
		
		this.destroyed = false;
	}
	
	public void destroy() {
		input = null;
		output = null;
		setDraggable(false);
		
		destroyed = true;
	}
	
	public void tick() {
		super.tick();
		
//		if (logicElement.getInputLength() > 0) {
//			for (int i=0;i<logicElement.getInputLength();i++) {
//				logicElement.getInput()[0].setLocation((int) x + logicElement.getInput()[0].getXOffset(), (int) y + logicElement.getInput()[0].getYOffset());
//			}
//		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	public void click(Point clickPoint) {};
	
	public void renderIO(Graphics g) {
		if (input != null) {
			for (int i=0;i<input.length;i++) {
				input[i].render(g);
			}
		}
		
		if (output != null) {
			for (int i=0;i<output.length;i++) {
				output[i].render(g);
			}
		}
	}
	
	public InputRenderer[] getInput() {
		return input;
	}
	
	public OutputRenderer[] getOutput() {
		return output;
	}
	
	public String getLogicType() {
		return logicType;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
}
