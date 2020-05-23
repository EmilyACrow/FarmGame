package gameScreens;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import gameLogic.Item;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


/**
 * 
 * A dialog box that will give the player the option to use an Item on the selected crop.
 * @author Kenn Leen Duenas Fulgencio
 *
 */
public class OptionalItemDialog extends JDialog {
	
	private Item optionalItem;
	private OptionalItemPanel optionItemPanel;
	

	/**
	 * Sets up the components inside the AskItemDialog
	 */
	public OptionalItemDialog(ArrayList<Item> itemList ) {
		setTitle("Optional item");
		setBounds(100, 100, 301, 329);
		
		JPanel OptionalItemPanel = new JPanel();
		getContentPane().add(OptionalItemPanel);
		
		
		//Only add items that can be used for crop. 
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		
		for(Item crop: itemList) {
			
		itemListModel.addElement(crop);
		}

	}
	
	/**
	 * OptionalItemDialog will set Optional item if player has chosen to use one
	 * otherwise sets to null
	 * @param selectedItem item chosen to use on crop
	 */
	public void setOptionalItem(Item item) {
		optionalItem = item;
	}
	
	
	/**
	 * Mainscreen calls getOptionalItem to get optionalItem value.
	 * @return item player has selected. null if no item selected.
	 */
	public Item getOptionalItem() {
		return optionalItem;
	}

}
