package com.edwardv.logic.component.receive;

import com.edwardv.logic.Tickable;
import com.edwardv.logic.misc.DataReceiver;

public class Light implements Tickable, DataReceiver{

	private boolean powered;
	
	public Light() {
		this.powered = false;
	}
	
	@Override
	public void setData(boolean[] data) {
		powered = data[0];
	}

	@Override
	public void setBit(boolean bit, int index) {
		powered = bit;
	}

	@Override
	public int getAcceptedDataWidth() {
		return 1;
	}

	@Override
	public void tick() { }
	
	public boolean isPowered() {
		return powered;
	}
}
