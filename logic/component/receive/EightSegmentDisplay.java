package logic.component.receive;

import logic.misc.DataReceiver;
import logic.misc.Tickable;

/**
 * An eight segment display.
 * @author ben
 */
public class EightSegmentDisplay implements Tickable, DataReceiver {
	
	public static final int TOP_LEFT = 0;
	public static final int CENTER_MIDDLE = 1;
	public static final int BOTTOM_LEFT = 2;
	public static final int CENTER_BOTTOM = 3;
	public static final int DOT = 4;
	public static final int BOTTOM_RIGHT = 5;
	public static final int TOP_RIGHT = 6;
	public static final int CENTER_TOP = 7;
	
	private boolean segments[];
	private boolean type;
	
	/**
	 * Create a new Eight Segment Display.
	 * @param anode Whether or not this display operates in anode mode.
	 */
	EightSegmentDisplay(boolean anode) {
		this.segments = new boolean[8];
		this.type = anode;
	}
	
	/**
	 * Get whething the specified pin number is on. These pins are available as static int's of this class.
	 * @param pinNum The number of the pin to check
	 * @return Whether the pin in question is on or off.
	 */
	public boolean getPinOn(int pinNum) {
		return type ? !segments[pinNum] : segments[pinNum];
	}

	@Override
	public void setData(boolean[] data) {
		this.segments = data;
	}

	@Override
	public void setBit(boolean bit, int index) {
		this.segments[index] = bit;
	}

	@Override
	public int getAcceptedDataWidth() {
		return 8;
	}

	@Override
	public void doTick() {	}

}
