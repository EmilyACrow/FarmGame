package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameLogic.Animal;
import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Farmer;
/**
 * test methods on Farm
 * @author Kenn Leen Duenas Fulgencio
 */
class FarmTest {
	
	


	@Test
	void addMoneytest() 
	{
		Farmer chad = new Farmer("chad", 100);	
		Farm farmTest = new Farm("redbarn", FarmType.RANCH, chad, 5);
		//ranch farm starts with $500
		
		farmTest.addMoney(510);
		assertEquals(1010, farmTest.getMoney() );
		
		
	}
	
	@Test
	void subtractMoneytest() 
	{
		Farmer chad = new Farmer("chad", 100);	
		Farm farmTest = new Farm("redbarn", FarmType.RANCH, chad, 5);
		//ranch farm starts with $500
		
		farmTest.subtractMoney(233);
		assertEquals(267, farmTest.getMoney() );
		
		farmTest.setMoney(31);
		farmTest.subtractMoney(5);
		assertEquals(26, farmTest.getMoney());
			
	}
	
	@Test
	void endDayTest()
	{
		Farmer luna = new Farmer("Luna", 100);	
		Farm farmTest = new Farm("bluebarn", FarmType.MIXED, luna, 8);
		
		//check that the day has decreased by 1
		//created to hold the returned value
		String message = farmTest.endDay();
				
		assertEquals(7, farmTest.getRemainingDays());
		
		//throws illegal exception if m_remaining days == 0
		
		//Check farm gets bonus from animals at end of day
		Animal horse = new Animal("horse","chips", 55, 80);
		Animal cat = new Animal("cat", "shoes", 30, 15);
		
		
		farmTest.addAnimal(horse);
		farmTest.addAnimal(cat);
		
		//check there are now two elements in the list. 
		System.out.println(farmTest.getAnimals().size());
		assertEquals(2, farmTest.getAnimals().size()  );
		
		
		
		farmTest.setRemainingDays(3);
		
		message = farmTest.endDay();
		assertEquals(1095,farmTest.getMoney());
		
		//check animals' happiness can't go below 0.
		cat.setHappiness(0);
		
		message = farmTest.endDay();
		assertEquals(0, cat.getHappiness());
	}

}
