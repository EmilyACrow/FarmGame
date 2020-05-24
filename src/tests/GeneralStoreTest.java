package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Farmer;
import gameLogic.GameEnvironment;
import gameLogic.GeneralStore;
import gameLogic.Merchandise;
import gameLogic.ShoppingCart;

/**
 * checks generalstore methods work
 */
class GeneralStoreTest {

	
	@Test 
	public void canPurchaseCartTest()
	{	//controlling the playMoney and finalCost to be fed into argument
		int playerMoney = 20;
		int finalCost = 13;
		
		Animal chicken = new Animal("Bonnie", "Chicken", 3, 4);
		Crop pea = new Crop("pea", 10, 20, 3);
		Farmer jane = new Farmer("jane", 44);
		GameEnvironment gameTest = new GameEnvironment("chimes", FarmType.COMMERCIAL, jane, 5 );
		GeneralStore testStore = new GeneralStore(gameTest);
		
		
		testStore.addToCart(pea);
		testStore.addToCart(chicken);

		//addToCart(Merchandise merch, int amount)
		assertEquals(true, testStore.canPurchaseCart(playerMoney, finalCost, testStore.getShoppingCart()) );
		
	}
	
	
	/**
	 * Testing GeneralStore's m_cart and ShoppingCart interactions:
	 * 
	 * Player should be able to add and remove from this.
	 * This should result in the totalCost being changed in response to these changes.
	 */
	
	
	//Below this line tests regarding the totalSum and player removing and adding items begins here.
	
	@Test 
	public void deleteFromCartTest() {
		
		Animal horse = new Animal("Jeff", "horse", 1, 2);	
		Animal chicken = new Animal("Bonnie", "Chicken", 3, 4);
		//name, purchaseprice, sellprice, days to grow
		Crop pea = new Crop("pea", 10, 20, 3);
		Farmer jane = new Farmer("jane", 44);
		GameEnvironment gameTest = new GameEnvironment("chimes", FarmType.COMMERCIAL, jane, 5 );
		GeneralStore testStore = new GeneralStore(gameTest);
		//Add to the cart.
		testStore.addToCart(chicken);
		testStore.addToCart(pea); 
		testStore.addToCart(horse); 
		
		assertEquals(14, testStore.getShoppingCart().getCartPrice());
		
		//Player has removed the chicken.
		testStore.removeFromCart(chicken);
		
		//we expect total price to be 4.
		assertEquals(11, testStore.getShoppingCart().getCartPrice() );
		
		//we expect the ArrayList to have only two items. Pea and horse.
		assertEquals(2, testStore.viewCart().size());
	}
	
 
}

