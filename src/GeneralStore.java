import java.util.ArrayList;

/**
 * The GeneralStore class is where the player can view or purchase available merchandise.
 * They can also view their current balance.
 * 
 * Last modified: 2-05-2020
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
	 * 
	 * @param item An instance of Item class.
	 */
	public void addItem(Item item) {
		
		m_itemsAvailable.add(item);
	}
	
	/**
	 * 
	 * @param animal An instance of Animal class.
	 */
	public void addAnimal(Animal animal) {
		
		m_animalsAvailable.add(animal);
	}
	
	/**
	 * 
	 * @param crop An instance of Animal class.
	 */
	public void addCrop(Crop crop) {
		
		m_cropsAvailable.add(crop);
	}
	
	/**  
	 * @return The current merch in player's shopping cart. 
	 */
	public ShoppingCart viewCart() {
		
		return m_shoppingCart;
	}
	
	
	}
