package logic.component.gate;

/**
 * An XNOR gate. If all inputs are the same,
 * then the output will be true, otherwise,
 * the output will be false.
 * @author ben
 */
public class GateXNOR extends Gate {
	
	public GateXNOR(int inputs) {
		super(inputs);
	}

	@Override
	public void doTick() {
		for (int i = 0; i < inputs.length - 1; i++) {
			if (inputs[i] != inputs[i + 1]) {
				output = false;
				return;
			}
		}
		output = true;
	}

}
