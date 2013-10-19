package logic.component.gate;

/**
 * A NOR gate. If at least one input is true, then
 * the output will be false, and if all inputs are false,
 * the output will be true.
 * @author ben
 */
public class GateNOR extends Gate {

	/**
	 * Create a new NOR gate.
	 * @param inputs The number of inputs this gate will have.
	 */
	public GateNOR(int inputs) {
		super(inputs);
	}

	@Override
	public void doTick() {
		for (boolean input : inputs) {
			if (input) {
				output = false;
				return;
			}
		}
		output = true;
	}

}
