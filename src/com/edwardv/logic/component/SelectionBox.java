package com.edwardv.logic.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.edwardv.entity.draggable.DragHandler;
import com.edwardv.entity.draggable.DraggableEntity;
import com.edwardv.logic.gui.LogicElementRenderer;
import com.edwardv.logic.save.SaveObject;

public class SelectionBox extends DraggableEntity{

	public SelectionGroup selectionGroup;
	
	private boolean boxStarted;
	
	private ArrayList<LogicElementRenderer> logicElements;
	
	public SelectionBox(DragHandler dragHandler, ArrayList<LogicElementRenderer> logicElements) {
		super(dragHandler, 0, 0, 0, 0, false);
		
		this.logicElements = logicElements;
		
		this.boxStarted = false;
	}
	
	public SaveObject duplicate() {
		if (selectionGroup != null) {
			if (selectionGroup.size() > 0) {
				return selectionGroup.duplicate();
			}
		}
		
		return null;
	}
	
	public void tick() {
		super.tick();
		
		if (selectionGroup != null) {
			selectionGroup.tick();
		}
		
		if (!boxStarted) {
			if (getDragHandler().startedDragging() && !draggingEntity) {
				boxStarted = true;
				getDragHandler().setStartedDragging(false);
				draggingEntity = true;
				x = getDragHandler().getMousePos().x;
				y = getDragHandler().getMousePos().y;
			}
		} else {
			width = (int) (getDragHandler().getMousePos().x - x);
			height = (int) (getDragHandler().getMousePos().y - y);
			if (getDragHandler().isDragging() == false) {
				boxStarted = false;
				ArrayList<LogicElementRenderer> LEInBox = new ArrayList<LogicElementRenderer>();
				for (int i=0;i<logicElements.size();i++) {
					if (getOmniDirectionalRect().intersects(logicElements.get(i).getRect())) {
						LEInBox.add(this.logicElements.get(i));
					}
				}
				selectionGroup = new SelectionGroup(LEInBox);
				width = 0;
				height = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		if (width != 0 && height != 0) {
			g.setColor(new Color(150, 150, 250, 60));
			g.fillRect((int) x+((int) x < x+width ? 0 : width), (int) y+((int) y < y+height ? 0 : height), ((int) x < x+width ? width : (int) -width), ((int) y < y+height ? height : -height));
			
			g.setColor(new Color(150, 150, 250));
			g.drawRect((int) x+((int) x < x+width ? 0 : width), (int) y+((int) y < y+height ? 0 : height), ((int) x < x+width ? width : (int) -width), ((int) y < y+height ? height : -height));
		}
		
		if (selectionGroup != null) {
			selectionGroup.render(g);
		}
	}
	
	public Rectangle getOmniDirectionalRect() {
		return new Rectangle((int) x+((int) x < x+width ? 0 : width), (int) y+((int) y < y+height ? 0 : height), ((int) x < x+width ? width : (int) -width), ((int) y < y+height ? height : -height));
	}
}
