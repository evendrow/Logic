package logic.component.gate;

/**
 * An OR gate. If at least one input is true, then
 * the output will be true, and if all inputs are false,
 * the output will be false.
 * @author ben
 */
public class GateOR extends Gate {

	/**
	 * Create a new OR gate.
	 * @param inputs The number of inputs this gate will have.
	 */
	public GateOR(int inputs) {
		super(inputs);
	}

	@Override
	public void doTick() {
		for (boolean input : inputs) {
			if (input) {
				output = true;
				return;
			}
		}
		output = false;
	}

}
