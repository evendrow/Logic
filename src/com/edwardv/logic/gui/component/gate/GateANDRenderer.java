package com.edwardv.logic.gui.component.gate;

import com.edwardv.entity.draggable.DragHandler;

public class GateANDRenderer extends GateRenderer{

	public GateANDRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "AND", "res/AndGate.png");
	}
}
