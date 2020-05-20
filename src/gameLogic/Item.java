package gameLogic;
/**
* The Item class represents any item that can be bought by the
* player and used to provide some benefit to their farm
* 
* Last modified: 1-5-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "item")
public class Item implements Merchandise{
	/**
	 * String representing the item's type
	 */
	@XmlElement(name = "name")
	private String m_name;
	/**
	 * int representing the price of the item from the store
	 */
	@XmlElement(name = "purchasePrice")
	private int m_purchasePrice;
	/**
	 * int representing the numerical boost amount provided by the item
	 */
	@XmlElement(name = "boostAmount")
	private int m_boostAmount;
	
	/*
	 * Some items can be used on animals; some can be used on crops.
	 * The following two flags determine what classes it can be used on
	 */
	/**
	 * Bool representing whether or not the item can be used on animals
	 */
	@XmlElement(name = "forAnimals")
	private boolean m_forAnimals;
	/**
	 * Bool representing whether or not the item can be used on crops
	 */
	@XmlElement(name = "forCrops")
	private boolean m_forCrops;
	
	public Item()
	{
	}
	
	/**
	 * 
	 * @param name				Name of the item
	 * @param purchasePrice		Price of item in General Store
	 * @param boostAmount		Amount of benefit provided when used
	 * @param forAnimals		Represents whether or not an object in the class Animal can use this item
	 * @param forCrops			Represents whether or not an object in the class Crop can use this item
	 * @return null
	 */
	public Item(String name, int purchasePrice, int boostAmount, boolean forAnimals, boolean forCrops)
	{
		m_name = name;
		m_purchasePrice = purchasePrice;
		m_boostAmount = boostAmount;
		m_forAnimals = forAnimals;
		m_forCrops = forCrops;
	}
	
	/**
	 * Create a deep copy of an Item
	 * @param source Item to create deep copy of
	 * @return Deep copy of source Item
	 */
	public Item copy(Item source)
	{
		return (new Item(m_name, m_purchasePrice, m_boostAmount, m_forAnimals, m_forCrops));
	}
	
	/**
	 * Get Item's name
	 * @return Item's name
	 */
	@Override
	public String getName()
	{
		return m_name;
	}
	
	/**
	 * Set new name for Item
	 * @param name new name for Item
	 */
	@Override
	public void setName(String name)
	{
		m_name = name;
	}
	
	/**
	 * Get price of Item when bought from General Store
	 * @return Price of Item when bought in General Store
	 */
	@Override
	public int getPurchasePrice()
	{
		return m_purchasePrice;
	}
	
	/**
	 * Set new price of Item when bought from General Store
	 * @param New price for Item
	 */
	@Override
	public void setPurchasePrice(int price)
	{
		m_purchasePrice = price;
	}
	
	/**
	 * Get boost value of Item
	 * @return Amount of benefit item provides to animal/crop it is used on
	 */
	public int getBoostAmount()
	{
		return m_boostAmount;
	}
	
	/**
	 * Set new boost value for item
	 * @param amount Set amount of boost item provides when used
	 */
	public void setBoostAmount(int amount)
	{
		m_boostAmount = amount;
	}
	
	/**
	 * Get item compatibility with Animal objects
	 * @return True: Item can be used on animal; False: Item cannot be used on animals
	 */
	public boolean getForAnimals()
	{
		return m_forAnimals;
	}
	
	/**
	 * Set item compatibility with Animal objects
	 * @param newVal Boolean to set - True: Can be used on animals. False: Cannot be used on animals
	 */
	public void setForAnimals(boolean newVal)
	{
		m_forAnimals = newVal;
	}
	
	/**
	 * Get item compatibility with Crop objects
	 * @return True: Item can be used on crops; False: Item cannot be used on crops
	 */
	public boolean getForCrops()
	{
		return m_forCrops;
	}
	
	/**
	 * Set item compatibility with Crop objects
	 * @param newVal Boolean to set - True: Can be used on crops. False: Cannot be used on crops
	 */
	public void setForCrops(boolean newVal)
	{
		m_forCrops = newVal;
	}
	
	@Override
	public String toString()
	{
		String output = "Item{";
        output.concat("name=" + m_name);
        output.concat(", purchasePrice='" + m_purchasePrice + '\'');
        output.concat(", boostAmount='" + m_boostAmount + '\'');
        output.concat(", forAnimals='" + m_forAnimals + '\'');
        output.concat(", forCrops='" + m_forCrops + '\'');
        output.concat("}");
        return output;
	}

}
