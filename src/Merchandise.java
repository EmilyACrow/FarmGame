/**
* The Merchandise interface represents any item that can be sold
* in the General Store
* 
* Implements XmlTransient from Blaise Doughan
* (http://blog.bdoughan.com/2011/06/ignoring-inheritance-with-xmltransient.html)
* 
* Last modified: 29-4-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/

public abstract class Merchandise {

	public abstract int getPurchasePrice();
	public abstract void setPurchasePrice(int price);
	public abstract String getName();
	public abstract void setName(String name);
}
