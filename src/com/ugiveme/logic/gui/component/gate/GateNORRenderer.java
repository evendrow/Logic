package com.ugiveme.logic.gui.component.gate;

import com.ugiveme.entity.draggable.DragHandler;

public class GateNORRenderer extends GateRenderer{

	public GateNORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "NOR", "res/NorGate.png");
	}
}
