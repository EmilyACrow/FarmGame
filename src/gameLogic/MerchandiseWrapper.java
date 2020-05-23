package gameLogic;
/**
* Wrapper for a Merchandise Arraylist. Sole purpose is to enable JAXB XML Parsing
* 
* Implements XmlElements based on code from Robby Cornelissen's answer to this question on SO
* (https://stackoverflow.com/questions/42289733/jaxb-xmlelement-with-multiple-names-and-types)
* Structure of this class based on 
* (https://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/)
* 
* Last modified: 13-5-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "merch")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchandiseWrapper {
	//Constants representing the class names of each possible class in MerchandiseWrapper
	public static final String ANIMAL = "Animal";
	public static final String CROP = "Crop";
	public static final String ITEM = "Item";
	@XmlElements({
        @XmlElement(name="animal",type=Animal.class),
        @XmlElement(name="crop",type=Crop.class),
        @XmlElement(name="item",type=Item.class)
    })
	private ArrayList<Merchandise> merchList = new ArrayList<Merchandise>();
	
	private int cartPrice = 0;

 
	public MerchandiseWrapper()
	{
		
	}
	
	public MerchandiseWrapper(ArrayList<Merchandise> source)
	{
		for(Merchandise item : source)
    	{
    		merchList.add(item.clone());
    	}
	}
	
	/**
	 * Returns deep copy of the ArrayList contained in the wrapper
	 * @return ArrayList<Merchandise> Deep copy of contained ArrayList
	 */
    public ArrayList<Merchandise> getMerchList() 
    {
    	ArrayList<Merchandise> newList = new ArrayList<Merchandise>();
    	for(Merchandise m : merchList)
    	{
    		newList.add(m.clone());
    	}
        return newList;
    }
    
    /**
     * Changes which ArrayList is being pointed to
     * @param merchList ArrayList<Merchandise> new list to be pointed at
     */
    public void setMerchList(ArrayList<Merchandise> list) 
    {
    	clear();
    	for(Merchandise m : list)
    	{
    		add(m);
    	}
        
    } 
    
    /**
     * makes this MerchandiseWrapper a deep copy of the source arraylist
     * @param source ArrayList<Merchandise> to deep copy
     */
    public void clone(ArrayList<Merchandise> source)
    {
    	merchList.clear();
    	for(Merchandise item : source)
    	{
    		merchList.add(item);
    	}
    }
    
    /**
     * Makes a deep copy of the MerchandiseWrapper
     * @return
     */
    public MerchandiseWrapper clone()
    {
    	
    	return new MerchandiseWrapper(merchList);
    }
    
    /**
     * Overload getAnimals to default to the local merchList
     * @return ArrayList<Animal> deep copy of all animals in the local merchlist
     */
    public ArrayList<Animal> getAnimals()
    {
    	return getAnimals(merchList);
    }
    
    /**
     * Get all animals out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Arraylist<Animal> deep copy of all animals in the param merchlist
     */
    public ArrayList<Animal> getAnimals(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Animal> animals = new ArrayList<Animal>();
    	for(Merchandise item : merchList)
    	{
    		if(item.getClass().getSimpleName().contentEquals("Animal"))
    		{
    			animals.add((Animal)item.clone());
    		}
    	}
    	return animals;
    }
    
    
    /**
     * Overload getCropss to default to the local merchList
     * @return ArrayList<Crop> deep copy of all crops in the local merchlist
     */
    public ArrayList<Crop> getCrops()
    {
    	return getCrops(merchList);
    }
    
    /**
     * Get all crops out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return ArrayList<Crop> deep copy of all crops in the param merchlist
     */
    public ArrayList<Crop> getCrops(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Crop> crops = new ArrayList<Crop>();
    	for(Merchandise crop : merchList)
    	{
    		if(crop.getClass().getSimpleName().contentEquals("Crop"))
    		{
    			crops.add((Crop)crop.clone());
    		}
    	}
    	return crops;
    }
    
    /**
     * Get all items out of a Merchandise arraylist
     * @return ArrayList<Item> deep copy of all items in the local merchlist
     */
    public ArrayList<Item> getItems()
    {
    	return getItems(merchList);
    }
    
    /**
     * Get all items out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return ArrayList<Item> deep copy of all items in the param merchlist
     */
    public ArrayList<Item> getItems(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Item> items = new ArrayList<Item>();
    	for(Merchandise item : merchList)
    	{
    		//Use .contenEquals instead of == because for some reason == isn't working here
    		if(item.getClass().getSimpleName().contentEquals("Item"))
    		{
    			items.add((Item)item.clone());
    		}
    	}
    	return items;
    }
    
    /**
     * Add a Merchandise Obj to the ArrayList
     * @param merch Merchandise
     */
    public void add(Merchandise merch)
    {
    	merchList.add(merch);
    	cartPrice += merch.getPurchasePrice();
    }
    
    /**
     * Remove a Merchandise Obj from the arraylist
     * @param merch Merchandise Obj to remove
     * @return true if merch successfully removed
     */
    public boolean remove(Merchandise merch)
    {
    	if(merchList.remove(merch))
    	{
    		cartPrice -= merch.getPurchasePrice();
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * Clears the ArrayList inside the wrapper
     */
    public void clear()
    {
    	cartPrice = 0;
    	merchList.clear();
    }
    
    /**
     * Returns total, unmodified purchase price of all Merchandise Obj in cart
     * @return int Total, unmodified price of everything in cart
     */
    public int getCartPrice()
    {
    	return cartPrice;
    }
    /**
     * Get number of Merchandise Objects of cart
     * @return int number of Merchandise Obj
     */
	public int size() {
		return merchList.size();
	}
}
