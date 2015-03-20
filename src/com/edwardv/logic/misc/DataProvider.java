package com.ugiveme.logic.misc;

public interface DataProvider {
	
	/**Get the entire set of data held by this DataProvider*/
	public boolean[] getCurrentData();
	/**Get the bit at the specified index*/
	public boolean getBit(int index);
	/**Get the accepted width of the data provided by this DataProvider*/
	public int getProvidedDataWidth();

}
