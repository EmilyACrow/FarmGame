import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameEnvironment {
	private ArrayList<Merchandise> m_selectedMerchandise;
	private Farm m_farm;
	private GeneralStore m_store;
	
	public GameEnvironment()
	{
	}
	
	public void Run()
	{
		showMainMenu();
	}
	
	private void showMainMenu()
	{
		
	}
	
	/**
	 * 
	 */
	private void createNewGame(String farmName, FarmType farmType, Farmer farmer)
	{		
		m_farm = new Farm(farmName, farmType, farmer);
		m_store = new GeneralStore();
	}
	
	private void removeFromFarm(Merchandise merch)
	{
		m_farm.removeMerchandise(merch);
	}
	
	private void addToFarm(Merchandise merch)
	{
		m_farm.addMerchandise(merch);
	}
	
	private void addFarmMoney(int amount)
	{
		m_farm.addMoney(amount);
	}
	
	private void removeFarmMoney(int amount)
	{
		try
		{
			m_farm.subtractMoney(amount);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void tendCrops(Crop crop)
	{
		String cropName = crop.getName();
		ArrayList<Crop> crops = m_farm.getCrops();
		int counter = 0;
		for(Crop c : crops)
		{
			if (c.getName() == cropName)
			{
				try
				{
					c.tendToCrop();
					counter++;
				}
				//The only time an exception is thrown is if the crop is already ready to harvest
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		System.out.println(String.format("%d crops tended to.", counter));
	}
	
	private void feedAnimal(Animal animal, Item item)
	{
		try
		{
			animal.useItem(item);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void playWithAnimal(Animal animal)
	{
		animal.setHappiness(m_farm.MAX_ANIMAL_HAPPINESS);
	}
	
	private void harvestCrop(Crop crop)
	{
		try
		{
			m_farm.addMoney(crop.harvest());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void tendLand()
	{
		
	}
	
	public int getCropStatus(Crop crop)
	{
		return crop.getDaysUntilHarvest();
	}
	
	public int[] getAnimalStatus(Animal animal)
	{
		//Health, then happiness
		int[] status = new int[2];
		status[0] = animal.getHealth();
		status[1] = animal.getHappiness();
		return status;
	}
	
	public void viewFarmStatus() 
	{

		//Print the details of each animal, item, and crop
		for(Animal animal : m_farm.getAnimals())
		{
			System.out.println(animal.toString());
		}
		for(Crop crop : m_farm.getCrops())
		{
			System.out.println(crop.toString());
		}
		for(Item item : m_farm.getItems())
		{
			System.out.println(item.toString());
		}
		//Print all farm details
		System.out.println(m_farm);
		
	}
	
	public int takeAction(PossibleAction action, ArrayList<Merchandise> selection)
	{
		if(m_farm.getRemainingActions() == 0)
		{
			System.out.println("No remaining actions!");
			return 0;
		}
		try
		{
			switch(action) 
			{
				case TEND_CROP: tendCrops((Crop)selection.get(0)); break;
				case FEED_ANIMAL: feedAnimal(); break;
				case PLAY_WITH_ANIMAL: break;
				case HARVEST_CROP: break;
				case TEND_LAND: break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return m_farm.getRemainingActions();
	}
	
	/**
	 * End the game day.
	 * If days remaining in game <= 0, end the game
	 */
	public void endDay()
	{
		if(m_farm.getRemainingDays() <= 0)
		{
			endGame();
			return;
		}
		m_farm.setRemainingDays(m_farm.getRemainingDays() - 1);
		
	}
	
	public ArrayList<Merchandise> selectMerch()
	{
		ArrayList<Merchandise> selection = new ArrayList<Merchandise>();
		return selection;
	}
	
	public void visitGeneralStore()
	{
		
	}
	
	private void endGame()
	{
		
	}
}
