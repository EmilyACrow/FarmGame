import java.util.ArrayList;

public class GameEnvironment {
	private ArrayList<Merchandise> m_selectedMerchandise;
	private Farm m_farm;
	private GeneralStore m_store;
	
	public GameEnvironment()
	{
		
	}
	
	private void init()
	{
		createNewGame();
	}
	
	private void createNewGame()
	{
		/*
		 * TODO: Read XML config file
		 */
		
	}
	
	private void removeFromFarm(Merchandise merch)
	{
		
	}
	
	private void addToFarm(Merchandise merch)
	{
		
	}
	
	private void addFarmMoney(int amount)
	{
		
	}
	
	private void removeFarmMoney(int amount)
	{
		
	}
	
	private void tendCrops(Crop crop)
	{
		
	}
	
	private void feedAnimal(Animal animal)
	{
		
	}
	
	private void playWithAnimal(Animal animal)
	{
		
	}
	
	private void harvestCrop(Crop crop)
	{
		
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
		
	}
	
	public int takeAction(PossibleAction action, ArrayList<Merchandise> selection)
	{
		return m_farm.getRemainingActions();
	}
	
	public void endDay()
	{
		
	}
	
	public ArrayList<Merchandise> selectMerch()
	{
		ArrayList<Merchandise> selection = new ArrayList<Merchandise>();
		return selection;
	}
	
	public void visitGeneralStore()
	{
		
	}
}
