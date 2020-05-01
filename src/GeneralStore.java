import java.util.ArrayList;

/**
 * The GeneralStore class is where the player can view or purchase available merchandise.
 * They can also view their current balance.
 * 
 * Last modified: 1-05-2020
 * 
 * created: 1-05-2020
 * @author Kenn Leen Fulgencio
 */

public class GeneralStore {
	
	private ArrayList<Item> m_itemsAvailable;
	private ArrayList<Animal> m_animalsAvailable;
	private ArrayList<Crop> m_cropsAvailable;
	
	private ShoppingCart m_shoppingCart;
	
	
	/**
	 * This constructor method creates empty lists for items, animals and crops in GeneralStore.
	 */
	public GeneralStore() {
		
		m_itemsAvailable = new ArrayList<Item>();
		m_animalsAvailable = new ArrayList<Animal>();
		m_cropsAvailable = new ArrayList<Crop>();
		
	
	}
	
	
	/**
	 * The checkout method will go through all the merch in the ShoppingCart.
	 */
	public ArrayList<Merchandise> checkout() {
		
		//int totalSum;	
		
	}
	
	
	
	/**
	 * checkBalance checks if the player can afford the sum total of the merch they want to buy. 
	 * @return True if player can afford merch. False if player can't.
	 */
	private boolean checkBalance() {
		/*
		//checks if player's money is greater or equal to  total sum . 
		if(Farm.getMoney() >= {
			
			return true;
			
		}
		
		// Player can't afford the merch
		else {
			return false;
		} */

	}
	
	/**  
	 * @return The current merch in player's shopping cart. 
	 */
	public ShoppingCart viewCart() {
		
		return m_shoppingCart;
	}
	
	
	}
	
	
	
	
	
	

