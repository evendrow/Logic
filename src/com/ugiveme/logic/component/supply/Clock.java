package com.ugiveme.logic.component.supply;

import com.ugiveme.logic.misc.DataProvider;
import com.ugiveme.logic.Tickable;

public class Clock implements Tickable, DataProvider {
	
	private int frequency;
	private boolean powering;
	private long lastTick;
	
	public Clock(int frequency) {
		this.frequency = frequency;
		this.powering = false;
		this.lastTick = System.nanoTime();
	}

	@Override
	public boolean[] getCurrentData() {
		return new boolean[]{powering};
	}

	@Override
	public boolean getBit(int index) {
		return new boolean[]{powering}[index];
	}

	@Override
	public int getProvidedDataWidth() {
		return 1;
	}

	@Override
	public void tick() {
		if ((lastTick - System.nanoTime()) * frequency > (long) 1000000000) {
			powering = true;
			lastTick = System.nanoTime();
		} else {
			powering = false;
		}
	}

}
