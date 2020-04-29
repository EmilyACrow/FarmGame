package farmLand;
/**
 * The Farmer class holds the name and age of the player's character, the farmer. 
 * created at the start of the game.
 * 
 * Last modified: 30/04/2020
 * 
 * Created 29/04/2020
 * @author Kenn Leen Duenas Fulgencio
 *
 */

public class Farmer {
	
	//name of the farmer
	private String m_name; 
	//age of the farmer. 
	private int m_age;
	
	/**
	 * 
	 * @param name 	name of farmer player has typed in.
	 * @param age 	the farmer's age player selected.
	 */
	public Farmer(String name, int age) {
		m_name = name;
		m_age = age; 
	}
	
	/**
	 * 
	 * @return The farmer's name.
	 */
	public String getName() {
		return m_name;
	}
	
	/**
	 * 
	 * @param name the new name the player has given the farmer in character setup. 
	 */
	public void setName(String name) {
		m_name = name;
	}
	
	/**
	 * 
	 * @return age of the farmer.
	 */
	public int getAge() {
		return m_age;
	}
	
	/**
	 * 
	 * @param age the age of the farmer.
	 */
	public void setAge(int age) {
		m_age = age;
	}
	
	

}
