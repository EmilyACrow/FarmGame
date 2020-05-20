package gameLogic;
/**
* The Merchandise interface represents any item that can be sold
* in the General Store

* 
* Last modified: 1-5-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/

public interface Merchandise {

	public int getPurchasePrice();
	public void setPurchasePrice(int price);
	public String getName();
	public void setName(String name);
}
