package tests;

import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Farmer;
import gameScreens.MainScreen;

public class MainScreenTest {

	public static void main(String[] args) {
		
		
		//sets up values for farmer and farm.
		Farmer hank = new Farmer("Hank", 53);
		
		Farm farmTest = new Farm("Acres", FarmType.PRODUCE, hank, 5);
		
		//making some farm plants in their inventory 
		// We have some list of items already
		//String name, int purchasePrice, int sellPrice, int daysToGrow
		
		
		Crop apples = new Crop("apples", 3, 5, 1);	
		farmTest.addCrop(apples);
		
		Crop corn = new Crop("corn", 6, 7, 2);
		farmTest.addCrop(corn);
		
		Crop tomato = new Crop("tomato", 10, 18, 4);
		farmTest.addCrop(tomato);
	
		
		//System.out.println("Print " + farmTest.getCrops());

		//Trying out the mainScreen
		MainScreen window = new MainScreen(farmTest);
		window.mainFrame.setVisible(true);
		
		
		System.out.println(farmTest.toString());
		
		//lost money
		//farmTest.setMoney(10);
	}

}
