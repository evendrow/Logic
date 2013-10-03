package com.ugiveme.logic.component.gate.gateTypes;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.Gate;

public class NandGate extends Gate{
	
	public NandGate(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, "Nand");
	}
	
	public void tick() {
		super.tick();
		
		setPowered(false);
		
		boolean allOn = true;
		
		for (int i=0;i<getInputLength();i++) {
			if (!getInputAtIndex(i).isPowered()) {
				allOn = false;
				break;
			}
		}
		if (!allOn) {
			setPowered(true);
		}
	}
}
