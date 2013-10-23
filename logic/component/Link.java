package logic.component;

import logic.misc.DataProvider;
import logic.misc.DataReceiver;
import logic.misc.Tickable;

public class Link implements Tickable {
	
	DataProvider source;
	DataReceiver destination;
	int sourceIndex, destinationIndex;
	
	public Link(DataProvider source, DataReceiver destination, int sourceIndex, int destinationIndex) {
		this.source = source;
		this.destination = destination;
		this.sourceIndex = sourceIndex;
		this.destinationIndex = destinationIndex;
	}
	
	@Override
	public void doTick() {
		destination.setBit(source.getBit(sourceIndex), destinationIndex);
	}

}
