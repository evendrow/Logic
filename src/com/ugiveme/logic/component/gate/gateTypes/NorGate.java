package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class NorGate extends Gate{

	public NorGate(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "Nor", "res/NorGate.png");
	}
	
	public void tick() {
		super.tick();
		
		setPowered(true);
		for (int i=0;i<getInputLength();i++) {
			if (getInputAtIndex(i).isPowered()) {
				setPowered(false);
				break;
			}
		}
	}
}
