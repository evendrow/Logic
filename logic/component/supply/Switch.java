package logic.component.supply;

import logic.misc.DataProvider;
import logic.misc.Tickable;

/**
 * A switch that can be switched between powering and non-powering.
 * @author ben
 */
public class Switch implements Tickable, DataProvider {
	
	private boolean powering;
	/**
	 * Create a new switch.
	 */
	public Switch() {
		this.powering = false;
	}
	
	/**
	 * Set whether this switch is powering.
	 * @param on Whether this switch should be powering.
	 */
	public void setOn(boolean on) {
		powering = on;
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
	public void doTick() {	}

}
