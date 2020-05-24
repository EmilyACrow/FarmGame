package tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameLogic.Crop;
import gameLogic.GeneralStore;
/**
 * Test Crop class methods.
 * @author Kenn Leen Duenas Fulgencio
 */
class CropTest {
	
	/**
	 * Test reduced remaining time.
	 * An Exception is thrown at 0. If remainingTime has a result less than 0, 
	 * expect it to reassign to 0.
	 */
	@Test
	void reduceRemainingTimeTest() 
	{
		
		//String name, int purchasePrice, int sellPrice, int daysToGrow)
		//create some instances
		Crop pea = new Crop("pea", 15, 20, 4);
		Crop cotton = new Crop("cotton", 30, 40, 8);
		Crop apples = new Crop("apples", 50, 60, 12);
		
		int timeReduced = 3;
		
		assertEquals(5, cotton.reduceRemainingTime(timeReduced));
		assertEquals(2, cotton.reduceRemainingTime(timeReduced));
		//expect zero even though  2 - 3 = -1 
		assertEquals(0, cotton.reduceRemainingTime(timeReduced));
		
		timeReduced = 2;
		pea.setDaysUntilHarvest(1);
		assertEquals(0, pea.reduceRemainingTime(timeReduced));
		
	}
	
	
	/**
	 * test player receives the sell price profit 
	 * An exception is thrown if 0
	 */
	@Test
	 void harvestProfitTest()
	{
		//create some instances
		Crop pea = new Crop("pea", 15, 20, 4);
		Crop cotton = new Crop("cotton", 30, 40, 8);
		Crop apples = new Crop("apples", 50, 60, 12);
		
		pea.setDaysUntilHarvest(0);
		assertEquals(4, pea.harvest());
		
		
		
		
	}
	
	@Test
	void tendCropTest()
	{
		//create some instances
		Crop pea = new Crop("pea", 15, 20, 4);
		Crop cotton = new Crop("cotton", 30, 40, 8);
		Crop apples = new Crop("apples", 50, 60, 12);
		
		cotton.setDaysUntilHarvest(0);
		assertEquals(8, cotton.harvest());
		
	}
	

}
