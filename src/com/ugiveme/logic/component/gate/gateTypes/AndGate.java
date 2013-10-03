package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class AndGate extends Gate{

	public AndGate(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "And");
	}
	
	public void tick() {
		super.tick();
		
		setPowered(false);
		
		boolean willPower = true;
		for (int i=0;i<getInputLength();i++) {
			if (!getInputAtIndex(i).isPowered()) {
				willPower = false;
				break;
			}
		}
		if (willPower) {
			setPowered(true);
		}
	}
}
