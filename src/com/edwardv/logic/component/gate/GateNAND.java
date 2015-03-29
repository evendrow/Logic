package com.edwardv.logic.component.gate;

/**
 * A NAND gate. If all inputs are true,
 * the output will be false, otherwise,
 * the output will be true.
 * @author ben
 */
public class GateNAND extends Gate {

	/**
	 * Create a new NAND gate.
	 * @param inputs The number of inputs this gate will have.
	 */
	public GateNAND(int inputs) {
		super(inputs);
	}

	@Override
	public void tick() {
		for (boolean input : inputs) {
			if (!input) {
				output = true;
				return;
			}
		}
		output = false;
	}

}
