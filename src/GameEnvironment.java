import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEnvironment {
	private ArrayList<Merchandise> m_selectedMerchandise;
	private Farm m_farm;
	private GeneralStore m_store;
	
	//scanner as an attribute
	private Scanner askPlayer;
	
	public GameEnvironment()
	{
		askPlayer = new Scanner(System.in);
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
		String output;
		try
		{
			output = m_farm.tendCrops(crop);
			System.out.println(output);
		}
		catch(IllegalStateException e)
		{
			System.out.println(e);
		}
		
	}
	
	private void tendCrops(Crop crop, Item item)
	{
		String output;
		try
		{
			output = m_farm.tendCrops(crop, item);
			System.out.println(output);
		}
		catch(IllegalStateException e)
		{
			System.out.println(e);
		}
		
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
		m_farm.playWithAnimal(animal);
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
		m_farm.setMaxAnimalAmount(m_farm.getMaxAnimalAmount() + 1);
		m_farm.setMaxCropAmount(m_farm.getMaxCropAmount() + 1);
		m_farm.setRemainingActions(m_farm.getRemainingActions() - 1);
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
		boolean useItem = true;
		try
		{
			Item item;
			Crop crop;
			Animal animal;
			switch(action) 
			{
				case TEND_CROP:
					try
					{
						crop = selectCrop();
					}
					catch (IllegalStateException e)
					{
						System.out.println(e);
						break;
					}
					catch(RuntimeException e)
					{
						System.out.println(e);
						break;
					}

					//Have to initialize item to something here, otherwise compiler thinks there's an issue later
					//even though that issue is handled'
					item = new Item();
					try
					{
						item = selectActionItem(false); 
					}
					catch(IllegalStateException e)
					{
						System.out.println(e);
						break;
					}
					catch(NullPointerException e)
					{
						useItem = false;
						System.out.println(e);
					}
					
					if(useItem)
					{
						tendCrops(crop, item);
					}
					else
					{
						 tendCrops(crop);
					}
					break;
				case FEED_ANIMAL: 
					try
					{
						animal = selectAnimal();
					}
					catch(IllegalStateException e)
					{
						System.out.println(e);
						break;
					}
					try
					{
						item = selectActionItem(true); 
					}
					catch(IllegalStateException e)
					{
						System.out.println(e);
						break;
					}
					
					feedAnimal(animal, item);
					break;
					
				case PLAY_WITH_ANIMAL: 
					try
					{
						animal = selectAnimal();
					}
					catch(IllegalStateException e)
					{
						System.out.println(e);
						break;
					}
					playWithAnimal(animal);
					break;
				case HARVEST_CROP: 
					try
					{
						crop = selectCrop();
					}
					catch (IllegalStateException e)
					{
						System.out.println(e);
						break;
					}
					catch(RuntimeException e)
					{
						System.out.println(e);
						break;
					}
					harvestCrop(crop);
				case TEND_LAND: 
					tendLand();
					break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return m_farm.getRemainingActions();
	}
	
	/**
	 * Method to select a crop from all crops in the farm
	 * @return crop to use
	 * @throws IllegalStateException if no crops are owned, or if the user cancels out of the selection
	 * @throws RuntimeException Occurs if the user makes a selection and then the name of the selection can't be found in the crop list. Shouldn't ever happen.
	 */
	public Crop selectCrop()
	{
		//Command line code to select a crop. 1 based for user readability
		ArrayList<Crop> crops = m_farm.getCrops();
		if(crops.size() == 0)
		{
			throw new IllegalStateException("No crops found!");
		}
		//Arraylist to keep track of which type of crop has already been listed
		ArrayList<String> listedCrops = new ArrayList<String>();
		for(int i = 1; i <= crops.size(); i++)
		{
			String name = crops.get(i - 1).getName();
			//Check if the crop type has already been printed - if not, print it
			if(!listedCrops.contains(name))
			{
				System.out.println(String.format("%d: %s\n", i, name));
				listedCrops.add(name);
			}
		}
		//Give user option to cancel
		System.out.println(String.format("%d: Cancel", crops.size()+ 1));
		System.out.println("Select a crop: ");
		int input = -1;
		do
		{
			input = askPlayer.nextInt();
		} while (input < 1 && input > crops.size() + 1);
		
		if (input == crops.size() + 1)
		{
			throw new IllegalStateException("User cancelled operation");
		}
		//Need to find the correct crop based on player selection, return once it is found
		for(Crop c : crops)
		{
			if(c.getName() == listedCrops.get(input - 1));
			return c;
		}
		//Shouldn't be able to get to this point
		throw new RuntimeException("Unknown error occured.\n"
				+ "Occured at end of GameEnvironment.selectCrop.\n"
				+ "How did you even get here?");
	}
	
	/**
	 * Method for allowing the user to choose an item after 
	 * they have chosen an action that allows/requires item use
	 * @param required WHether or not the user is required to select an item
	 * @return An item to use for the selected action
	 * @throws IllegalStateException if the farm has no items or if the user cancels out of the selection
	 * @throws NullPointerException Not an error, occurs when the user doesn't want to use an item AND not using an item is allowed
	 */
	public Item selectActionItem(boolean required) throws Exception
	{
		//Command line code to select a crop. 1 based for user readability
		ArrayList<Item> items = m_farm.getItems();
		if(items.size() == 0)
		{
			throw new IllegalStateException("No items found.");
		}
		
		//Print an extra option if selection no item is an option
		if(!required)
		{
			System.out.println("0: Don't use item\n");			
		}
		for(int i = 1; i <= items.size(); i++)
		{
			String name = items.get(i - 1).getName();
			System.out.println(String.format("%d: %s\n", i, name));
			
		}
		//Give user option to cancel
		System.out.println(String.format("%d: Cancel", items.size()+ 1));
		
		System.out.println("Select an item: ");
		int input = -1;
		//Determine what the lowest valid user input is: 1 if required, 0 if not
		int validFloor = (required?1:0);
		do
		{
			input = askPlayer.nextInt();
		} while (input < validFloor && input > items.size() + 1);
		
		if(input == 0)
		{
			throw new NullPointerException("Chose no item");
		}
		else if (input == items.size() + 1)
		{
			throw new IllegalStateException("User cancelled operation");
		}
		return items.get(input - 1);
	}
	
	/**
	 * 
	 * @return Animal chosen by user
	 * @throws IllegalStateException if the user chose no animal, or if the user cancelled out of the selection
	 */
	public Animal selectAnimal()
	{
		//Command line code to select a crop. 1 based for user readability
		ArrayList<Animal> animals = m_farm.getAnimals();
		if(animals.size() == 0)
		{
			throw new IllegalStateException("No animals found.");
		}
		
		
		for(int i = 1; i <= animals.size(); i++)
		{
			String name = animals.get(i - 1).getName();
			System.out.println(String.format("%d: %s\n", i, name));
			
		}
		//Give user option to cancel
		System.out.println(String.format("%d: Cancel", animals.size()+ 1));
		
		System.out.println("Select an animal: ");
		int input = -1;
		do
		{
			input = askPlayer.nextInt();
		} while (input < 0 && input > animals.size() + 1);
		
		if (input == animals.size() + 1)
		{
			throw new IllegalStateException("User cancelled operation");
		}
		return animals.get(input - 1);
	}

	
	/**
	 * End the game day.
	 * If days remaining in game <= 0, end the game
	 */
	public void endDay()
	{
		try
		{
			m_farm.endDay();
		}
		catch (IllegalStateException e)
		{
			endGame();
		}
		
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
