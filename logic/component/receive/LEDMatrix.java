package logic.component.receive;

import logic.misc.DataReceiver;
import logic.misc.Tickable;

public class LEDMatrix implements Tickable, DataReceiver {
	
	private int width;
	private int height;
	/**
	 * The type of this LED matrix.
	 * If true, then anode will activate columns and cathode will activate rows.
	 * If false, then cathode will activate columns and anode will activate rows.
	 */
	private boolean type;
	private boolean matrix[][];
	private boolean pins[];
	
	public LEDMatrix(int width, int height, boolean type) {
		this.width = width;
		this.height = height;
		this.type = type;
		this.matrix = new boolean[width][height];
	}
	
	/**
	 * Get the current matrix of LED's that are on in this matrix.
	 * Note that anode vs cathode is adjusted for (by the type),
	 * so true = on and false = off. No funny business.
	 * @return
	 */
	public boolean[][] getMatrix() {
		return matrix;
	}

	/**
	 * Accepts data as a list of pin numbers -> true/false.
	 */
	@Override
	public void setData(boolean[] newData) {
		pins = newData;
	}

	@Override
	public void setBit(boolean bit, int index) {
		pins[index] = bit;
	}

	@Override
	public int getAcceptedDataWidth() {
		return width + height;
	}

	@Override
	public void doTick() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (type) {
					matrix[i][j] = !pins[i] && pins[j];
				} else {
					matrix[i][j] = pins[i] && !pins[j];
				}
			}
		}
	}

}
