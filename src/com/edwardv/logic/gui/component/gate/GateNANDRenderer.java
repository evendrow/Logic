package com.edwardv.logic.gui.component.gate;

import com.edwardv.entity.draggable.DragHandler;

public class GateNANDRenderer extends GateRenderer{

	public GateNANDRenderer(DragHandler dragHandler, int x, int y, int inputs) {
		super(dragHandler, x, y, inputs, "NAND", "res/NandGate.png");
	}
}
