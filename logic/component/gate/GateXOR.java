package logic.component.gate;

/**
 * An XOR gate. There are two modes of operation for this gate: strict and non-strict.
 * While in strict mode, if exactly one input is set to true, the output will be true,
 * and if in non-strict mode, it will return true only if there is an odd number of true inputs.
 * @author ben
 */
public class GateXOR extends Gate {
	
	private boolean strict;
	
	/**
	 * Create a new XOR gate.
	 * @param inputs The number of inputs this gate has.
	 * @param strict Whether or not this gate should operate in strict mode.
	 */
	public GateXOR(int inputs, boolean strict) {
		super(inputs);
		this.strict = strict;
	}
	
	public GateXOR(int inputs) {
		this(inputs, false);
	}
	
	public boolean getStrict() {
		return strict;
	}
	
	public void setStrict(boolean strict) {
		this.strict = strict;
	}
	
	@Override
	public void doTick() {
		if (strict) {
			boolean temp = false;
			for (boolean input : inputs) {
				if (input && temp) {
					output = false;
					return;
				} else if (input) {
					temp = true;
				}
			}
		} else {
			byte num = 0;
			for (boolean input : inputs) {
				if (input) {
					num++;
				}
			}
			if (num % 2 == 1) {
				output = true;
				return;
			}
		}
	}

}
