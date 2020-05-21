package gameLogic;
import gameScreens.GeneralStoreScreen;
import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
/**
 * The GeneralStore class is where the player can view or purchase available merchandise.
 * They can also view their current balance.
 * 
 * Last modified: 13-05-2020
 * 
 * created: 1-05-2020
 * @author Kenn Leen Duenas Fulgencio
 */

public class GeneralStore {
	
	/**
	 * Wrapper for all merchandise in shop
	 */
	private MerchandiseWrapper m_merchWrapper;
	
	/**
	 * Shopping cart variable
	 */
	private ShoppingCart m_shoppingCart;
	
	private GeneralStoreScreen m_storefront;
	
	
	/**
	 * This constructor method reads in from an XML config file and populates the merchandise wrapper.
	 * Also makes an instance of a ShoppingCart class.
	 */
	public GeneralStore() {
		
		m_shoppingCart = new ShoppingCart();
		
		try
		{
			File file = new File("config/store.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(MerchandiseWrapper.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        m_merchWrapper = (MerchandiseWrapper) unmarshaller.unmarshal(file);
		}
		catch(Exception e)
		{
			System.out.println(e);
			throw new RuntimeException("Error configuring GeneralStore");
		}
		
	
	}

	/*Using overloading on methods addToShop() and removeFromShop.
	 This way of coding is another way to put the merch 
	 into the Wrapper.
	 */
	
	
	
	
	//addToShop methods begin below this line 
	
	
	
	/**
	 * 
	 * @param item An instance of Item class.
	 */
	public void addToShop(Item item) {
		
		addToShop((Merchandise)item);
	}
	
	/**
	 * 
	 * @param animal An instance of Animal class.
	 */
	public void addToShop(Animal animal) {
		
		addToShop((Merchandise)animal);
	}
	
	/**
	 * 
	 * @param crop An instance of Crop class.
	 */
	public void addToShop(Crop crop) {
		
		addToShop((Merchandise)crop);
	}
	
	public void addToShop(Merchandise merch)
	{
		//Create shallow copy of Arraylist inside merchwrapper
		ArrayList<Merchandise> tempList = m_merchWrapper.getMerchList();
		//Since it is a shallow copy, this will affect the original as well
		tempList.add(merch);
	}
	
	
	//removeFromShop methods begin below this line

	
	
	/**
	 * @param purchasedItem item player has bought.
	 */
	public void removeFromShop(Item item) 
	{
		
		removeFromShop((Merchandise) item);		
		
	}
	
	/**
	 * @param purchasedAnimal the animal player has bought.
	 */
	public void removeFromShop(Animal animal) 
	{
		
		removeFromShop((Merchandise) animal);				
	}
	
	/**
	 * @param purchasedAnimal the animal player has bought.
	 */
	public void removeFromShop(Crop crop) 
	{
		
		removeFromShop((Merchandise) crop);				
	}
	
	/**
	 * @param purchasedAnimal the animal player has bought.
	 */
	public void removeFromShop(Merchandise merch) 
	{
		//Create shallow copy of Arraylist inside merchwrapper
		ArrayList<Merchandise> tempList = m_merchWrapper.getMerchList();
		//Since it is a shallow copy, this will affect the original as well
		tempList.remove(merch);
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
	 * before giving merch to the player.
	 * @return an ArrayList of merch player has purchased.
	 */
	public ArrayList<Merchandise> checkout(Farm farm) {
		
		//local variable created to hold the merchandise being bought after cart is cleared.
		MerchandiseWrapper purchasedMerch = new MerchandiseWrapper(m_shoppingCart.getCart());
		
		int playersMoney = farm.getMoney();
		double discountPercent = farm.getPurchaseDiscountMod();
		
		//calculation for any discounts.
		double amountRemoved = m_shoppingCart.getTotalCost() * discountPercent;
		int finalCost = (int) (m_shoppingCart.getTotalCost() - amountRemoved); 
		
		
		//check player's balance
		if (checkBalance(finalCost, playersMoney)) {
			//Check that the farm can fit all of the animals in the cart
			if(farm.getAnimals().size() + purchasedMerch.getAnimals().size() > farm.getMaxAnimalAmount())
			{
				throw new IllegalStateException(
						String.format("Not enough room for all animals! %d animals in cart, but only %d spaces in farm."
								, purchasedMerch.getAnimals().size()
								, farm.getMaxAnimalAmount() - farm.getAnimals().size()));
			}
			//Check that the farm has space for all for the crops in the cart
			if(farm.getCrops().size() + purchasedMerch.getCrops().size() > farm.getMaxCropAmount())
			{
				throw new IllegalStateException(
						String.format("Not enough room for all crops! %d crops in cart, but only %d spaces in farm."
								, purchasedMerch.getCrops().size()
								, farm.getMaxCropAmount() - farm.getCrops().size()));
			}
			
			// subtract the totalCost from the player's money.
			farm.setMoney(playersMoney - finalCost);
			
			
			//Empty the cart of the merch. 
			m_shoppingCart.clearCart();
			
			return purchasedMerch.getMerchList();
			
		}
		
		else {
			//Player can't afford the merch they have in the Cart.
			throw new IllegalStateException(String.format("Not enough Money. Total cost is %d but your only have %d in the bank"
					, finalCost
					, playersMoney));
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
	 * 
	 * @return MerchadniseWrapper of all merchandise
	 */
	public MerchandiseWrapper getMerchandise()
	{
		return m_merchWrapper;
	}
	
	/**
	 * @return a list of available animals in store.
	 */
	public ArrayList<Animal> getAnimals() {
		
		return m_merchWrapper.getAnimals();
	}
	
	/**
	 * @return an arraylist of items available in store 
	 */
	
	public ArrayList<Item> getItems() {
		
		return m_merchWrapper.getItems();
	}

	/**
	 * @return a list of crops available in store 
	 */
	public ArrayList<Crop> getCrops() {
		
		return m_merchWrapper.getCrops();
	}	
	
	/**
	 * @return The instance of m_shoppingCart. Has the attributes totalCost and Price.
	 */
	public ShoppingCart getShoppingCart() {
		
		return m_shoppingCart;
	}
	
	
}
