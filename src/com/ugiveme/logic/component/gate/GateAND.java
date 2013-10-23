package com.ugiveme.logic.component.gate;

/**
 * An AND gate. If all inputs are true,
 * the output will be true, otherwise,
 * the output will be false.
 * @author ben
 */
public class GateAND extends Gate {

	/**
	 * Create a new AND gate.
	 * @param inputs The number of inputs that this gate has.
	 */
	public GateAND(int inputs) {
		super(inputs);
	}

	@Override
	public void tick() {
		for (boolean input : inputs) {
			if (!input) {
				output = false;
				return;
			}
		}
		output = true;
	}

}
