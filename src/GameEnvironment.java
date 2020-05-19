
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
	 * the constructor method activates the scanner that will be used to get player's input
	 * It also asks the player to input information to create a farm.
	 */
	public GameEnvironment()
	{
		askPlayer = new Scanner(System.in);
				
		
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
		
		System.out.println("New game is loading");
		//Creates a new game
		createNewGame(farmName, farmType, farmer, remainingDays);
		
		

		
	}
	
	
	
	
	
	/*
	 * 
	 */
	private void createNewGame(String farmName, FarmType farmType, Farmer farmer, int remainingDays)
	{		
		m_farm = new Farm(farmName, farmType, farmer, remainingDays);
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
		//animal.setHappiness(m_farm.MAX_ANIMAL_HAPPINESS);
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
		
		// remove one action
		
		//
		
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
		//call activity screen.
		showActivityScreen();
		
	}
	
	public ArrayList<Merchandise> selectMerch()
	{
		ArrayList<Merchandise> selection = new ArrayList<Merchandise>();
		return selection;
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
		System.out.println("Items in cart: " + m_store.getShoppingCart().getCart()); //need for loop here? for each item view item's name
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
		
		//type in if statements that will call the methods it represents.
		
		if(playerAnswer == 1) {
			//view animals for sale
			System.out.println("Viewing animals for sale");
			m_store.getAnimals();
			
			//ask player if there is anything from the provided list that they want to buy. New method. askAddCart??
			
		}
		
		else if(playerAnswer == 2) {
			//my crops for sale
			System.out.println("Viewing crops for sale");
			m_store.getCrops();
			
		}	
		
		else if(playerAnswer == 3) {
			//view items
			System.out.println("viewing items for sale");
			m_store.getItems();
			
		}
		
		else if(playerAnswer == 4) {
			//view inventory
			System.out.println("Viewing inventory");
			m_farm.getItems();
		}
		
		else if(playerAnswer == 5) {
			
			//purchase everything in cart and from the received list, add it to the farm.		
			for(Merchandise merch: m_store.checkout(m_farm)) {
				addToFarm(merch);
			}
			
		}
		
		//because if the player selects 3 and when the code finished running 1 or 2, they all call show activity screen.
		System.out.println("Calling acitivity screen");
		showActivityScreen();
		

		
	}
	
	
	
	/**
	 * End of game. Shows player their final score.
	 */
	private void endGame()
	{
			
		System.out.println("The game has ended. Your score is: ");
		
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
		
		
		while (!(playerAnswer == 1) && !(playerAnswer == 2) && !(playerAnswer == 3) && !(playerAnswer == 4) && !(playerAnswer == 5)) {
			
			
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
			//viewFarmStatus();
			System.out.println("Seeing farm stats");
		}
		
		else if (playerAnswer == 3) {
			//see farm balance			
			
			System.out.println("Your current balance is: " +  m_farm.getMoney());
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
		
		
		
		while (!(playerAnswer == 1) && !(playerAnswer == 2) && !(playerAnswer == 3) && !(playerAnswer == 4) && !(playerAnswer == 5)) {
			
			
			playerAnswer = numberOnly();
			
			//Is the length of the input number a length of 1?
			if (isValidInput(playerAnswer) ) {
				
				//Tells player if the number chosen isn't available. 
				if (!(playerAnswer == 1) && !(playerAnswer == 2) && !(playerAnswer == 3) && !(playerAnswer == 4) && !(playerAnswer == 5)) {
					System.out.println(playerAnswer + " is not a valid number, please select another: ");
				}
				
	
			}
			
					
		}
			
		
		//if statements that will call the method takeAction with the correct parameters 
		//takeAction(PossibleAction action, ArrayList<Merchandise> selection)
		
		if(playerAnswer == 1) {
			System.out.println("Tending land selected");
			action = PossibleAction.TEND_LAND;
			
			//takeAction(action, ArrayList<Merchandise> selection);
		}
		
		else if(playerAnswer == 2) {
			System.out.println("Tend crop selected");
			action = PossibleAction.TEND_CROP;
			
			//takeAction(action, ArrayList<Merchandise> selection);
		}
		
		else if(playerAnswer == 3) {
			System.out.println("Play with Animal selected");
			action = PossibleAction.PLAY_WITH_ANIMAL;
			
			//takeAction(action, ArrayList<Merchandise> selection);
		}
		
		else if(playerAnswer == 4) {
			System.out.println("harvest crop selected");
			action = PossibleAction.HARVEST_CROP;
			
			//takeAction(action, ArrayList<Merchandise> selection);		
		
		}
		
		//if playerAnswer == 5 or we have already processed the action then it just ends and calls the showactivityScreen.
		showActivityScreen();
			
		
	}
	
	
	
	
	
	
	
	
	/*
	* Getter and Setter methods begin below here
	* 
	*
	*/
	
	
	
	/**
	 * scans the input the player has typed in.
	 * @return the scanner used to get input from console
	 */
	public Scanner getAskPlayer() {
		return askPlayer;
	}
	
	
}
