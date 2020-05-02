/**
* The Animal class represents an animal that can be bought by the
* player and placed on their farm
* 
* Last modified: 1-5-20 by Dmitri Smith
*
* Created: 29-4-20
* @author  Dmitri Smith
*/
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "animal")
public class Animal implements Merchandise{
	/**
	 * String representing the animal's name given by the player
	 */
	@XmlElement(name = "name")
	private String m_name;
	
	/**
	 * String representing the animal's species
	 */
	@XmlElement(name = "species")
	private String m_species;
	
	/**
	 * int representing the price of the animal from the store
	 */
	@XmlElement(name = "purchasePrice")
	private int m_purchasePrice;
	
	/**
	 * int representing the animal's health; 
	 * scale of 0-100 with 100 being perfect health and 0 being dead
	 * can be raised by feeding the animal an item
	 */
	private int m_health;
	
	/**
	 * int representing the animal's happiness
	 * scale of 0-100 with 100 being very happy and 0 being miserable
	 * Factors into anima's bonus amount
	 * can be raised by playing with animal
	 */
	private int m_happiness;
	
	/**
	 * Amount of cash given to farmer at end of each day
	 */
	@XmlElement(name = "dailyBonus")
	private int m_dailyBonus;
	
	/**
	 * Empty default constructor for JAXB
	 */
	public Animal()
	{
		
	}
	
	/**
	 * 
	 * @param name Name of the animal
	 * @param species Species of the animal
	 * @param purchasePrice Price to buy the animal from the General Store
	 * @param dailyBonus Amount of money given to the player at the end of each day
	 */
	public Animal(String name, String species, int purchasePrice, int dailyBonus)
	{
		m_name = name;
		m_species = species;
		m_purchasePrice = purchasePrice;
		m_dailyBonus = dailyBonus;
		
		//Health and happiness always start at 100
		m_health = 100;
		m_happiness = 100;
	}
	
	/**
	 * Use item to increase animal's health
	 * @param item Item to use on animal
	 * @return True if item is used
	 * @throws RuntimeException if item cannot be used
	 */
	public boolean useItem(Item item)
	{
		//First check if animals can use the item
		if(!item.getForAnimals())
		{
			throw new RuntimeException(String.format("%1$s cannot be used on animals - %1$s not used.", item.getName()));
		}
		
		//Only use the item if the animal is not at full health
		if(m_health >= 100)
		{
			throw new RuntimeException(String.format("%s is completely healthy - %s not used.", m_name, item.getName()));
		}
		
		m_health += item.getBoostAmount();
		if(m_health > 100)
		{
			m_health = 100;
		}
		
		return true;
	}
	
	/**
	 * @return Name of animal
	 */
	@Override
	public String getName()
	{
		return m_name;
	}
	
	/**
	 * 
	 * @param name new name for the animal
	 */
	@Override
	public void setName(String name)
	{
		m_name = name;
	}
	
	/**
	 * 
	 * @return Species of the animal
	 */
	public String getSpecies()
	{
		return m_species;
	}
	
	/**
	 * 
	 * @param species New species to set the animal to
	 */
	public void setSpecies(String species)
	{
		m_species = species;
	}
	
	/**
	 * @return Price to buy animal from General Store
	 */
	@Override
	public int getPurchasePrice()
	{
		return m_purchasePrice;
	}
	
	/**
	 * 
	 * @param price New price to buy animal from General Store
	 */
	@Override
	public void setPurchasePrice(int price)
	{
		m_purchasePrice = price;
	}
	
	/**
	 * 
	 * @return Amount of cash awarded at the end of each day
	 */
	public int getDailyBonus()
	{
		return m_dailyBonus;
	}
	
	/**
	 * 
	 * @param bonus New amount of cash to be awarded at the end of each day
	 */
	public void setDailyBonus(int bonus)
	{
		m_dailyBonus = bonus;
	}
	
	/**
	 * 
	 * @return CUrrent health of the animal
	 */
	public int getHealth()
	{
		return m_health;
	}
	
	/**
	 * 
	 * @param health Amount to set the animal's health to
	 */
	public void setHealth(int health)
	{
		m_health = health;
	}
	
	/**
	 * 
	 * @return Happiness level of the animal
	 */
	public int getHappiness()
	{
		return m_happiness;
	}
	
	/**
	 * 
	 * @param happiness New amount to set the animal's happiness to
	 */
	public void setHappiness(int happiness)
	{
		m_happiness = happiness;
	}
	
	@Override
	/**
	 * Override default toString. Modification of Eclipse generated toString to make more readable
	 * @return String with class name followed by all private variables and their values in curly brackets
	 */
	public String toString()
	{
		String output = "Animal{";
        output.concat("name=" + m_name);
        output.concat(", specieces='" + m_species + '\'');
        output.concat(", purchasePrice='" + m_purchasePrice + '\'');
        output.concat(", health='" + m_health + '\'');
        output.concat(", happiness'" + m_happiness + '\'');
        output.concat(", dailyBonus='" + m_dailyBonus + '\'');
        output.concat("}");
        return output.toString();
	}
}
