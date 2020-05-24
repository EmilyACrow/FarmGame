package gameLogic;
import gameScreens.ConfirmPurchaseDialog;
import gameScreens.GeneralStoreScreen;
import gameScreens.StoreFilter;

import java.awt.LayoutManager;
import java.io.File;
import java.io.InputStream;
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
	private MerchandiseWrapper m_storeInventory;
	
	/**
	 * Shopping cart variable
	 */
	private MerchandiseWrapper m_shoppingCart;
	
	private int m_playerMoney;
	
	private GeneralStoreScreen m_storeFront;
	
	/**
	 * Enable the generalstore to access other classes through accessor methods
	 */
	private GameEnvironment m_game;
	
	
	/**
	 * RUN setGamEnvironment IMMEDIATELY AFTER THIS!
	 * This constructor method reads in from an XML config file and populates the merchandise wrapper.
	 * Also makes an instance of a MerchandiseWrapper class.
	 * @param game Reference to paired GameEnvironment object
	 */
	public GeneralStore(GameEnvironment game) {
		
		m_shoppingCart = new MerchandiseWrapper();
		
		try
		{
			InputStream inStream = GeneralStore.class.getResourceAsStream("store.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(MerchandiseWrapper.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        m_storeInventory = (MerchandiseWrapper) unmarshaller.unmarshal(inStream);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error configuring GeneralStore");
		}		
		
		m_game = game;
		m_storeFront = new GeneralStoreScreen(this);
	
	}
	
	/**
	 * Setter for GameEnviornment reference
	 * @param game Reference to paired GameEnvironment object
	 */
	public void setGameEnvironment(GameEnvironment game)
	{
		m_game = game;
	}
	
	/**
	 * Returns paired GameEnvironment obj
	 * @return GameEnvironment reference
	 */
	public GameEnvironment getGameEnvironment()
	{
		return m_game;
	}
	
	/**
	 * Sets atatched GeneralStoreScreen to visible
	 * @param visible New visibility state
	 */
	public void setVisible(boolean visible)
	{
		try
		{
			m_storeFront.setVisible(visible);
		}
		catch(NullPointerException e)
		{
			System.out.println("No Screen attached to GeneralStore!");
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
		ArrayList<Merchandise> tempList = m_storeInventory.getMerchList();
		//Since it is a shallow copy, this will affect the original as well
		tempList.add(merch);
	}
	
	
	//removeFromShop methods begin below this line

	
	
	/**
	 * @param item item player has bought.
	 */
	public void removeFromShop(Item item) 
	{
		
		removeFromShop((Merchandise) item);		
		
	}
	
	/**
	 * @param animal the animal player has bought.
	 */
	public void removeFromShop(Animal animal) 
	{
		
		removeFromShop((Merchandise) animal);				
	}
	
	/**
	 * @param crop the crop player has bought.
	 */
	public void removeFromShop(Crop crop) 
	{
		
		removeFromShop((Merchandise) crop);				
	}
	
	/**
	 * @param merch the merch player has bought.
	 */
	public void removeFromShop(Merchandise merch) 
	{
		//Create shallow copy of Arraylist inside merchwrapper
		ArrayList<Merchandise> tempList = m_storeInventory.getMerchList();
		//Since it is a shallow copy, this will affect the original as well
		tempList.remove(merch);
	}
	
	
	//methods relevant to the ShoppingCart begin below this line.
	
	
	
	/**  
	 * @return ArrayList Reference to Merchandise currently in cart.
	 */
	public ArrayList<Merchandise> viewCart() {
	
		return m_shoppingCart.getMerchList();
	}
	
	/**
	 * @param merch is either an instance of a crop, animal or item.
	 */
	public void addToCart(Merchandise merch) {
		
		m_shoppingCart.add(merch);
	}
	
	/**
	 * @param merch player has chosen to remove from cart.
	 */
	public void removeFromCart(Merchandise merch) {
		
		m_shoppingCart.remove(merch);
	}
	
	
	
	//methods relevant to purchasing merch in the car begin below. WIP
	
	
	
	/**
	 * The checkout method checks the player can afford the merch in the cart
	 * before giving merch to the player.
	 */
	public void checkout() {
		
		int playersMoney = m_game.getPlayerMoney();
		double discountPercent = m_game.getPurchaseDiscountMod();
		
		//calculation for any discounts.
		double amountRemoved = m_shoppingCart.getCartPrice() * discountPercent;
		int finalCost = (int) (m_shoppingCart.getCartPrice() - amountRemoved); 
		ConfirmPurchaseDialog confirmDialog = new ConfirmPurchaseDialog(this, m_shoppingCart, playersMoney, finalCost);
		confirmDialog.setVisible(true);

	}
	
	/**
	 * Checks if the player has the money and the space to buy everything in the cart
	 * @param playersMoney Amount of money player has
	 * @param finalCost Total adjusted cost of cart
	 * @param cart MerchandiseWrapper of merchandise in cart
	 * @return true if player meets all criteria to purchase the cart
	 */
	public boolean canPurchaseCart(int playersMoney, int finalCost, MerchandiseWrapper cart)
	{
		if (checkBalance(finalCost, playersMoney)) {
			//Check that the farm can fit all of the animals in the cart
			if(m_game.getPlayerAnimals().size() + cart.getAnimals().size() > m_game.getMaxAnimalAmount())
			{
				throw new IllegalStateException(
						String.format("Not enough room for all animals! %d animals in cart, but only %d spaces in farm."
								, cart.getAnimals().size()
								, m_game.getMaxAnimalAmount() - m_game.getPlayerAnimals().size()));
			}
			//Check that the farm has space for all for the crops in the cart
			if(m_game.getPlayerCrops().size() + cart.getCrops().size() > m_game.getMaxCropAmount())
			{
				throw new IllegalStateException(
						String.format("Not enough room for all crops! %d crops in cart, but only %d spaces in farm."
								, cart.getCrops().size()
								, m_game.getMaxCropAmount() - m_game.getPlayerCrops().size()));
			}
		}
		else {
			//Player can't afford the merch they have in the Cart.
			throw new IllegalStateException(String.format("Not enough Money. Total cost is %d but your only have %d in the bank"
					, finalCost
					, playersMoney));
		}
		return true;
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
	
	public ArrayList<Merchandise> purchaseCart(MerchandiseWrapper cart, int finalPrice)
	{
		// subtract the totalCost from the player's money.
		m_game.setPlayerMoney(m_game.getPlayerMoney() - finalPrice);
		
		for(Merchandise m : cart.getMerchList())	
		{
			m_game.addPlayerMerchandise(m);
		}
		
		//Empty the cart of the merch. 
		clearCart();
		m_storeFront.refreshCart();
		m_storeFront.refreshDisplay();
		
		m_game.updateStatusBar();
		
		return cart.getMerchList();
	}
	
	/**
	 * Clears content of GeneralStore cart
	 */
	public void clearCart()
	{
		m_shoppingCart.clear();
		m_storeFront.refreshCart();
	}
	
	
	//getters and setters begin below this line 
	
	/**
	 * 
	 * @return MerchadniseWrapper of all store merchandise
	 */
	public MerchandiseWrapper getMerchandise()
	{
		return m_storeInventory;
	}
	
	/**
	 * @return a list of available animals in store.
	 */
	public ArrayList<Animal> getAnimals() {
		
		return m_storeInventory.getAnimals();
	}
	
	/**
	 * @return an arraylist of items available in store 
	 */
	
	public ArrayList<Item> getItems() {
		
		return m_storeInventory.getItems();
	}

	/**
	 * @return a list of crops available in store 
	 */
	public ArrayList<Crop> getCrops() {
		
		return m_storeInventory.getCrops();
	}	
	
	/**
	 * @return The instance of m_shoppingCart. Has the attributes totalCost and Price.
	 */
	public MerchandiseWrapper getShoppingCart() {
		
		return m_shoppingCart;
	}
	
	/**
	 * 
	 * @return int Amount of money player has
	 */
	public int getPlayerMoney()
	{
		return m_playerMoney;
	}
	
	/**
	 * 
	 * @param money amount to set to
	 */
	public void setPlayerMoney(int money)
	{
		m_playerMoney = money;
	}

	/**
	 * 
	 * @return MerchandiseWrapper All of player's merchandise
	 */
	public MerchandiseWrapper getPlayerMerchandise() {
		return m_game.getPlayerMerchandise();
	}

	public double getPurchaseDiscountMod() {
		return m_game.getPurchaseDiscountMod();
	}
	
}