package tests;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Farmer;
import gameLogic.Item;
import gameLogic.Merchandise;
import gameScreens.MainScreen;

public class MainScreenTest {

	public static void main(String[] args) {
		/*
		Item wateringCan = new Item("watering can", 0, 1, false, true);
		
		Item tractor = new Item("tractor", 0, 30, false, true);
		Item shovel = new Item("tractor", 30, 10, false, true);
		
		ArrayList<Item> inventoryTest = new ArrayList<Item>();
		
		inventoryTest.add(shovel);
		inventoryTest.add(tractor);
		inventoryTest.add(wateringCan);
		 */
		
		System.out.println("Testing main screen with an instance of a Farm-type class");
		//sets up values for farmer and farm.
		Farmer hank = new Farmer("Hank", 53);
		
		Farm farmTest = new Farm("Acres", FarmType.PRODUCE, hank, 5);
		

		// fill in farmer's inventory
		
		Crop apples = new Crop("apples", 3, 5, 1);	
		farmTest.addCrop(apples);
		
		Crop corn = new Crop("corn", 6, 7, 2);
		farmTest.addCrop(corn);
		
		Crop tomato = new Crop("tomato", 10, 18, 4);
		//check if there is autoScroll.
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		farmTest.addCrop(tomato);
		
		Animal rabbit = new Animal("rabbit", "fluff", 0, 10);
		farmTest.addAnimal(rabbit);
		
		Item wateringCan = new Item("watering can", 0, 1, false, true);
		farmTest.addItem(wateringCan);
		//System.out.println("Print " + farmTest.getCrops());

		
		//Trying out the mainScreen
		MainScreen window = new MainScreen(farmTest);
		window.frmSelectActivity.setVisible(true);
		
		
		//lost money
		//farmTest.setMoney(10); */
		
		//for(items in)
		// if item not in list
		//add to list
		
		
	}

}
