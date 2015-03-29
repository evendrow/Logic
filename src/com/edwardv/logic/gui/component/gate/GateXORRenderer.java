package com.edwardv.logic.gui.component.gate;

import com.edwardv.entity.draggable.DragHandler;

public class GateXORRenderer extends GateRenderer{

	public GateXORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "XOR", "res/XorGate.png");
	}
}
