package com.ugiveme.logic.component.gate;

/**
 * A NOT gate, otherwise known as an inverted.
 * If the input is true, then the output will be false, 
 * and if the input is false, the output will be true.
 * @author ben
 */
public class GateNOT extends Gate {

	/**
	 * Create a new NOT gate with one input.
	 */
	public GateNOT() {
		super(1);
	}

	@Override
	public void tick() {
		output = !inputs[0];
	}

}
