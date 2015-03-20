package com.ugiveme.logic.component;

import com.ugiveme.logic.misc.DataProvider;
import com.ugiveme.logic.misc.DataReceiver;
import com.ugiveme.logic.Tickable;

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
	public void tick() {
		destination.setBit(source.getBit(sourceIndex), destinationIndex);
	}

}
