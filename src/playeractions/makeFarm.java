package playeractions;

import java.util.Scanner;

import maincode.Farm;
import maincode.FarmType;
import maincode.Farmer;

/**
 * makeFarm is a command line to create a Farm class via player's input.
 * uses a scanner
 * 
 * Created: 12-05-2020
 *
 */

public class makeFarm {
	
	

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in); 
		
		//In order to call the Farm class, we need to 
		
		
		
		/* game session creation */
		
		// player make a farmer!
		
		//prompt: a name
		System.out.println("Enter farmer's name!");
		
		String farmerName = scan.next();
		
		//prompt age
		System.out.println("Enter farmer's age!");
		
		int farmerAge = scan.nextInt();
		
		Farmer farmer = new Farmer(farmerName, farmerAge);
		
		//prompt name farm.
		System.out.println("Name your new Farm!");
		
		String farmName = scan.next();
		farmName += scan.nextLine();
		
		//prompt farm type
		
		System.out.println("Select Farm type!");
		System.out.println("Pick from: Ranch, Commercial, Produce, Mixed !");
		
		//This is an enum. converts string to an enumerator. 
		FarmType farmType = FarmType.valueOf(scan.next().toUpperCase());

		
		// player make a farm!
		Farm playerFarm = new Farm(farmName, farmType, farmer);
		
		//prompt how many days they want to play
		//Should have a min between 5 and max 10 
		// but at the moment it accepts any int
		System.out.println("How many days do you want to play? "
				+ "\npick between 5-10.");
		
		int daysSelected = scan.nextInt();

		
		
		
		/*The game begins here
		 * 
		 *  
		 *  */
		//Begins the game 
		int daysPassed= 1;
		int actionsLeft = 2;
			
		//Loops until we reach day 5. 
		//day 5 will be included.
		
		while(daysPassed <= daysSelected) {

			
			//In relation to player action.
			while (actionsLeft != 0) {
				
				
				
				// a default value that's just a place holder.
				int playerSelection = 0;
				
				System.out.println("\nIt is day: " + daysPassed + " On " + playerFarm.getFarmName());
				System.out.println(String.format("You have %s remaining actions", actionsLeft) );
				
				System.out.println("Actions:"
						+ "\n1. View animal and crop status"
						+ "\n2. Farm balance"
						+ "\n3. visit general store"
						+ "\n4. Next day");
				
				
				System.out.println("Please enter number of action you want to do:");
				
				
				playerSelection = scan.nextInt();
				
				if (playerSelection == 4) {
					System.out.println("You ended work early");
					break; //leaves this while loop. 
					
				}
				
				
				else {
					System.out.println("You did an action");
					actionsLeft -= 1;
					continue;
				}
				
				
			}
			
			//YOU HAVE LEFT THE INNER WHILE LOOP
			System.out.println("\nThe day has ended!");
			daysPassed += 1;
			//reset actions left 
			actionsLeft = 2;
		}
		
		//close scanner 
		scan.close();
		
		//End of game
		System.out.println("Game has ended");
		System.out.println("Your final score is: ");
		
		
		
	}
	
	
	
	
}
