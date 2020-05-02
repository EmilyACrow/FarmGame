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
		showMainMenu();
	}
	
	private void showMainMenu()
	{
		
	}
	
	/**
	 * 
	 */
	private void createNewGame()
	{
		/*
		 * TODO: Read XML config file
		 */
		try
		{
			configureStore();
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
	/**
	 * Read Merchandise config file then initialize GeneralStore with config data
	 */
	private int configureStore()
	{
		try
		{
			File file = new File("config/test.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(MerchWrapper.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        MerchWrapper merchWrapper = (MerchWrapper) unmarshaller.unmarshal(file);
	        m_store = new GeneralStore(merchWrapper.getMerchList());
	        return 0;
		}
		catch(Exception e)
		{
			System.out.println(e);
			throw new RuntimeException("Error configuring GeneralStore");
		}
		
	}
	
	private void removeFromFarm(Merchandise merch)
	{
		
	}
	
	private void addToFarm(Merchandise merch)
	{
		
	}
	
	private void addFarmMoney(int amount)
	{
		m_farm.setMoney(m_farm.getMoney() + amount);
	}
	
	private void removeFarmMoney(int amount)
	{
		int bank = m_farm.getMoney();
		if(amount < bank)
		{
			m_farm.setMoney(0);
		}
		else
		{
			m_farm.setMoney(bank - amount);
		}
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
		ArrayList<Merchandise> merchList;
		merchList = (ArrayList<Merchandise>) m_farm.getAnimals();
		
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
