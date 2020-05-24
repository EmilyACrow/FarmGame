package gameScreens;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;

import gameLogic.Item;

public class MyCustomDialog extends JDialog  {
	
	

	/**
	 * Sets up the components inside the AskItemDialog
	 * @param itemList ArrayList of Items representing all of the player's items
	 */
	public MyCustomDialog(ArrayList<Item> itemList ) {
		setTitle("Optional item");
		setBounds(100, 100, 301, 329);
		
		
		//Only add items that can be used for crop. 
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		
		for(Item crop: itemList) {
			
		itemListModel.addElement(crop);
		}

	}
	
	

}
