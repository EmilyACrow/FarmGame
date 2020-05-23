package gameLogic;
import java.util.ArrayList;

/**
 * The Farm class holds information of what the player currently owns, their statuses such as 
 * actions remaining or any health, money, growth boosts.
 * This is also where the bonuses the player can start with is created.
 * 
 * TODO: Rig for JAXB
 * - Dmitri
 * 
 *Last modified: 18-05-2020
 *
 *Created 29-04-2020
 * @author Kenn Leen Duenas Fulgencio
 *
 */
public class Farm {
	
	/**
	 * The name of the Farm
	 */	
	private String m_name;

	/**
	 * The type of farm the player selected. It represents the selected FarmType identifier 
	 *
	 */
	 private FarmType m_type;
	  
	/**
	 * The farmer the player created in creation menu. 
	 */	 
	private Farmer m_farmer;
	
	/**
	 * Wrapper for arraylist of all farm items, crops, and animals
	 */
	private MerchandiseWrapper m_merch;

	/**
	 * Player's current monetary balance.
	 */
	private int m_money;

	/**
	 * The maximum amount of crops the player can own.
	 */
	private int m_maxCropAmount;

	/**
	 * The maximum amount of animals the player can own.
	 */
	private int m_maxAnimalAmount;

	/**
	 * Speed bonus farm provides to growing crops.
	 */
	private double m_growingSpeedMod;

	/**
	 * Harvest bonus farm provides to growing crops. 
	 */
	private double m_cropCashMod;

	/**
	 * Happiness bonus farm provides to animals.
	 */
	private double m_animalHappinessMod;

	/**
	 * Discount-bonus farm provides when player purchases from General Store. 
	 */
	private double m_purchaseDiscountMod; 

	/** 
	 * remaining actions player has left for the day. 
	 */
	private int m_remainingActions;
	
	/**
	 * days remaining in the game
	 */
	private int m_remainingDays;
	
	/**
	 * Max possible animal happiness
	 * TODO: Replace hardcoded value with xml config file
	 */
	private static final int MAX_ANIMAL_HAPPINESS = 100;
	
	/**
	 * Max possible animal health
	 * TODO: Replace hardcoded value with xml config file
	 */
	private static final int MAX_ANIMAL_HEALTH = 100;
	
	
	
	/** 
	 * The Farm constructor has a switch determined by the FarmType selected. 
	 * The types are COMMERCIAL, PRODUCE, RANCH, MIXED.
	 * These determine the bonuses the player starts the game with.
	 * 
	 * Variables the switch cases will set are m_money, m_maxCropAmount,
	 * m_maxAnimalAmount, m_growingSpeedMod, m_cropCashMod, m_animalHappinessMod 
	 * and m_purchaseDisountMod.
	 * 
	 *  
	 * 
	 * @param farmName	Name of the farm 
	 * @param farmType 	the enumerator 'FarmType' the player has selected. 
	 * @param farmer 	an object of the class Farmer. This is the character player created.
	 * @param remainingDays how many days the player is playing the game.
	 */
	public Farm(String farmName, FarmType farmType, Farmer farmer, int remainingDays) {
		
		m_name = farmName;
		m_farmer = farmer;
		m_type = farmType;	
		m_remainingDays = remainingDays;	
		m_remainingActions = 2;
		
		m_merch = new MerchandiseWrapper();
		
		
		//Switch cases for FarmTypes determine starting values
		switch(farmType) {
		case COMMERCIAL:
					
			m_money = 100;
			
			m_maxCropAmount = 6;
			m_maxAnimalAmount = 6;
			
			m_growingSpeedMod = 0.4;
			m_cropCashMod = 0.4; 
			m_animalHappinessMod = 0.4; 
			
			m_purchaseDiscountMod = 0.0;
			
			
			//break stops code from going to the case below.
			break;
			
		case PRODUCE:
			
			m_money = 500;
			
			m_maxCropAmount = 6;
			m_maxAnimalAmount = 2;
			
			m_growingSpeedMod = 0.4;
			m_cropCashMod = 0.5; 
			m_animalHappinessMod = 0.0; 
			
			m_purchaseDiscountMod = 0.0;
			
			break;
			
		case RANCH:
						
			m_money = 500;
			
			m_maxCropAmount = 2;
			m_maxAnimalAmount = 6;
			
			m_growingSpeedMod = 0.0;
			m_cropCashMod = 0.0; 
			m_animalHappinessMod = 0.4; 
			
			m_purchaseDiscountMod = 0.0;
			
			break;
			
		case MIXED:
					
			m_money = 1000;
			
			m_maxCropAmount = 3;
			m_maxAnimalAmount = 3;
			
			m_growingSpeedMod = 0.0;
			m_cropCashMod = 0.0; 
			m_animalHappinessMod = 0.0; 
			
			m_purchaseDiscountMod = 0.2;
			
			break;
		
		
		}
			

	}
	
	/**
	 * Method for getting all of the numerical values for farm modifiers
	 * Array representing all of the starting modifiers:
	 * 0: Starting money
	 * 1: Max starting crop amount
	 * 2: Max starting animal amount
	 * 3: Growing speed modifier
	 * 4: Crop sale price bonus
	 * 5: Maximum animal happiness modifier
	 * 6: Discount on store prices
	 * @param type FarmType enum
	 * @return Double array of all farm modifier values
	 */
	public static double[] getFarmModifierValues(FarmType type)
	{
		
		double[] modifiers = new double[7];
		
		switch(type) {
		case COMMERCIAL:
			modifiers[0] = 100;
			modifiers[1] = 6;
			modifiers[2] = 6;
			modifiers[3] = 0.4;
			modifiers[4] = 0.4; 
			modifiers[5] = 0.4; 
			modifiers[6] = 0.0;
			break;
		case PRODUCE:
			modifiers[0] = 500;
			modifiers[1] = 6;
			modifiers[2] = 2;
			modifiers[3] = 0.4;
			modifiers[4] = 0.5; 
			modifiers[5] = 0.0; 
			modifiers[6] = 0.0;
			break;
		case RANCH:
			modifiers[0] = 500;
			modifiers[1] = 2;
			modifiers[2] = 6;
			modifiers[3] = 0.0;
			modifiers[4] = 0.0; 
			modifiers[5] = 0.4; 
			modifiers[6] = 0.0;
			break;
		case MIXED:
			modifiers[0] = 1000;
			modifiers[1] = 3;
			modifiers[2] = 3;
			modifiers[3] = 0.0;
			modifiers[4] = 0.0; 
			modifiers[5] = 0.0;
			modifiers[6] = 0.2;
			break;
		}
		
		return modifiers;
	}
	
	public static String[] getFarmModifierNames()
	{
		String[] names = new String[7];
		names[0] = "Starting money";
		names[1] = "Max starting crop amount";
		names[2] = "Max starting animal amount";
		names[3] = "Growing speed modifier";
		names[4] = "Crop sale price bonus";
		names[5] = "Maximum animal happiness modifier";
		names[6] = "Store Discount";
		return names;
	}

	
	//Getters and Setters begin below this line.

	
	
	/**
	 * 
	 * @return name	the name of the farm the player typed in for the farm.
	 */
	public String getFarmName() {
		return m_name;
	}
	
	
	/**
	 * 
	 * @param name the name of the farm player wrote.
	 */
	public void setFarmName(String name) {
		m_name = name;
		
	}

	
	
	/**
	 * 
	 * @return m_type	the type of farm player selected.
	 */
	public FarmType getfarmType() {
		return m_type;
	}
	
	
	
	public void setFarmType(FarmType farmType) {
		m_type = farmType;
	}
	
	
	
	/**
	 * 
	 * @return m_farmer object of the Farmer class. It's the player's character.
	 */
	public Farmer getFarmer() {
		return m_farmer;
	}
	
	
	
	/**
	 * 
	 * @param farmer the farmer player created. Reminder that this is an object. 
	 */
	public void setFarmer(Farmer farmer) {
		m_farmer = farmer;
	}
	
	
	
	/**
	 * 
	 * @return m_animals list of Animal objects representing the animals owned by player. 
	 */
//	public ArrayList<Animal> getAnimals() {
//		return m_animals; 
//	}
	public ArrayList<Animal> getAnimals()
	{
		return m_merch.getAnimals();
	}
	
	
	/**
	 * 
	 * @param animal the new animal object player purchased from GeneralStore.
	 */
//	public void addAnimal(Animal animal) {
//		m_animals.add(animal);
//		
//	}
	public void addAnimal(Animal animal)
	{
		addMerchandise((Merchandise) animal);
	}
	
	/**
	 * 
	 * @param animal animal to remove from farm
	 * @return true if removal was successful
	 */
	public boolean removeAnimal(Animal animal)
	{
		return removeMerchandise((Merchandise) animal);
	}
	
	/**
	 * 
	 * @param animalList List of Animal objects.
	 */
	
//	public void setAnimalList(ArrayList<Animal> animalList) {
//		m_animals = animalList;
//	}

	
	
	/**
	 * 
	 * @return m_crops	list of Crop objects representing the plants the player is growing.
	 */
//	public ArrayList<Crop> getCrops() {
//		return m_crops;
//	}
	public ArrayList<Crop> getCrops()
	{
		return m_merch.getCrops();
	}

	
	/**
	 * 
	 * @param crop	the new crop object the  player has purchased from GeneralStore.
	 */
//	public void addCrop(Crop crop) {
//		m_crops.add(crop);
//		
//	}
	public void addCrop(Crop crop)
	{
		addMerchandise((Merchandise) crop);
	}
	
	/**
	 * 
	 * @param crop crop to remove from farm
	 * @return true if removal was successful
	 */
	public boolean removeCrop(Crop crop)
	{
		return removeMerchandise((Merchandise) crop);
	}
	
//	public void setCropList(ArrayList<Crop> cropList) {
//		m_crops = cropList;
//	}
	
	
	/**
	 * 
	 * @return m_items list of item objects representing the items player owns.
	 */
//	public ArrayList<Item> getItems() {
//		return m_items;
//	}
	public ArrayList<Item> getItems()
	{
		return m_merch.getItems();
	}
	
	/**
	 * 
	 * @return MerchandiseWrapper containing all of the player owned merchandise
	 */
	public MerchandiseWrapper getMerch()
	{
		return m_merch;
	}
	
	/**
	 * 
	 * @param item  new item object the player purchased from GeneralStore.
	 */
	public void addItem(Item item)
	{
		addMerchandise((Merchandise) item);
	}
	
	/**
	 * 
	 * @param item item to remove from farm
	 * @return true if removal was successful
	 */
	public boolean removeItem(Item item)
	{
		return removeMerchandise((Merchandise) item);
	}
	
	/**
	 * 
	 * @param merch merchandise to add to farm
	 */
	public void addMerchandise(Merchandise merch)
	{
		m_merch.add(merch);
	}
	
	/**
	 * 
	 * @param merch merchandise to remove from farm
	 * @return true if removal was successful
	 */
	public boolean removeMerchandise(Merchandise merch)
	{
		return m_merch.remove(merch);
	}

	/**
	 * 
	 * @return m_money the player's current monetary balance.
	 */
	public int getMoney() {
		return m_money;
	}
	

	
	/**
	 * 
	 * @param money	updates the Farm's monetary balance.
	 */
	public void setMoney(int money) {
		m_money = money;
	}
	
	/**
	 * Adds a given amount of money to the farm's bank
	 */
	public void addMoney(int amount)
	{
		m_money += amount;
	}
	
	/**
	 * Subtract a given amount of money from the player's bank
	 * @throws RuntimeException if player does not have enough money
	 */
	public void subtractMoney(int amount)
	{
		if (m_money < amount)
		{
			throw new RuntimeException(String.format("Not enough money. Needed %d, but only %d available", amount, m_money));
		}
		else
		{
			m_money -= amount;
		}
	}
	
	/**
	 * 
	 * @return m_maxCropAmount The maximum amount of crops a player can have growing
	 */
	public int getMaxCropAmount() {
		return m_maxCropAmount;
	}

	
	
	/**
	 * 
	 * @param m_maxCrop sets the maximum amount of crops in player's farm.
	 */
	
	public void setMaxCropAmount(int maxCrop) {
		m_maxCropAmount = maxCrop;
	}

	/**
	 * 
	 * @return m_maxAnimalAmount the maximum animals player can have in farm. 
	 */
	public int getMaxAnimalAmount() {
		return m_maxAnimalAmount;
	}

	/**
	 * 
	 * @param maxAnimal sets the maximum amount of animals in player's farm.
	 */
	public void setMaxAnimalAmount(int maxAnimal) {
		m_maxAnimalAmount = maxAnimal;
	}
	
	/**
	 * 
	 * @return m_growingSpeedMod gives growth speed bonus for crops in farm.
	 */
	public double getGrowingSpeedMod() {
		return m_growingSpeedMod;
	}
	
	/**
	 * 
	 * @param growingSpeed	sets the growing speed bonus for crops in farm to a 
	 * 						different value. SetGrowingSpeedMod can be influenced by tending farm or items.
	 */
	public void setGrowingSpeedMod(double growingSpeed) {
		m_growingSpeedMod = growingSpeed;
	}
	
	/**
	 * 
	 * @return m_cropCashMod the bonus percentage player receives when harvesting crops. 
	 * 						 It is influenced by the type of farm chosen.
	 */
	public double getCropCashMod() {
		return m_cropCashMod;
	}

	/**
	 * 
	 * @param m_cropCashMod sets the bonus percentage
	 *  					player earn when harvesting crops to a different value. 
	 */
	public void setCropCashMod(double cropCash) {
		m_cropCashMod = cropCash;
	}
	
	/**
	 * 
	 * @return m_animalHappinessMod percentage bonus for animal's happiness.
	 */
	public double getAnimalHappinessMod() {
		return m_animalHappinessMod;
	}
	
	/**
	 * 
	 * @param animalHappiness  the percentage happiness bonus for animals.
	 * 						  May be altered by action tendFarm or lack of farm tending.
	 */
	public void setAnimalHappinessMod(double animalHappiness) {
		m_animalHappinessMod = animalHappiness;
	}

	/**
	 * 
	 * @return m_purchaseDiscountMod A percentage discount off of prices when buying
	 * 								 at GeneralStore.
	 */
	public double getPurchaseDiscountMod() {
		return m_purchaseDiscountMod;
	}
	
	/**
	 * 
	 * @param purchaseDiscount  percentage of the discount player gets.
	 */
	public void setPurchaseDiscountMod(double purchaseDiscount) {
		m_purchaseDiscountMod = purchaseDiscount;
	}
	
	/**
	 * 
	 * @return how many actions left.
	 */
	public int getRemainingActions() {
		
		return m_remainingActions; 
	}
	
	/**
	 * @param actionsLeft change how many actions player has left.
	 */
	public void setRemainingActions(int actionsLeft) {
		
		m_remainingActions = actionsLeft;
	}
	
	/**
	 * @return how manydays left in the game.
	 */
	public int getRemainingDays()
	{
		return m_remainingDays;
	}
	
	/**
	 * @param actionsLeft change how many days left in the game.
	 */
	public void setRemainingDays(int days)
	{
		m_remainingDays = days;
	}


	@Override
	public String toString() 
	{
		String output = "Farm {";
		output = output.concat("m_name=" + m_name + ", ");
		output = output.concat("m_type=" + m_type + ", ");
		output = output.concat("m_farmer=" + m_farmer + ", ");
		output = output.concat("m_crops=" + m_merch.getCrops() + ", ");
		output = output.concat("m_animals=" + m_merch.getAnimals() + ", ");
		output = output.concat("m_items=" + m_merch.getItems() + ", ");
		output = output.concat("m_money=" + m_money + ", ");
		output = output.concat("m_maxCropAmount=" + m_maxCropAmount + ", ");
		output = output.concat("m_maxAnimalAmount=" + m_maxAnimalAmount + ", ");
		output = output.concat("m_growingSpeedMod=" + m_growingSpeedMod + ", ");
		output = output.concat("m_cropCashMod=" + m_cropCashMod + ", ");
		output = output.concat("m_animalHappinessMod=" + m_animalHappinessMod + ", ");
		output = output.concat("m_purchaseDiscountMod=" + m_purchaseDiscountMod + ", ");
		output = output.concat("m_remainingActions=" + m_remainingActions + ", ");
		output = output.concat("m_remainingDays=" + m_remainingDays); 
		output = output.concat("}");
		return output;
	}

	/**
	 * 
	 * @return Maximum possible animal happiness
	 */
	public static int getMaxAnimalHappiness() {
		return MAX_ANIMAL_HAPPINESS;
	}

	/**
	 * 
	 * @return Maximum possible animal health
	 */
	public static int getMaxAnimalHealth() {
		return MAX_ANIMAL_HEALTH;
	}
	
	public String endDay()
	{
		//Returns a message with carriage returns that informs the players of the results of the day.
		String message = "";
		if(m_remainingDays <= 0)
		{
			throw new IllegalStateException("No more days remaining.");
		}
		m_remainingDays -= 1;
		message.concat(String.format("%d days remaining in the game\n", m_remainingDays));
		
		ArrayList<Animal> animals = m_merch.getAnimals();
		
		//For each animal, give the player a cash bonus, then decrement their health and happiness
		for (Animal animal : animals)
		{
			//Only get cash bonus from animal if happiness is above 0
			if(animal.getHappiness() > 0)
			{
				addMoney(animal.getDailyBonus());
				//Decrement animal happiness by set amount. If happiness goes below 0, force it back to 0.
				animal.setHappiness(animal.getHappiness() - animal.getDailyHappinessLoss());
				if (animal.getHappiness() < 0)
				{
					animal.setHappiness(0);
				}
			}

			//Do the same for animal health. 
			//If the animal's health hits zero, it is removed after all animals have been handled
			animal.setHealth(animal.getHealth() - animal.getDailyHealthLoss());
		}
		//For loop to start from the back of the list of animals and remove any whose health is at zero
		//Starts at the end so that removing one animal doesn't cause an overflow by the end of the loop
		for(int i = animals.size(); i > 0; --i)
		{
			if(animals.get(i).getHealth() <= 0)
			{
				message.concat(String.format("Animal %s died.\n", animals.get(i).getName()));
				animals.remove(i);
			}
			
		}
		
		//Decrement the number of days until harvest for each crop
		for(Crop c : m_merch.getCrops())
		{
			if(c.getDaysUntilHarvest() > 1)
			{
				c.setDaysUntilHarvest(c.getDaysUntilHarvest() - 1);
			}
			else if(c.getDaysUntilHarvest() == 1)
			{
				c.setDaysUntilHarvest(c.getDaysUntilHarvest() - 1);
				message.concat(String.format("%s is ready to harvest", c.getName()));
			}
			else
			{
				message.concat(String.format("%s is ready to harvest", c.getName()));
			}
		}
		
			
		return message;
		
		
	}
	
	public String tendCrops(Crop crop)
	{
		/* Crops can be tended without having an item, by only boosting by one day.
		 * Rather than copying code, a new item is temporarily created with 
		 * values:
		 * name: temp
		 * purchase price: 0
		 * boostAmt: 1
		 * forAnimals: false (you must have a food item to feed an animal)
		 * forCrops: true
		 */
		Item tempItem = new Item("temp", 0, 1, false, true);
		return tendCrops(crop, tempItem);
	}
	
	
	public String tendCrops(Crop crop, Item item)
	{
		//Returns a message with carriage returns that informs the players the harvest results
		String message = "";
		
		//The crops in the farm will be compared based on name to see if they are tended to
		String cropName = crop.getName();
		ArrayList<Crop> crops = m_merch.getCrops();
		int counter = 0;
		int readyToHarvestCounter = 0;
		
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
					readyToHarvestCounter++;
				}
			}
		}
		//This occurs if no crops were able to be tended to
		if(counter == 0)
		{
			throw new IllegalStateException(String.format("No crops could be tended to! %d ready to harvest.\n", readyToHarvestCounter));
		}
		
		//Remove item once it has been used
		m_merch.getItems().remove(item);
		
		//Update status message to inform user of results
		message.concat(String.format("%d crops tended to.\n", counter));
		message.concat(String.format("%d crops ready to harvest.\n", readyToHarvestCounter));
		
		return message;
	}


	public void playWithAnimal(Animal animal) {
		animal.setHappiness((int)(MAX_ANIMAL_HAPPINESS * m_animalHappinessMod));
	}
	
	public ArrayList<Merchandise> getPlayerMerchFromString(String str)
	{
		ArrayList<Merchandise> matchingMerch = new ArrayList<Merchandise>();
		for(Merchandise m : m_merch.getMerchList())
		{
			if(m.getName() == str)
			{
				matchingMerch.add(m);
			}
		}
		return matchingMerch;
	}

	

}