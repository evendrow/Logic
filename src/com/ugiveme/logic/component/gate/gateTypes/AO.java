package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class AO extends Gate{

	public AO(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "AO");
		
		setPowered(true);
	}
}
