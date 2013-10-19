package logic.misc;

public interface DataReceiver {
	
	/**Set the data of this DataReceiver*/
	public void setData(boolean[] data);
	/**Set the bit at the specified index*/
	public void setBit(boolean bit, int index);
	/**Get the accepted data width of this DataReceiver*/
	public int getAcceptedDataWidth();

}
