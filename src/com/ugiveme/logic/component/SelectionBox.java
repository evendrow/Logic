package com.ugiveme.logic.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.entity.draggable.DraggableEntity;
import com.ugiveme.logic.LogicElement;

public class SelectionBox extends DraggableEntity{

	public SelectionGroup selectionGroup;
	
	private boolean boxStarted;
	
	private ArrayList<LogicElement> logicElements;
	
	public SelectionBox(DragHandler dragHandler, ArrayList<LogicElement> logicElements) {
		super(dragHandler, 0, 0, 0, 0, false);
		
		this.logicElements = logicElements;
		
		this.boxStarted = false;
	}
	
	public ArrayList[] duplicate() {
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
				ArrayList<LogicElement> LEInBox = new ArrayList<LogicElement>();
				for (int i=0;i<logicElements.size();i++) {
					if (getRect().intersects(logicElements.get(i).getRect())) {
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
			g.fillRect((int) x, (int) y, width, height);
			
			g.setColor(new Color(150, 150, 250));
			g.drawRect((int) x, (int) y, width, height);
		}
		
		if (selectionGroup != null) {
			selectionGroup.render(g);
		}
	}
}
