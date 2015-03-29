package com.edwardv.logic.component.supply;

import com.edwardv.logic.Tickable;
import com.edwardv.logic.misc.DataProvider;

/**
 * A counter.
 * WARNING: CREATING IT WITH AN UPPER BOUND IS LIKELY BUGGY.
 * @author ben
 */
public class Counter implements Tickable, DataProvider {
	
	private boolean range[];
	private boolean outputs[];
	private int index;
	
	/**
	 * Create a new Counter.
	 * @param outputs The number of outputs this counter has. It is equivalent to the provided data width.
	 * @param toCount The range of data that this counter should cycle through.
	 */
	public Counter(int outputs, boolean[] toCount) {
		this.range = toCount;
		this.outputs = new boolean[outputs];
		this.index = 0;
	}
	
	/**
	 * Create a new Counter.
	 * @param countUpper The upper limit that this counter should go to. The width of this is the width of the supplied data. Leading zeroes are stripped.
	 */
	public Counter(boolean[] countUpper) {
		boolean[] toPass;
		int len = 0;
		int numPlaces = 0;
		for (int i = 0; i < countUpper.length; i++) {
			len += (countUpper[countUpper.length - (1 + i)] == true) ? 2^i : 0;
			if (countUpper[i] == true) {
				numPlaces = i;
			}
		}
		toPass = new boolean[len];
		for (int i = 0; i < len; i++) {
			if (Integer.toBinaryString(i).charAt(i % numPlaces) == '1') {
				toPass[i] = true;
			} else {
				toPass[i] = false;
			}
		}
		this.range = toPass;
		this.outputs = new boolean[countUpper.length];
		this.index = 0;
	}

	@Override
	public boolean[] getCurrentData() {
		return outputs;
	}

	@Override
	public boolean getBit(int index) {
		return outputs[index];
	}

	@Override
	public int getProvidedDataWidth() {
		return outputs.length;
	}

	@Override
	public void tick() {
		for (int i = 0; i < outputs.length; i++) {
			outputs[i] = range[index];
			if (index < range.length - 1) {
				index++;
			} else {
				index = 0;
			}
		}
	}

}
