package com.edwardv.logic.gui.component.gate;

import com.edwardv.entity.draggable.DragHandler;

public class GateNORRenderer extends GateRenderer{

	public GateNORRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "NOR", "res/NorGate.png");
	}
}
