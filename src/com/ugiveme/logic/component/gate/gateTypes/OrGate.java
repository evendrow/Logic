package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class OrGate extends Gate{

	public OrGate(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "or");
	}
	
	public void tick() {
		super.tick();
		
		setPowered(false);
		for (int i=0;i<getInputLength();i++) {
			if (getInputAtIndex(i).isPowered()) {
				setPowered(true);
				break;
			}
		}
	}
}
