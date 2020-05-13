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
	@XmlElements({
        @XmlElement(name="animal",type=Animal.class),
        @XmlElement(name="crop",type=Crop.class),
        @XmlElement(name="item",type=Item.class)
    })
	private ArrayList<Merchandise> merchList = new ArrayList<Merchandise>();

 
	public MerchandiseWrapper()
	{
		
	}
	
	public MerchandiseWrapper(ArrayList<Merchandise> source)
	{
		for(Merchandise item : source)
    	{
    		merchList.add(item);
    	}
	}
	
    public ArrayList<Merchandise> getMerchList() 
    {
        return merchList;
    }
 
    public void setMerchList(ArrayList<Merchandise> merchList) 
    {
        this.merchList = merchList;
    } 
    
    public void clone(ArrayList<Merchandise> source)
    {
    	merchList.clear();
    	for(Merchandise item : source)
    	{
    		merchList.add(item);
    	}
    }
    
    /**
     * Overload getAnimals to default to the local merchList
     * @return Arraylist of all animals in the local merchlist
     */
    public ArrayList<Animal> getAnimals()
    {
    	return getAnimals(merchList);
    }
    
    /**
     * Get all animals out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Arraylist of all animals in the param merchlist
     */
    public ArrayList<Animal> getAnimals(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Animal> animals = new ArrayList<Animal>();
    	for(Merchandise item : merchList)
    	{
    		if(item.getClass().getSimpleName() == "Animal")
    		{
    			animals.add((Animal)item);
    		}
    	}
    	return animals;
    }
    
    
    /**
     * Overload getCropss to default to the local merchList
     * @return Arraylist of all crops in the local merchlist
     */
    public ArrayList<Crop> getCrops()
    {
    	return getCrops(merchList);
    }
    
    /**
     * Get all crops out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Arraylist of all crops in the param merchlist
     */
    public ArrayList<Crop> getCrops(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Crop> crops = new ArrayList<Crop>();
    	for(Merchandise item : merchList)
    	{
    		if(item.getClass().getSimpleName() == "Crop")
    		{
    			crops.add((Crop)item);
    		}
    	}
    	return crops;
    }
    
    /**
     * Get all items out of a Merchandise arraylist
     * @return Arraylist of all items in the local merchlist
     */
    public ArrayList<Item> getItems()
    {
    	return getItems(merchList);
    }
    
    /**
     * Get all items out of a Merchandise arraylist
     * @param merchList Merchandise arraylist
     * @return Arraylist of all items in the param merchlist
     */
    public ArrayList<Item> getItems(ArrayList<Merchandise> merchList)
    {
    	ArrayList<Item> items = new ArrayList<Item>();
    	for(Merchandise item : merchList)
    	{
    		if(item.getClass().getSimpleName() == "Item")
    		{
    			items.add((Item)item);
    		}
    	}
    	return items;
    }
}
