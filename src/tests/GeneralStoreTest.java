package tests;

package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Farmer;
import gameLogic.GeneralStore;
import gameLogic.Merchandise;

class GeneralStoreTest {
	
	//I found that the tests, other than first one after BeforeEach can't call any of this.
	private GeneralStore testStore;
	private Farm testFarm;
	
	
	//in order for setup to work, variables like horse must be up here. 

	private Animal horse; 
	private Animal chicken;
	
	private Crop pea;
	
		
	@BeforeEach
	public void setUp() {

		Farmer Hank = new Farmer("Hank", 53);
		testFarm = new Farm("bird crossing", FarmType.RANCH, Hank, 5);
		
		testStore = new GeneralStore();
		// cost price and money bonus
		horse = new Animal("Jeff", "horse", 1, 2);	
		chicken = new Animal("Bonnie", "Chicken", 3, 4);

		//name, purchaseprice, sellprice, days to grow
		pea = new Crop("pea", 10, 20, 3);
		
		
		//It only works for the @test directly below it 
		testStore.addToShop(horse);
		testStore.addToShop(chicken);
		testStore.addToShop(pea);
		
	}
	

	@Test
	public void checkMerchCount() {
		//Adds the items to the Store.

		//Is there two animals in Animal array list?	
		assertEquals(2, testStore.getAnimalsAvailable().size());
		
	
	} 
	
	
	
	/**
	 * Testing GernalStore's m_cart and ShoppingCart interactions:
	 * 
	 * Player should be able to add and remove from this.
	 * This should result in the totalCost being changed in response to these changes.
	 */
	
	
	//Below this line tests regarding the totalSum and player removing and adding items begins here.
	
	@Test
	public void totalCostTest() {
		testStore.addToCart(chicken);
		testStore.addToCart(pea); //expect totalCost to be 13.
		
		assertEquals(13, testStore.getShoppingCart().getTotalCost());
		
		
	}
	
	@Test 
	public void deleteFromCartTest() {
		
		//Add to the cart.
		testStore.addToCart(chicken);
		testStore.addToCart(pea); 
		testStore.addToCart(horse); 
		
		assertEquals(14, testStore.getShoppingCart().getTotalCost());
		
		//Player has removed the chicken.
		testStore.removeFromCart(chicken);
		
		//we expect total price to be 4.
		assertEquals(11, testStore.getShoppingCart().getTotalCost());
		
		//we expect the ArrayList to have only two items. Pea and horse.
		System.out.println(testStore.viewCart());
		assertEquals(2, testStore.viewCart().size());
	}
	
	
	
	//Below this line tests regarding the Checkout ArrayList begins here.
	
	@Test 
	public void checkoutFailTest() {
		//This is meant to say they can not afford.
		
		//Set his money to 1. ensuring it is less than cart cost.
		testFarm.setMoney(1);
		
		
		//add to the cart.
		testStore.addToCart(chicken);
		testStore.addToCart(pea); 
		testStore.addToCart(horse); 
		
		testStore.checkout(testFarm);

	}
	
	@Test 
	public void checkoutJustEnoughTest() {
		
		
		//Set his money to be equal to what he will buy. ensuring it is less than cart cost.
		testFarm.setMoney(3);
		
		
		//add to the cart.
		testStore.addToCart(chicken); // costs only 3. 
		
		//Expect to have the merch returned after it has been emptied from cart.
		ArrayList<Merchandise> hold =  testStore.checkout(testFarm);
		
		//expect Hank's money to be zero.
		assertEquals(0, testFarm.getMoney());
		
		//cart should be emptied. 
		assertEquals(0, testStore.viewCart().size());
		
		//cheep cheep. One chicken bought!
		assertEquals(1, hold.size());

	}
	
	
	//GeneralStore's discount price function.
	@Test
	public void discountPriceTest() {
		/*Checks player will get a discount if they are applicable */
		
		Farmer jane = new Farmer("Jane", 53);
		
		Farm janeFarm = new Farm("birds barn", FarmType.MIXED, jane, 5);
		
		
		//Set his money to be equal to what he will buy. ensuring it is less than cart cost.
		janeFarm.setMoney(14);
		
		
		//add to the cart.
		testStore.addToCart(chicken); // costs only 3. 
		testStore.addToCart(chicken);
		testStore.addToCart(chicken);
		testStore.addToCart(chicken); // total cost 12.
		
		//Begin transaction of buying.
		testStore.checkout(janeFarm);
		
		assertEquals(5, janeFarm.getMoney()); // With the discount, expect cost to be 9 rather than 12.
		
	}
	
 
}

