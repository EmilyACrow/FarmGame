package gameLogic;
import java.util.ArrayList;

/**
 * ShoppingCart is a class that keeps track of what the merch player wants to buy and the cost of it.
 * Last modified: 3-05-2020
 * 
 * Created: 2-05-2020
 * @author Kenn Leen Duenas Fulgencio
 */
public class ShoppingCart {
	
	/**
	 * m_cart is a list of Merchandise subclasses. Item, Animal or Crop.
	 */
	private ArrayList<Merchandise> m_cart; 
	
	/**
	 * total cost of all items on the list.
	 */
	private int m_totalCost;
	
	/**
	 * The shoppingCart Constructor method.
	 *  It makes an empty list and assigns m_totalCost with 0. 
	 */
	public ShoppingCart() {
		
		m_cart = new ArrayList<Merchandise>();
		m_totalCost = 0;
		
	}
	
	/**
	 * 
	 * Shopping Cart constructor for pre-populated ArrayList<Merchandise>
	 */
	public ShoppingCart(ArrayList<Merchandise> merchList)
	{
		m_cart = new ArrayList<Merchandise>();
		m_totalCost = 0;
		for(Merchandise m : merchList)
		{
			addToCart(m, m.getPurchasePrice());
		}
	}
	
	public ShoppingCart clone(ShoppingCart cart)
	{
		ShoppingCart newCart = new ShoppingCart();
		for(Merchandise m : cart.getCart())
		{
			Merchandise clonedMerch = m.clone();
			newCart.addToCart(clonedMerch, clonedMerch.getPurchasePrice());
		}
		return newCart;
	}
	
	/**
	 * 
	 * @param merch an instance of Merchandise. It could be a Crop, Item or Animal.
	 * @param amount cost of the merchandise player has added to the Cart.
	 */
	public void addToCart(Merchandise merch, int amount) {
		
		// add amount to the totalSum and add to the list.
		m_totalCost += amount;
		m_cart.add(merch);		
	}
	
	public void addToCart(Merchandise merch) {
		// add amount to the totalSum and add to the list.
		m_totalCost += merch.getPurchasePrice();
		m_cart.add(merch);	
		
	}
	
	/**
	 * Removes the merch from arraylist and the merch's price from totalCost
	 * @param merch A crop, item or Animal the player has removed from cart.
	 */
	public void removeFromCart(Merchandise merch) {
		
		m_totalCost -= merch.getPurchasePrice();
		m_cart.remove(merch);
	}
	
	/**
	 * Removes everything from the cart list.
	 */
	public void clearCart() {
		
		m_cart.clear();
	}
	
	/**
	 * @return list of merchandise in cart.
	 */
	public ArrayList<Merchandise> getCart() {
		return m_cart;
		
	}
	
	/**
	 * @param cart list of merchandises. Could be an object of Item, Crop or Animal.
	 */
	public void setM_cart(ArrayList<Merchandise> cart) {
		m_cart = cart;
	}
	
	/**
	 * @return total cost of everything in cart
	 */
	public int getTotalCost() {
		return m_totalCost;
	}
	
	/**
	 * @param totalCost the new calculated price for everything in cart.
	 */
	public void setTotalCost(int totalCost) {
		m_totalCost = totalCost;
	}

}