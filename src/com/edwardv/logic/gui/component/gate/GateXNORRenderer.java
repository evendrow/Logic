package com.ugiveme.logic.gui.component.gate;

import com.ugiveme.entity.draggable.DragHandler;

public class GateXNORRenderer extends GateRenderer{

	public GateXNORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "XNOR", "res/XnorGate.png");
	}
}
