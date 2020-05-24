package gameScreens;
/**
 * Popup that gets the item a player wants based on what type of Merchandise they have selected
 * 
 * last modified: 5-24-2020
 * @author Kenn Leen Duenas Fulgencio
 *
 */

import javax.swing.JDialog;

import gameLogic.GameEnvironment;
import gameLogic.Item;
import gameLogic.MerchandiseWrapper;

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


/**
 * Uses overloading in order to cater to Animal and Crop type.
 * A dialog box that will give the player the option to use an Item on the selected crop.
 * @author Kenn Leen Duenas Fulgencio
 *
 */
public class OptionalItemDialog extends JDialog {
	
	//private Item optionalItem;

	public OptionalItemDialog(GameEnvironment game, MerchandiseWrapper selection) {
		//Sets string to match the class name of the first item in the MerchandiseWrapper
		//Do this because we already know that the MerchandiseWrapper only has one type of Merchandise in it
		String selectionType = selection.getMerchList().get(0).getClass().getSimpleName();
		boolean isAnimal = selectionType.contentEquals(MerchandiseWrapper.ANIMAL);
		setTitle("Select optional item");
		setBounds(100, 100, 340, 340);
		getContentPane().setLayout(null);
		
		
		//Only add items that can be used for crop. 
		DefaultListModel<String> itemListModel = new DefaultListModel<String>();
		
		//using for-loop in game environment
		//Need to get the items for crops or animals depending on the merch. 
		//Here I try to separate them by doing this. 

		for(Item item : game.getFarm().getItems())
		{
			if((item.getForAnimals() && isAnimal) || (!item.getForAnimals() && !isAnimal))
			{
				String itemDetails = String.format("%s(Boost amount: %d)", item.getName(), item.getBoostAmount());
				itemListModel.addElement(itemDetails);
			}
		}	

		//ScrollPane for Jlist
		JScrollPane optionalItemScroll = new JScrollPane();
		optionalItemScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		optionalItemScroll.setBounds(10, 39, 304, 185);
		getContentPane().add(optionalItemScroll);
		
		//creates Jlist
		JList<String> itemsOwnedList = new JList<String>(itemListModel);
		optionalItemScroll.setViewportView(itemsOwnedList);
	
		String verb = (selectionType == MerchandiseWrapper.ANIMAL) ? "feed" : "give";
		JLabel lblAskHeading = new JLabel(String.format("What item do you want to %s to your %s?", verb, selectionType.toLowerCase()));
		lblAskHeading.setBounds(10, 9, 304, 19);
		getContentPane().add(lblAskHeading);
		
		
		//Buttons located on bottom frame are below this line.
		
		
		JButton btnNotUse = new JButton("No Item");
		btnNotUse.addActionListener(new ActionListener() {
			
			//delete this dialog when no is clicked
			public void actionPerformed(ActionEvent e) {
				game.tendCrops(selection, null);
				//itemListModel.clear();		
				dispose();
			}
		});
		btnNotUse.setBounds(134, 236, 85, 37);
		if(!isAnimal)
		{
			getContentPane().add(btnNotUse);
		}
		
		
		
		JButton btnUse = new JButton("Confirm");
		btnUse.setBounds(229, 235, 85, 39);
		getContentPane().add(btnUse);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 235, 85, 38);
		getContentPane().add(btnCancel);
		
		btnUse.setVisible(false);
		//Uses the selected item object in the Jlist
		btnUse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				//Rough, last minute solution
				String formattedSelection = itemsOwnedList.getSelectedValue().substring(0,itemsOwnedList.getSelectedValue().indexOf('('));				
				Item itemToUse = (Item) game.getFarm().getPlayerMerchFromString(formattedSelection).get(0);
				//Uses game to call the right method depending on the type of life.
				if(isAnimal) 
				{	
					
					game.feedAnimals(selection, itemToUse);
					//itemListModel.clear();		
					dispose();	
				}
				
				else 
				{
					game.tendCrops(selection, itemToUse);
					//itemListModel.clear();		
					dispose();	
				}
			}
		});

		
		
		
		//Jlist itemsOwnedList, mouse Clicked. makes button appear.
		itemsOwnedList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//-1 indicates nothing selected for function DefaultListModel.getSelectedIndex()
				if(itemsOwnedList.getSelectedIndex() != -1)
				{
					btnUse.setVisible(true);
				}
				
			}
		});

	}
		
}