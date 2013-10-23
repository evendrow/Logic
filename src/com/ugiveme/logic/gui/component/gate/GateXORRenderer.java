package com.ugiveme.logic.gui.component.gate;

import com.ugiveme.entity.draggable.DragHandler;

public class GateXORRenderer extends GateRenderer{

	public GateXORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "XOR", "res/XorGate.png");
	}
}
