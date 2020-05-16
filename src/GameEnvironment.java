
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class GameEnvironment {
	private ArrayList<Merchandise> m_selectedMerchandise;
	private Farm m_farm;
	private GeneralStore m_store;
	
	//scanner as an attribute
	private Scanner askPlayer;
	
	
	/**
	 * the constructor method calls methods that use the scanner to prompt player to input data 
	 * required to create the Farm class. 
	 */
	public GameEnvironment()
	{
		askPlayer = new Scanner(System.in);
		
		System.out.println("Enter the farmer's name:");
		String farmerName = askName();
		
		
		System.out.println("Enter the farmer's age:");
		int farmerAge = askAge();
			
		
		//create a Farmer instance
		Farmer farmer = new Farmer(farmerName, farmerAge);
		
		System.out.println("Enter name of Farm:");
		String farmName = askName();
		
		
		
		System.out.println("Enter number of a type of farm.");
		System.out.println("Pick from: "+
				"\n1.Ranch" +
				"\n2.Commercial" +
				"\n3Produce" +
				"\n4Mixed");
		FarmType farmType = askFarmType();
		
		
		//Creates a new game
		createNewGame(farmName, farmType, farmer);
		
				
		
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
	
	
	
	
	
	
	
	
	
	/*
	 * Methods for prompting the player with the Scanner begin below.
	 * 
	 */
	
	
	
	
	
	/**
	 * This method asks the player to input a name.
	 * @return the farmer's name.
	 */
	public String askName() {
		
		//this avoids the 'variable may not be initialized' error
		String name = "kiwifruit";
		
		Boolean isValid = false;
		
		//This will continue asking for a name if not valid
		
		while(!(isValid)) {
			name = askPlayer.next();
			name += askPlayer.nextLine();
			
			
			isValid = isValidInput(name);
		
		}
		
		return name;
	
		}
	
	
	/**
	 * Ask user to input age for farmer.
	 * @return farmer's age
	 */
	public int askAge() {
		//this avoids the 'variable may not be initialized' error
		int farmerAge = numberOnly();
		
		return farmerAge;
	}
	
	
	/**
	 * ask player to enter a number corresponding to a type of farm.
	 * 
	 * @return a number that represents a farmType's value
	 */
	public FarmType askFarmType() {
		
		//avoids the variable is NULL error.
		FarmType farmType = FarmType.RANCH;
		int playerSelection = 0;
		
		//keeps asking player until a valid number is chosen.
		while((!(playerSelection == 1)) && (!(playerSelection == 2)) && (!(playerSelection == 3) && (!(playerSelection == 4)) )) {
			
			System.out.println("enter a number above:");
			playerSelection = numberOnly();
			
			
			if((!(playerSelection == 1)) && (!(playerSelection == 2)) && (!(playerSelection == 3) && (!(playerSelection == 4)) )) {
				
				System.out.println(playerSelection + " is not a valid number.");
			}
			
			
		}
		
		
		
		//Depending on the number selected, it will assign the Farm Type. 
		if (playerSelection == 1) {
			farmType = FarmType.RANCH;
		}
		
		else if (playerSelection == 2) {
			farmType = FarmType.COMMERCIAL;
		}
		
		else if (playerSelection == 3) {
			farmType = FarmType.PRODUCE;
		}
		
		else if (playerSelection == 4) {
			farmType = FarmType.MIXED;
		}

		
		return farmType;
	}
	
	

	
	/*
	 * Methods that check if the player's input is valid begin below here
	 * 
	 */
	
	
	
	/**
	 * Only returns a player's input if it is an Integer. 
	 * @return an integer
	 */
	public int numberOnly() {
		
		//this avoids the 'variable may not be initialized' error
		int validNumber = 100;
		
		Boolean isValid = false;
		
		//loop back to the beginning of the try-catch block. 
		while(!(isValid)) {
			
			try {
				
				//scanner only accepts an integer.
				validNumber = askPlayer.nextInt();
				
			}
			
			//tells player if they enter something that isn't a number.
			catch(InputMismatchException e) {
				System.out.println( "invalid input. Please only enter a number: ");
				
				//assigns any skipped lines to nothing. so it should leave the scanner free of any characters.
				askPlayer.nextLine();
				continue;
			
			}

			//assigns any skipped lines to nothing. so it should leave the scanner free of any characters.
			askPlayer.nextLine();
			
			isValid = true;
			
			
		}
		
		return validNumber;
	}
	
	
	
	
	
	/**
	 * Checks if the string-value the player entered is valid.
	 * 
	 * @param playerInput Value player has typed in.
	 * @return True: player input fits length and type requirements.
	 */
	public Boolean isValidInput(String playerInput) {
		
		
		//First checks if there are only letters in the input
		
		for (int index = 0; index < playerInput.length(); index ++ ) {
			
			char check = playerInput.charAt(index);
			
			
			if(Character.isAlphabetic(check)) {
				continue; // next iteration
				
			}
			
			
			else {
				
				System.out.println("please don't use any numbers, spaces or symbols. Input another name:");
				return false;
			}
		}
		
		
		
		//Does it exceed 15 characters?
		if (playerInput.length() > 15) {
			
			System.out.println(playerInput + " is too long. Max length is 15 char. Please input another name:");
			return false;
		}
		
		//Is it less than 3?
		else if (playerInput.length() < 3) {
			System.out.println(playerInput + " is too short! Min length is 3. Please input another name:");
			return false;
		}
		
		

		else {
			//It has passed through all the conditions. 
			return true;
		}
		
	}
		
	
	
	
	
	/*
	* Getter and Setter methods begin below here
	* 
	*
	*/
	
	
	public Scanner getAskPlayer() {
		return askPlayer;
	}
	
	
	
	
	
	
	/*
	 * Run the game.
	 */
	public static void main(String[] args) {
		
		
		//uncomment everything to run. 
		//GameEnvironment newSession = new GameEnvironment();
		
		//System.out.println("Back from character creation");

		
		//closes the scanner once game finished.
		//newSession.getAskPlayer().close();
		

	}
}
