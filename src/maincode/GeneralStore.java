package maincode;

import java.util.ArrayList;

/**
 * The GeneralStore class is where the player can view or purchase available merchandise.
 * They can also view their current balance.
 * 
 * Last modified: 7-05-2020
 * 
 * created: 1-05-2020
 * @author Kenn Leen Duenas Fulgencio
 */

public class GeneralStore {
	
	/**
	 * An arrayList of available items in shop
	 */
	private ArrayList<Item> m_itemsAvailable;
	
	/**
	 * an arraylist of animals available in shop
	 */
	private ArrayList<Animal> m_animalsAvailable;
	
	/**
	 * an arraylist of crops available
	 */
	private ArrayList<Crop> m_cropsAvailable;
	
	/**
	 * Shopping cart variable
	 */
	private ShoppingCart m_shoppingCart;
	
	
	/**
	 * This constructor method creates empty lists for items, animals and crops in GeneralStore.
	 * Also makes an instance of a ShoppingCart class.
	 */
	public GeneralStore() {
		
		m_itemsAvailable = new ArrayList<Item>();
		m_animalsAvailable = new ArrayList<Animal>();
		m_cropsAvailable = new ArrayList<Crop>();
		
		m_shoppingCart = new ShoppingCart();
		
	
	}

	/*Using overloading on methods addToShop() and removeFromShop.
	 This way of coding is another way to put the merch 
	 into the correct ArrayList. 
	 */
	
	
	
	
	//addToShop methods begin below this line 
	
	
	
	/**
	 * 
	 * @param item An instance of Item class.
	 */
	public void addToShop(Item item) {
		
		m_itemsAvailable.add(item);
	}
	
	/**
	 * 
	 * @param animal An instance of Animal class.
	 */
	public void addToShop(Animal animal) {
		
		m_animalsAvailable.add(animal);
	}
	
	/**
	 * 
	 * @param crop An instance of Crop class.
	 */
	public void addToShop(Crop crop) {
		
		m_cropsAvailable.add(crop);
	}
	
	
	//removeFromShop methods begin below this line

	
	
	/**
	 * @param purchasedItem item player has bought.
	 */
	public void removeFromShop(Item purchasedItem) {
		
		m_itemsAvailable.remove(purchasedItem);		
		
	}
	
	/**
	 * @param purchasedAnimal the animal player has bought.
	 */
	public void removeFromShop(Animal purchasedAnimal) {
		
		m_animalsAvailable.remove(purchasedAnimal);				
	}
	
	
	
	//methods relevant to the ShoppingCart begin below this line.
	
	
	
	/**  
	 * @return An arrayList of merch currently in cart.
	 */
	public ArrayList<Merchandise> viewCart() {
	
		return m_shoppingCart.getCart();
	}
	
	/**
	 * @param merch is either an instance of a crop, animal or item.
	 */
	public void addToCart(Merchandise merch) {
		
		m_shoppingCart.addToCart(merch, merch.getPurchasePrice());
	}
	
	/**
	 * @param merch player has chosen to remove from cart.
	 */
	public void removeFromCart(Merchandise merch) {
		
		m_shoppingCart.removeFromCart(merch);
	}
	
	
	
	//methods relevant to purchasing merch in the car begin below. WIP
	
	
	
	/**
	 * The checkout method checks the player can afford the merch in the cart
	 * before selling to player.
	 * @return an ArrayList of merch player has purchased.
	 */
	public ArrayList<Merchandise> checkout(Farm farm) {
		
		//local variable created to hold the merchandise being bought after cart is cleared.
		ArrayList<Merchandise> purchasedMerch = new ArrayList<Merchandise>(m_shoppingCart.getCart());
		
		int playersMoney = farm.getMoney();
		double discountPercent = farm.getPurchaseDiscountMod();
		
		//calculation for any discounts.
		double amountRemoved = m_shoppingCart.getTotalCost() * discountPercent;
		int finalCost = (int) (m_shoppingCart.getTotalCost() - amountRemoved); 
		
		
		//check player's balance
		if (checkBalance(finalCost, playersMoney)) {
			
			// subtract the totalCost from the player's money.
			farm.setMoney(playersMoney - finalCost);
			
			
			//Empty the cart of the merch. 
			m_shoppingCart.clearCart();
			
			return purchasedMerch;
			
		}
		
		else {
			//Player can't afford the merch they have in the Cart.
			throw new IllegalStateException("Not enough Money");
		}

	}
	
	/**
	 * @param finalCost Price of everything in the cart.
	 * @param playersMoney money the player has.
	 * 
	 * @return True: totalCost of merch is less than or equal to player's money. Therefore, the player can purchase it.
	 * False: totalCost is larger than player's money. Player can't purchase.
	 */
	private boolean checkBalance(int finalCost, int playersMoney) {
		
		
		if (finalCost <= playersMoney) {
			
			return true;
		}
		
		else {
			return false;
		}
		
	}
	

	
	
	//getters and setters begin below this line 
	
	
	
	/**
	 * @return a list of available animals in store.
	 */
	public ArrayList<Animal> getAnimalsAvailable() {
		
		return m_animalsAvailable;
	}
	
	/**
	 * 
	 * @param animals an arrayList of Animal instances.
	 */
	public void setAnimalsAvailable(ArrayList<Animal> animals) {
		
		m_animalsAvailable = animals;
		
	}
	
	/**
	 * @return an arraylist of items available in store 
	 */
	public ArrayList<Item> getItemsAvailable() {
		
		return m_itemsAvailable;
	}
	
	/**
	 * @param items an arraylist of Item instances.
	 */
	public void setItemsAvailable(ArrayList<Item> items) {
		
		m_itemsAvailable = items;
	}
	
	/**
	 * @return a list of crops available in store 
	 */
	public ArrayList<Crop> getCropsAvailable() {
		
		return m_cropsAvailable;
	}
	
	/**
	 * @param crops arraylist of  Crop instances
	 */
	public void setCropsAvailable(ArrayList<Crop> crops) {
		
		m_cropsAvailable = crops;
		
	}
	
	/**
	 * @return The instance of m_shoppingCart. Has the attributes totalCost and Price.
	 */
	public ShoppingCart getShoppingCart() {
		
		return m_shoppingCart;
	}
	
	
	}