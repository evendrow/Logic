package com.edwardv.logic.component;

import com.edwardv.logic.Tickable;
import com.edwardv.logic.misc.DataProvider;
import com.edwardv.logic.misc.DataReceiver;

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
