package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertEquals(510, farmTest.getMoney() );
		
		
	}
	
	@Test
	void subtractMoneytest() {
		Farmer chad = new Farmer("chad", 100);	
		Farm farmTest = new Farm("redbarn", FarmType.RANCH, chad, 5);
		//ranch farm starts with $500
		
		farmTest.subtractMoney(233);
		assertEquals(267, farmTest.getMoney() );
		
		farmTest.setMoney(31);
		farmTest.subtractMoney(5);
		assertEquals(26, farmTest.getMoney());
			
		
	}

}
