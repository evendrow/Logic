package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class EOrGate extends Gate{

	public EOrGate(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "Eor");
	}
	
	public void tick() {
		super.tick();
		
		boolean onePowered = false;
		for (int i=0;i<getInputLength();i++) {
			if (getInputAtIndex(i).isPowered()) {
				if (onePowered) {
					onePowered = false;
					break;
				} else {
					onePowered = true;
				}
			}
		}
		
		if (onePowered) {
			setPowered(true);
		} else {
			setPowered(false);
		}
	}
}
