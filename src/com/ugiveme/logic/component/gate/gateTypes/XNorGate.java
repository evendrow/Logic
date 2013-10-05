package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class XNorGate extends Gate{

	public XNorGate(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "XNor");
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
			setPowered(false);
		} else {
			setPowered(true);
		}
	}
}
