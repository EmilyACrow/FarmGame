import java.util.ArrayList;

/**
 * The Farm class holds information of what the player currently owns, their statuses such as 
 * actions remaining or any health, money, growth boosts.
 * This is also where the bonuses the player can start with is created.
 * 
 * TODO: Rig for JAXB
 * - Dmitri
 * 
 *Last modified: 13-05-2020
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
	 * Array list of Crop objects  the player owns.
	 */	 
	private ArrayList<Crop> m_crops;
	
	
	
	/**
	 * ArrayList of Animal objects  the player owns. 
	 */
	private ArrayList<Animal> m_animals;
	
	
	
	/**
	 * ArrayList of Item objects the player owns. 
	 */
	private ArrayList<Item> m_items; 
	
	

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
	 */
	public Farm(String farmName, FarmType farmType, Farmer farmer) {
		
		m_name = farmName;
		m_farmer = farmer;
		m_type = farmType;	
		
		
		//setting up the arrayLists. When the game starts, these must be empty
		m_crops = new ArrayList<Crop>();
		m_animals = new ArrayList<Animal>();
		m_items = new ArrayList<Item>();		
		
		
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
	public ArrayList<Animal> getAnimals() {
		return m_animals; 
	}
	
	
	
	/**
	 * 
	 * @param animal the new animal object player purchased from GeneralStore.
	 */
	public void addAnimal(Animal animal) {
		m_animals.add(animal);
		
	}
	
	
	
	/**
	 * 
	 * @param animalList List of Animal objects.
	 */
	
	public void setAnimalList(ArrayList<Animal> animalList) {
		m_animals = animalList;
	}

	
	
	/**
	 * 
	 * @return m_crops	list of Crop objects representing the plants the player is growing.
	 */
	public ArrayList<Crop> getCrops() {
		return m_crops;
	}
	

	
	/**
	 * 
	 * @param crop	the new crop object the  player has purchased from GeneralStore.
	 */
	public void addCrop(Crop crop) {
		m_crops.add(crop);
		
	}
	
	public void setCropList(ArrayList<Crop> cropList) {
		m_crops = cropList;
	}
	
	
	/**
	 * 
	 * @return m_items list of item objects representing the items player owns.
	 */
	public ArrayList<Item> getItems() {
		return m_items;
	}
	
	
	
	/**
	 * 
	 * @param item  new item object the player purchased from GeneralStore.
	 */
	public void addItem(Item item) {
		m_items.add(item);
		
	}
	
	
	
	/**
	 * 
	 * @param itemList list of Item objects.
	 */
	public void setItemList(ArrayList<Item> itemList) {
		m_items = itemList;
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



}