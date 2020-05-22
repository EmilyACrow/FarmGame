package tests;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Farmer;
import gameScreens.MainScreen;

public class MainScreenTest {

	public static void main(String[] args) {
		

		
		System.out.println("Testing main screen with an instance of a Farm-type class");
		//sets up values for farmer and farm.
		Farmer hank = new Farmer("Hank", 53);
		
		Farm farmTest = new Farm("Acres", FarmType.PRODUCE, hank, 5);
		

		// things farmer owns. 
		
		Crop apples = new Crop("apples", 3, 5, 1);	
		farmTest.addCrop(apples);
		
		Crop corn = new Crop("corn", 6, 7, 2);
		farmTest.addCrop(corn);
		
		Crop tomato = new Crop("tomato", 10, 18, 4);
		farmTest.addCrop(tomato);
		
		Animal rabbit = new Animal("rabbit", "fluff", 0, 10);
		farmTest.addAnimal(rabbit);
	
		
		//System.out.println("Print " + farmTest.getCrops());

		//Trying out the mainScreen
		MainScreen window = new MainScreen(farmTest);
		window.frmSelectActivity.setVisible(true);
	
		//lost money
		//farmTest.setMoney(10); */
	}

}
