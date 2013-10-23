package logic.component.gate;

import logic.misc.DataProvider;
import logic.misc.DataReceiver;
import logic.misc.Tickable;

/**
 * A generic logic gate with any number of inputs and one output.
 * To implement your own gate, extend this class and override the
 * doTick() method in order to determine the output based on the
 * current inputs.
 * @author ben
 */
public abstract class Gate implements Tickable, DataProvider, DataReceiver {
	
	protected boolean inputs[];
	protected boolean output;
	
	/**
	 * Create a new Gate
	 * @param inputs The number of inputs that this gate has.
	 */
	public Gate(int inputs) {
		this.inputs = new boolean[inputs];
	}
	
	/**
	 * Set the number of inputs that this gate has.
	 * @param length The number of inputs this gate should have.
	 */
	public void setInputLength(int length) {
		inputs = new boolean[length];
	}

	@Override
	public void setData(boolean[] data) {
		inputs = data;
	}

	@Override
	public void setBit(boolean bit, int index) {
		inputs[index] = bit;
	}

	@Override
	public int getAcceptedDataWidth() {
		return inputs.length;
	}

	@Override
	public boolean[] getCurrentData() {
		return new boolean[]{output};
	}

	@Override
	public boolean getBit(int index) {
		return new boolean[]{output}[index];//This way there is an error thrown if used weirdly
	}

	@Override
	public int getProvidedDataWidth() {
		return 1;
	}

}
