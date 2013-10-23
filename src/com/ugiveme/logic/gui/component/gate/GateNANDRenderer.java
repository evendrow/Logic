package com.ugiveme.logic.gui.component.gate;

import com.ugiveme.entity.draggable.DragHandler;

public class GateNANDRenderer extends GateRenderer{

	public GateNANDRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "NAND", "res/NandGate.png");
	}
}
