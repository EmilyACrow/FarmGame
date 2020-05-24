package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameLogic.Animal;

/**
 * Test the Animal class
 */
class AnimalTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		//Animal(String name, String nickname, int purchasePrice, int dailyBonus)
		Animal cat = new Animal("cat", "wizard", 25, 10);
		
	}

}
