/**
* The Item class represents any item that can be bought by the
* player and used to provide some benefit to their farm
* 
* Last modified: 29-4-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "item")
public class Item extends Merchandise{
	//Variables
	@XmlElement(name = "name")
	private String m_name;
	@XmlElement(name = "purchasePrice")
	private int m_purchasePrice;
	@XmlElement(name = "boostAmount")
	private int m_boostAmount;
	
	/*
	 * Some items can be used on animals; some can be used on crops.
	 * The following two flags determine what classes it can be used on
	 */
	@XmlElement(name = "forAnimals")
	private boolean m_forAnimals;
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
	public String getName()
	{
		return m_name;
	}
	
	/**
	 * Set new name for Item
	 * @param name new name for Item
	 */
	public void setName(String name)
	{
		m_name = name;
	}
	
	/**
	 * Get price of Item when bought from General Store
	 * @return Price of Item when bought in General Store
	 */
	public int getPurchasePrice()
	{
		return m_purchasePrice;
	}
	
	/**
	 * Set new price of Item when bought from General Store
	 * @param New price for Item
	 */
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
		final StringBuffer sb = new StringBuffer("Item{");
        sb.append("name=").append(m_name);
        sb.append(", purchasePrice='").append(m_purchasePrice).append('\'');
        sb.append(", boostAmount='").append(m_boostAmount).append('\'');
        sb.append(", forAnimals='").append(m_forAnimals).append('\'');
        sb.append(", forCrops='").append(m_forCrops).append('\'');
        sb.append('}');
        return sb.toString();
	}

}
