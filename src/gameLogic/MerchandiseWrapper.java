package gameLogic;
/**
* Wrapper for a Merchandise Arraylist. Sole purpose is to enable JAXB XML Parsing
* 
* Implements XmlElements based on code from Robby Cornelissen's answer to this question on SO
* (https://stackoverflow.com/questions/42289733/jaxb-xmlelement-with-multiple-names-and-types)
* Structure of this class based on 
* (https://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/)
* 
* Last modified: 22-5-20 by Dmitri Smith
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
	public static final String ANIMAL = Animal.class.getSimpleName();
	public static final String CROP = Crop.class.getSimpleName();
	public static final String ITEM = Item.class.getSimpleName();
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
	
	/**
	 * Create new MerchandiseWrapper around target ArrayList
	 * @param source ArrayList to wrap
	 */
	public MerchandiseWrapper(ArrayList<Merchandise> source)
	{
		for(Merchandise item : source)
    	{
    		merchList.add(item);
    	}
	}
	
	/**
	 * Returns reference to the ArrayList contained in the wrapper
	 * @return ArrayList of Merchandise Reference to contained ArrayList
	 */
    public ArrayList<Merchandise> getMerchList() 
    {
        return merchList;
    }
    
    /**
     * Changes which ArrayList is being pointed to
     * @param list ArrayList of Merchandise, new list to be pointed at
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
     * @param source ArrayList of Merchandise to deep copy
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
     * @return MerchandiseWrapper clone of the current MerchandiseWrapper
     */
    public MerchandiseWrapper clone()
    {
    	ArrayList<Merchandise> newList = new ArrayList<Merchandise>();
    	for(Merchandise m : merchList)
    	{
    		newList.add(m.clone());
    	}
    	return new MerchandiseWrapper(newList);
    }
    
    /**
     * Returns a reference to a single Merchandise Object at position index
     * @param index location in array
     * @return Reference to object at index i
     */
    public Merchandise get(int index)
    {
    	return merchList.get(index);
    }
    
    /**
     * Overload getAnimals to default to the local merchList
     * @return Reference to all animals in the local merchlist
     */
    public ArrayList<Animal> getAnimals()
    {
    	return getAnimals(merchList);
    }
    
    /**
     * Get all animals out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Reference to all animals in the param merchlist
     */
    public ArrayList<Animal> getAnimals(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Animal> animals = new ArrayList<Animal>();
    	for(Merchandise item : merchList)
    	{
    		if(item.getClass().getSimpleName().contentEquals("Animal"))
    		{
    			animals.add((Animal)item);
    		}
    	}
    	return animals;
    }
    
    
    /**
     * Overload getCropss to default to the local merchList
     * @return Reference to all crops in the local merchlist
     */
    public ArrayList<Crop> getCrops()
    {
    	return getCrops(merchList);
    }
    
    /**
     * Get all crops out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Reference to all crops in the param merchlist
     */
    public ArrayList<Crop> getCrops(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Crop> crops = new ArrayList<Crop>();
    	for(Merchandise crop : merchList)
    	{
    		if(crop.getClass().getSimpleName().contentEquals("Crop"))
    		{
    			crops.add((Crop)crop);
    		}
    	}
    	return crops;
    }
    
    /**
     * Get all items out of a Merchandise arraylist
     * @return Reference to all items in the local merchlist
     */
    public ArrayList<Item> getItems()
    {
    	return getItems(merchList);
    }
    
    /**
     * Get all items out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Reference to all items in the param merchlist
     */
    public ArrayList<Item> getItems(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Item> items = new ArrayList<Item>();
    	for(Merchandise item : merchList)
    	{
    		//Use .contenEquals instead of == because for some reason == isn't working here
    		if(item.getClass().getSimpleName().contentEquals("Item"))
    		{
    			items.add((Item)item);
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
    	//The cart price might have been changed if some of the merchandise was passed out by reference
    	//So, update the cart price before returning it
    	updateCartPrice();
    	
    	return cartPrice;
    }
    
    /**
     * Update the cartPrice
     * Necessary because the MerchandiseWrapper allows the merchandise inside to be accessed by reference, so their cost values could be changed
     */
    private void updateCartPrice()
    {
    	int price = 0;
    	for(Merchandise m : merchList)
    	{
    		price += m.getPurchasePrice();
    	}
    	cartPrice = price;
    }
    
    /**
     * Get number of Merchandise Objects of cart
     * @return int number of Merchandise Obj
     */
	public int size() {
		return merchList.size();
	}
}
