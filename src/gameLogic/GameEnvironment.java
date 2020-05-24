package gameLogic;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gameScreens.MainScreen;
import gameScreens.OptionalItemDialog;
/**
 * Contains methods to run the farm game. Contains methods that allow the player's input to alter variables in the game. 
 * 
 * last modified: 20-05-2020
 * 
 * created: 
 * @author Dmitri Smith 
 * @author Kenn Leen Duenas Fulgencio
 *
 */

public class GameEnvironment {
	private ArrayList<Merchandise> m_selectedMerchandise;
	private Farm m_farm;
	private GeneralStore m_store;
	private MainScreen m_mainScreen;
	
	//scanner as an attribute
	private Scanner askPlayer;
	
	/**
	 * the constructor method activates the scanner that will be used to get player's input
	 * It also asks the player to input information to create a farm.
	 * @param farmName name of farm
	 * @param farmType Type of farm
	 * @param farmer Farmer Object to use
	 * @param remainingDays Number of days to play the game
	 */
	public GameEnvironment(String farmName, FarmType farmType, Farmer farmer, int remainingDays)
	{
		askPlayer = new Scanner(System.in);
		Farm newFarm = new Farm(farmName, farmType, farmer, remainingDays);
		createNewGame(newFarm);
		
	}
	
	/**
	 * the constructor method activates the scanner that will be used to get player's input
	 * It also asks the player to input information to create a farm.
	 * @param farm Reference to Farm object created for the game
	 */
	public GameEnvironment(Farm farm)
	{
		askPlayer = new Scanner(System.in);
		createNewGame(farm);
		
	}
	
	public void Run()
	{
		
		System.out.println("Creating a farm.");
		showMainMenu();
		
		//now call the showActivityScreen.
		showActivityScreen();
	}

	
	/*
	 * Methods regarding visual screens begin here
	 */
	

	
	/**
	 * The mainmenu is where the player creates the farm. Using information given by the player, it creates an instance of the Farm class for the attribute 'm_farm'
	 * 
	 */
	private void showMainMenu()
	{
		
		System.out.println("Enter the farmer's name:");
		String farmerName = askName();
		
		
		System.out.println("Enter the farmer's age:");
		int farmerAge = askAge();

		System.out.println("Enter name of Farm:");
		String farmName = askName();
		
		
		//Select a type of farm.
		System.out.println("Enter number of a type of farm.");
		System.out.println("Pick from: "+
				"\n1.Ranch" +
				"\n2.Commercial" +
				"\n3Produce" +
				"\n4Mixed");
		FarmType farmType = askFarmType();
		
		
		//prompt for how many days the player wants to play
		System.out.println("Enter number (5 - 10) of days you will play: ");
		int remainingDays = askDays();
				
		
		
		//create a Farmer instance
		Farmer farmer = new Farmer(farmerName, farmerAge);
		
		Farm farm = new Farm(farmName, farmType, farmer, remainingDays);
		
		System.out.println("New game is loading");
		//Creates a new game
		createNewGame(farm);
		
		

		
	}
	
	/*
	 * 
	 */
	private void createNewGame(Farm farm)
	{		
		m_farm = farm;
		m_store = new GeneralStore(this);
		m_mainScreen = new MainScreen(this);
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
	
	/**
	 * Tends to the crops then updates the detail box to tell the player about their crops. It then deletes the item used.
	 * 
	 * @param message Formatted String to display to user
	 */
	public void tendCropMessage(String message) {
		updateDetailText(message);
		
	}
	
	public void tendCrops(MerchandiseWrapper selection, Item item) {
		ArrayList<Crop> crops = new ArrayList<Crop>();
		ArrayList<Item> items = new ArrayList<Item>();
		for(Crop c : selection.getCrops())
		{
			crops.add(c);
		}
		//If the user chose not to use an item
		if(item == null)
		{
			for(int i = 0; i < crops.size(); i++)
			{
				items.add(Item.getBlankCropItem());
			}
		}
		else 
		{
			ArrayList<Item> playerItems = m_farm.getItems();
			for(Item i : playerItems)
			{
			
				if(i.getName() == item.getName())
				{
					items.add(i);
				}
			}
		}
		try
		{
			tendCrops(crops, items);
		}
		catch(Exception e)
		{
			updateDetailText(e.getMessage());
		}
		
		ArrayList<Item> playerItems = m_farm.getItems();
		
		String message = "";
		for(int i = 0; i < crops.size(); i++)
		{
			if(crops.get(i).getDaysUntilHarvest() != 0)
			{
				message += String.format("%s(%d): %d days until ready to harvest.\n", crops.get(i).getName(),i,crops.get(i).getDaysUntilHarvest());
			}
			else
			{
				message += String.format("%s(%d): Ready to harvest.\n", crops.get(i).getName(),i,crops.get(i).getDaysUntilHarvest());
			}
		}
		tendCropMessage(message);
	}
	
	/**
	 * Called by OptionalItemDialog, sends a list of chosen items and animals of the selected species.
	 * @param selection the animals the player has selected to feed.
	 * @param item the item type the player has chosen to feed to the animal.
	 */
	public void feedAnimals(MerchandiseWrapper selection, Item item) 
	{
		ArrayList<Animal> animals = new ArrayList<Animal>();
		ArrayList<Item> items = new ArrayList<Item>();
		for(Animal a : selection.getAnimals())
		{
			animals.add(a);
		}
		for(Item i : m_farm.getItems())
		{
			if(i.getName() == item.getName())
			{
				items.add(i);
			}
		}
		
		try
		{
			feedAnimals(animals, items);
		}
		catch(Exception e)
		{
			updateDetailText(e.getMessage());
		}
		
	}
		
	private void tendCrops(MerchandiseWrapper wrappedSelection)
	{
		try
		{
			
			OptionalItemDialog optionalItemDialog = new OptionalItemDialog(this, wrappedSelection);
			optionalItemDialog.setVisible(true);

		}
		catch(Exception e)
		{
			updateDetailText(e.getMessage());
		}
		
	}
	
	/**
	 * Feed animals 
	 * @param wrappedSelection is a reference to the species of animal chosen by player
	 */
	private void feedAnimals(MerchandiseWrapper wrappedSelection) 
	{
		//there exists animals that need feeding. 
		//Create OptionalItemDialog window
		
		if(wrappedSelection.getAnimals().size()>=1) 
		{  
			
			try 
			{
				
				//Create OptionalItemDialog window. send the items the player owns
				OptionalItemDialog optionalItemDialog = new OptionalItemDialog(this, wrappedSelection);
				optionalItemDialog.setVisible(true);

			//get selected items into an ArrayList<Item>
			}
			catch(Exception e)
			{
				updateDetailText(String.format("Unable to feed %s", wrappedSelection));
			}
			
			//m_farm.getSelectedItem();
					
		}
		
		
	}
	
	/**
	 * Feed the animals once animals to feed and item to use are established
	 * @param animals ArrayList<Animal> animals to feed
	 * @param items ArrayList<Item> items to feed to animals
	 */
	private void feedAnimals(ArrayList<Animal> animals, ArrayList<Item> items)
	{
		if(animals.size() > items.size())
		{
			throw new IllegalStateException(String.format("Not enough %s: %d needed but %d only in inventory.", items.get(0).getName(), animals.size(), items.size()));
		}
		else if (!items.get(0).getForAnimals())
		{
			throw new IllegalStateException(String.format("Item %s not compatible with animals.", items.get(0).getName()));
		}

		for(int i = animals.size() - 1; i >= 0; i--)
		{
			
			try
			{
				animals.get(i).useItem(items.get(i));
				m_farm.removeItem(items.get(i));
			}
			catch(Exception e)
			{
				m_mainScreen.setDetailText(e.getMessage());
			}
		}
		m_farm.setRemainingActions(m_farm.getRemainingActions() - 1);

	}
	
	/**
	 * Tend the crops once crops to tend and item to use are established
	 * @param cropss ArrayList<Crop> crops to tend
	 * @param items ArrayList<Item> items to use
	 */
	private void tendCrops(ArrayList<Crop> crops, ArrayList<Item> items)
	{
		if(crops.size() > items.size())
		{
			throw new IllegalStateException(String.format("Not enough %s: %d needed but %d only in inventory.", items.get(0).getName(), crops.size(), items.size()));
		}
		else if (!items.get(0).getForCrops())
		{
			throw new IllegalStateException(String.format("Item %s not compatible with crops.", items.get(0).getName()));
		}

		for(int i = crops.size() - 1; i >= 0; i--)
		{
			
			try
			{
				crops.get(i).useItem(items.get(i));
				m_farm.removeItem(items.get(i));
			}
			catch(Exception e)
			{
				m_mainScreen.setDetailText(e.getMessage());
			}
		}
		m_farm.setRemainingActions(m_farm.getRemainingActions() - 1);

	}
	
	private void playWithAnimals(MerchandiseWrapper wrappedSelection)
	{
		
		//Need to access wrapper this way in order to act on reference to player object rather than 
		for(int i = 0; i < wrappedSelection.size(); i++)
		{
			Animal a = (Animal)wrappedSelection.get(i);
			m_farm.playWithAnimal(a);
		}
		
		updateDetailText(String.format("All animals of this species have their happiness increased by %s", m_farm.getAnimalHappinessMod() ));	
		m_farm.setRemainingActions(m_farm.getRemainingActions() - 1);
	}
	
	private void harvestCrop(MerchandiseWrapper wrappedSelection)
	{
		//Need to access wrapper this way in order to act on reference to player object rather than 
		for(int i = 0; i < wrappedSelection.size(); i++)
		{
			Crop c = (Crop)wrappedSelection.get(i);
			try
			{
				m_farm.addMoney(c.harvest());
			}
			catch(Exception e)
			{
				m_mainScreen.setDetailText(e.getMessage());
			}
		}
		m_farm.setRemainingActions(m_farm.getRemainingActions() - 1);

	}
	
	private void tendLand()
	{
		String message = "";
		m_farm.setMaxAnimalAmount(getMaxAnimalAmount() + 1);
		m_farm.setMaxCropAmount(m_farm.getMaxCropAmount() + 1);
		message = message.concat(String.format("Animal capacity increased to %d\n", getMaxAnimalAmount()));
		message = message.concat(String.format("Crop capacity increased to %d", getMaxCropAmount() ));
		
		updateDetailText(message);
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
		
		//call activity screen
		showActivityScreen();
		
	}
	
	/**
	 * Makes an action based on the player's input
	 * @param action PossibleAction action the player can
	 * @param selection String name representing the .getName property of a Merchandise object
	 */
	public void takeAction(PossibleAction action, String selection)
	{
		if(m_farm.getRemainingActions() == 0)
		{
			m_mainScreen.setDetailText("No remaining actions!");
			return;
		}
		
		ArrayList<Merchandise> selectedMerch = m_farm.getPlayerMerchFromString(selection);
		if(selectedMerch.size() == 0 && !(action.equals(PossibleAction.TEND_LAND)) )
		{	
			m_mainScreen.setDetailText(String.format("No merchandise matching %s found in inventory!", selection));
			return;
		}
		MerchandiseWrapper wrappedSelection = new MerchandiseWrapper(selectedMerch);
		
		
		try
		{
			switch(action) 
			{
				case TEND_CROP:
					
					//gets items only for animals. 
					try
					{							 
						//get the merchandise equivalent.
						tendCrops(wrappedSelection);
					}
					catch (IllegalStateException e)
					{
						m_mainScreen.setDetailText(e.getMessage());
						break;
					}
					break;
				case FEED_ANIMAL: 			
					
					//gets the matching class type.
					feedAnimals(wrappedSelection);
					break;
					
				case PLAY_WITH_ANIMAL: 
					playWithAnimals(wrappedSelection);
					break;
				case HARVEST_CROP: 
					harvestCrop(wrappedSelection);
					break;
				case TEND_LAND: 
					tendLand();
					break;
			}
			
			m_mainScreen.updateStatusBar();
		}
		catch(Exception e)
		{
			m_mainScreen.setDetailText(e.getMessage());
		}
		updateStatusBar();
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
		} while (input < 1 || input > crops.size() + 1);
		
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
	 * @throws Exception Any other exception
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
		} while (input < validFloor || input > items.size() + 1);
		
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
		
		
		{
			//String name = animals.get(i - 1).getName();
			//System.out.println(String.format("%d: %s\n", i, name));
			
		}
		//Give user option to cancel
		System.out.println(String.format("%d: Cancel", animals.size()+ 1));
		
		System.out.println("Select an animal: ");
		int input = -1;
		do
		{
			input = askPlayer.nextInt();
		} while (input < 1 || input > animals.size() + 1);
		
		if (input == animals.size() + 1)
		{
			throw new IllegalStateException("User cancelled operation");
		}
		return animals.get(input - 1);
	}

	
	/**
	 * End the game day.
	 * If days remaining in game less than 1, it ends the game.
	 */
	public void endDay()
	{
		try
		{
			updateDetailText(m_farm.endDay());
			m_farm.setRemainingActions(2);
			updateStatusBar();
			m_mainScreen.refreshConfirmButton();
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
	

	
	/**
	 * End of game. Shows player their final score.
	 */
	private void endGame()
	{
		String closingMessage = String.format("The game has ended. Your score is: $%d", m_farm.getMoney());
		JOptionPane.showMessageDialog(null, closingMessage);
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
		while(playerSelection < 1 || playerSelection > 4) {
			
			System.out.println("enter a number above:");
			playerSelection = numberOnly();
			
			
			if(playerSelection < 1 || playerSelection > 4) {
				
				System.out.println(playerSelection + " is not a valid number.");
			}
			
			
		}
		
		
		
		//Depending on the number selected, it will assign the constant to farmType. 
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
	
	
	/**
	 * Ask the player to input how many days they want to play in-game.
	 * They are restricted to only selecting between 5- 10 days.
	 * @return the number of days they want to play.
	 */
	public int askDays() {
		
		int daysChosen = 0;
		
		Boolean isValid = false;
		
		
		// continues looping until valid = true
		while( !(isValid) ) {
			
			daysChosen = numberOnly();
			
			//Tells them if the chosen days is not possible.
			if (!(5 <= daysChosen)) {
				
				System.out.println(daysChosen + " is too small, select a number that is equal to or greater than 5:");
			}
			
			else if (!(daysChosen <= 10)) {
				
				System.out.println(daysChosen + " is too large, select a number that is equal to or less than 10: ");
			}
			
			else {
				isValid = true;
			}
			
		}
		
		
		return daysChosen;
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

			//assigns any lines after first entered input to nothing. so it should leave the scanner free of any characters.
			askPlayer.nextLine();
			
			isValid = true;
			
			
		}
		
		return validNumber;
	}
	
	
	
	
	
	/**
	 * Contains conditions that all Integer inputs representing an action must follow.
	 * @param playerInput the integer player has typed in. 
	 * @return True if it passes the conditions. False if it is the expected input.
	 */
	public Boolean isValidInput(int playerInput) {
		
		//chosenAction must be converted into an int so we can check length
		
		if (String.valueOf(playerInput).length() == 1) {
			
			return true;
			
		}
		
		else {
			
			System.out.println("Too many numbers, please input only one number");
			return false;
		}
		
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
	
	
	
	
	
	/**
	 * Methods representing different screens/forms begin here
	 */
	
	
	
	
	/**
	 * Player gets to select an activity to do. They will continue being prompted until the end of the game.
	 */
	public void showActivityScreen() {
		
		int playerAnswer = 0;
		
		
		// discarded while loop idea. 
		//while(!(m_farm.getRemainingDays() < 0)) {
		System.out.println("\n");
		System.out.println("showActivityScreen");
		System.out.println("\n" + m_farm.getRemainingDays() +" Days left on " + m_farm.getFarmName());
		System.out.println(String.format("You have %s remaining actions", m_farm.getRemainingActions()) );
		System.out.println("\n");
		System.out.println("Activities:"
				+ "\n1. Do Farm work"
				+ "\n2. See farm status"
				+ "\n3. Farm balance"
				+ "\n4. visit general store"
				+ "\n5. end day");
		System.out.println("\n");
		System.out.println("Input a number:");
		
		
		while (playerAnswer < 1 || playerAnswer > 5) {
			
			
			playerAnswer = numberOnly();
			
			//Is the length of the input number a length of 1?
			if (isValidInput(playerAnswer) ) {
				
				//Tells player if the number chosen is one that is available. 
				if (!(playerAnswer == 1) && !(playerAnswer == 2) && !(playerAnswer == 3) && !(playerAnswer == 4) && !(playerAnswer == 5)) {
					System.out.println(playerAnswer + " is not a valid number, please select another: ");
				}
				
	
			}
			
			
			
		}
		
		//escape the while loop when a valid number is selected. 
		//below are if statements that will call the method the number represents.
		
		if (playerAnswer == 1) {
			
			farmWorkScreen();
			
			//remove one from action 
			System.out.println("removed 1 from actions");

		}
		
		
		else if (playerAnswer == 2) {
			//see farm status
			viewFarmStatus();
			System.out.println("Seeing farm stats");

		}
		
		else if (playerAnswer == 3) {
			//see farm balance			
			System.out.println("Your current balance is: " +  m_farm.getMoney());
			//call activity screen
			showActivityScreen();
		}
		
		else if (playerAnswer == 4) {
			// visit general store.
			visitGeneralStore();
			
		}
		
		else if (playerAnswer == 5) {
			// end day
			System.out.println("\n");
			System.out.println("The day has ended.\n");
			
			
			
			endDay();
			
			
			//reset the actions
			m_farm.setRemainingActions(2);
				
		}
		
		else {
			
			System.out.println("Error, while loop shouldn't have let this happen " + playerAnswer);

		}
		
		
		//RESET playerAnswer 
		playerAnswer = 0;
		
		} // the closing bracket for "while remaining days isn't equal to 0.

	//}
	
	
	
	/**
	 * Player can view the work they can do on the farm. Each job will cost one action.
	 * They will be prompted to input a valid number. 
	 * Farmwork screen will reprint itself unless player selects return to availableActivity
	 */
	public void farmWorkScreen() {
		
		//avoiding the 'variable not resolved error', we preset some values 
		PossibleAction action = PossibleAction.TEND_CROP;
		int playerAnswer = 0;
		
		//will keep asking the player until there are no more actions remaining. 
		System.out.println("\n");
		System.out.println("Take action");
		System.out.println(String.format("You have %s remaining", m_farm.getRemainingActions()) );
		System.out.println("\n");
		
		System.out.println("possible actions:"
				+"\n1.Tend land "
				+"\n2.Tend crop"
				+"\n3.Play with animal"
				+"\n4.Harvest Crop"
				+"\n5.return to activities");
		
		System.out.println("\n");
		System.out.println("Input a number:");
		
		
		
		while (playerAnswer < 1 || playerAnswer > 5) {
			
			
			playerAnswer = numberOnly();
			
			//Is the length of the input number a length of 1?
			if (isValidInput(playerAnswer) ) {
				
				//Tells player if the number chosen isn't available. 
				if (playerAnswer < 1 || playerAnswer > 5) {
					System.out.println(playerAnswer + " is not a valid number, please select another: ");
				}
				
	
			}
			
					
		}
			
		
		//if statements that will call the method takeAction with the correct parameters 
		//takeAction(PossibleAction action, ArrayList<Merchandise> selection)
		
		if(playerAnswer == 1) {
			System.out.println("Tending land selected");
			action = PossibleAction.TEND_LAND;
			
			//calling this method will returning the set the actions left to another amount.
			//m_farm.setRemainingActions(takeAction(action,   ));

			farmWorkScreen();
			
			//takeAction(action, ArrayList<Merchandise> selection);
		}
		
		else if(playerAnswer == 2) {
			System.out.println("Tend crop selected");
			action = PossibleAction.TEND_CROP;
			
			farmWorkScreen();
			
			//takeAction(action, ArrayList<Merchandise> selection);
		}
		
		else if(playerAnswer == 3) {
			System.out.println("Play with Animal selected");
			action = PossibleAction.PLAY_WITH_ANIMAL;
			
			farmWorkScreen();
			
			//takeAction(action, ArrayList<Merchandise> selection);
		}
		
		else if(playerAnswer == 4) {
			System.out.println("harvest crop selected");
			action = PossibleAction.HARVEST_CROP;
			
			farmWorkScreen();
			//takeAction(action, ArrayList<Merchandise> selection);		
		
		}
		
		else {
			//if playerAnswer == 5 or we have already processed the action then it just ends and calls the showactivityScreen.
			System.out.println("Returning to activity screen");
			showActivityScreen();
		}
			
		
	}
	
	/**
	 * Display the General Store GUI
	 */
	public void displayGeneralStore()
	{
		m_store.setVisible(true);
	}
	
	/**
	 * Player visits the generalStore. Here they can purchase items.
	 */
	public void visitGeneralStore()
	{
		
		
		int playerAnswer = 0; 
		
		// a backslash n for space from previous printed previous lines
		System.out.println("\nWelcome to the general store!");
		System.out.println("\n");
		System.out.println("Your current balance is: $" + m_farm.getMoney());
		System.out.println("\n");
		System.out.println("Items in cart: " + m_store.getShoppingCart().getMerchList()); //need for loop here? for each item view item's name
		System.out.println("\n");
		System.out.println("1. view animals");
		System.out.println("2. view crops");
		System.out.println("3. view items");
		System.out.println("4. my inventory");
		System.out.println("5. purchase everything in cart");
		System.out.println("6. return to activity");
		System.out.println("\n");
		System.out.println("input your action:");
		
		
		
		while (!(playerAnswer == 1) && !(playerAnswer == 2) && !(playerAnswer == 3) && !(playerAnswer == 4) && !(playerAnswer == 5) && !(playerAnswer == 6) ) {
			
			
			playerAnswer = numberOnly();
			
			//Is the length of the input number a length of 1?
			if (isValidInput(playerAnswer) ) {
				
				//Tells reader if the number chosen is one that is available. 
				if (!(playerAnswer == 1) && !(playerAnswer == 2) && !(playerAnswer == 3) && !(playerAnswer == 4) && !(playerAnswer == 5) && !(playerAnswer == 6)  ) {
					System.out.println(playerAnswer + " is not a valid number, please select another: ");
				}
				
	
			}		
			
		}
		
		//IF STATEMENTS REPRESENTING AN ACTION BEGIN BELOW
		
		if(playerAnswer == 1) {
			//setting default values for variables that will be used
			//the input number from player 
			int chosenMerch = 100;
			
			//the length of the arraylist for this merch type.
			int merchCount = m_store.getAnimals().size();
			int indexPosition = 0;
			//view animals for sale
			System.out.println("Viewing animals for sale");
			
		
			//Prints all items of this category for sale
			for(Animal available: m_store.getAnimals() ) {		
				
				System.out.println(indexPosition + "." + available.getName());
				indexPosition += 1;
			}
				
			
			//Because the amount of available merch will vary from each category the while loop condition must alter to fit this
			//originally meant to be a method but was getting a parameter type error.
			while(chosenMerch >= merchCount) {
				System.out.println("Pick a number:");
				
				chosenMerch = numberOnly();
				
				//Is the length of the input number a length of 1?
				if (isValidInput(chosenMerch) ) {
					
					//Tells player if the number chosen isn't available. 
					if (chosenMerch >=  merchCount) {
						System.out.println(chosenMerch + " is not a valid number, please select another: ");
					}			
		
				}
								
			}
			
			//adds to the cart. 
			Animal animal = m_store.getAnimals().get(chosenMerch);
			System.out.println("adding" + animal.getName() + " to the cart");			
			
			m_store.addToCart(animal);
			
			//print the generalstore again
			visitGeneralStore();
			
			
		}
		
		else if(playerAnswer == 2) {
			// crops for sale
			
			//setting default values for variables that will be used
			//the input number from player 
			int chosenMerch = 100;
			
			//the length of the arraylist for this merch type.
			int merchCount = m_store.getCrops().size();
			int indexPosition = 0;
			
			System.out.println("Viewing crops for sale");
			
			//Prints all items of this category for sale
			for(Crop available: m_store.getCrops() ) {		
				
				System.out.println(indexPosition + "." + available.getName());
				indexPosition += 1;
			}
			
			//Because the amount of available merch will vary from each category the while loop condition must alter to fit this
			//originally meant to be a method but was getting a parameter type error.
			while(chosenMerch >= merchCount) {
				System.out.println("Pick a number:");
				
				chosenMerch = numberOnly();
				
				//Is the length of the input number a length of 1?
				if (isValidInput(chosenMerch) ) {
					
					//Tells player if the number chosen isn't available. 
					if (chosenMerch >=  merchCount) {
						System.out.println(chosenMerch + " is not a valid number, please select another: ");
					}			
		
				}
								
			}
			
			
			//adds to the cart. 
			Crop plant = m_store.getCrops().get(chosenMerch);
			System.out.println("adding " + plant.getName() + " to the cart");			
			
			m_store.addToCart(plant);
			
			//print the generalstore again
			visitGeneralStore();
	
		}	
		
		else if(playerAnswer == 3) {
			//view items
			System.out.println("viewing items for sale");
			
			
			//setting default values for variables that will be used
			//the input number from player 
			int chosenMerch = 100;
			
			//the length of the arraylist for this merch type.
			int merchCount = m_store.getItems().size();
			int indexPosition = 0;
			
			//Prints all items of this category for sale
			for(Item available: m_store.getItems() ) {		
				
				System.out.println(indexPosition + "." + available.getName());
				indexPosition += 1;
			}
			
			//Because the amount of available merch will vary from each category the while loop condition must alter to fit this
			//originally meant to be a method but was getting a parameter type error.
			while(chosenMerch >= merchCount) {
				System.out.println("Pick a number:");
				
				chosenMerch = numberOnly();
				
				//Is the length of the input number a length of 1?
				if (isValidInput(chosenMerch) ) {
					
					//Tells player if the number chosen isn't available. 
					if (chosenMerch >=  merchCount) {
						System.out.println(chosenMerch + " is not a valid number, please select another: ");
					}			
		
				}
								
			}
						
			
			//adds to the cart. 
			Item item = m_store.getItems().get(chosenMerch);
			System.out.println("adding " + item.getName() + " to the cart");			
			
			m_store.addToCart(item);
			
			//print the generalstore again
			visitGeneralStore();
			
		}
		
		else if(playerAnswer == 4) {
			//view inventory
			System.out.println("Viewing inventory");
			m_farm.getItems();
			
			visitGeneralStore();
		}
		
		else if(playerAnswer == 5) {
			
			
			//purchase everything in cart and from the received list, add it to the farm.		
//			for(Merchandise bought: m_store.checkout()) {
//				addToFarm(bought);
//			}
//			//check the merch has been bought. 
//			System.out.println("Purchase successful");
//			
//			visitGeneralStore();
			System.out.println("This section temporarily disabled\nSorry. -Dmitri");
			
		}
		
		else {		
			//because if the player selects 6 they all call activity screen.
			System.out.println("Calling activity screen");
			showActivityScreen();
		
		}

		
	}
	

	/*
	* Getter and Setter methods begin below here
	* 
	*
	*/
	
	public int getPlayerMoney()
	{
		return m_farm.getMoney();
	}
	
	public void setPlayerMoney(int amount)
	{
		m_farm.setMoney(amount);
	}
	
	/**
	 * 
	 * @return purchaseDiscountMod A percentage discount off of prices when buying
	 * 								 at GeneralStore.
	 */
	public double getPurchaseDiscountMod() {
		return m_farm.getPurchaseDiscountMod();
	}
	
	
	/**
	 * 
	 * @param purchaseDiscount  percentage of the discount player gets.
	 */
	public void setPurchaseDiscountMod(double purchaseDiscount) {
		m_farm.setPurchaseDiscountMod(purchaseDiscount);
	}
	
	
	
	/**
	 * scans the input the player has typed in.
	 * @return the scanner used to get input from console
	 */
	public Scanner getAskPlayer() {
		return askPlayer;
	}

	public int getMaxAnimalAmount() {
		return m_farm.getMaxAnimalAmount();
	}
	
	public void setMaxAnimalAmount(int amount) {
		m_farm.setMaxAnimalAmount(amount);;
	}
	
	public int getMaxCropAmount() {
		return m_farm.getMaxCropAmount();
	}
	
	public void setMaxCropAmount(int amount) {
		m_farm.setMaxCropAmount(amount);;
	}

	public ArrayList<Animal> getPlayerAnimals() {
		return m_farm.getAnimals();
	}
	
	public ArrayList<Crop> getPlayerCrops() {
		return m_farm.getCrops();
	}
	
	public ArrayList<Item> getPlayerItems() {
		return m_farm.getItems();
	}
	
	public void addPlayerAnimal(Animal animal)
	{
		m_farm.addAnimal(animal);
	}
	
	public void addPlayerCrop(Crop crop)
	{
		m_farm.addCrop(crop);
	}
	
	public void addPlayerItem(Item item)
	{
		m_farm.addItem(item);
	}
	
	public void addPlayerMerchandise(Merchandise merch)
	{
		m_farm.addMerchandise(merch);
	}

	public MerchandiseWrapper getPlayerMerchandise() {
		return m_farm.getMerch();
	}

	public Farm getFarm() {
		return m_farm;
	}

	public int getRemainingActions() {
		return m_farm.getRemainingActions();
	}

	public int getRemainingDays() {
		return m_farm.getRemainingDays();
	}

	public void updateStatusBar() {
		m_mainScreen.updateStatusBar();
		
	}
	
	/**
	 * Tells player additional information or results of an action.
	 * @param text message that will appear on the detail's box
	 */
	public void updateDetailText(String text) {
		m_mainScreen.setDetailText(text);
	}

	/**
	 * Get farm status
	 * @return Formatted string representing farm status
	 */
	public String getFarmStatus() {
		ArrayList<Animal> animals = m_farm.getAnimals();
		ArrayList<Crop> crops = m_farm.getCrops();
		ArrayList<Item> items = m_farm.getItems();
		
		String output = "";
		output += String.format("Your name: %s\n", m_farm.getFarmer().getName());
		output += String.format("Your age: %d\n", m_farm.getFarmer().getAge());
		output += String.format("Farm name: %s\n", m_farm.getFarmName());
		output += String.format("Farm type: %s\n", m_farm.getfarmType());
		output += String.format("Farm money: %d\n", getPlayerMoney());
		output += String.format("Max number of animals: %d\n", getMaxAnimalAmount());
		output += String.format("Max number of crops: %d\n", getMaxCropAmount());
		output += String.format("Items(%d):\n", items.size());
		for(int i = 0; i < items.size(); i++)
		{
			output += String.format("(%d) %s\n", (i + 1), items.get(i).getName());
			output += "   Compatable with " + ((items.get(i).getForAnimals()) ? "animals\n" : "crops\n");
			output += String.format("   Boost amount: %d\n", items.get(i).getBoostAmount());
		}
		output += String.format("Crops(%d):\n", crops.size());
		for(int i = 0; i < crops.size(); i++)
		{
			output += String.format("(%d) %s\n", (i + 1), crops.get(i).getName());
			output += String.format("   Days to grow: %d\n", crops.get(i).getDaysToGrow());
			output += String.format("   Days until ready to harvest: %d\n", crops.get(i).getDaysUntilHarvest());
		}
		output += String.format("Animals(%d):\n", animals.size());
		for(int i = 0; i < animals.size(); i++)
		{
			output += String.format("(%d) %s\n", (i + 1), animals.get(i).getName());
			output += String.format("   End-of-day cash bonus: $%d\n", animals.get(i).getDailyBonus());
			output += String.format("   Health: %s\n", animals.get(i).getHealth());
			output += String.format("   Happiness: %s\n", animals.get(i).getHappiness());
		}
		
		return output;
	}
	

	
}