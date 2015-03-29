package com.edwardv.logic.gui.component.gate;

import com.edwardv.entity.draggable.DragHandler;

public class GateORRenderer extends GateRenderer{

	public GateORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "OR", "res/OrGate.png");
	}
}
