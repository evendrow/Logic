package com.edwardv.logic.gui.component.gate;

import com.edwardv.entity.draggable.DragHandler;

public class GateXNORRenderer extends GateRenderer{

	public GateXNORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "XNOR", "res/XnorGate.png");
	}
}
