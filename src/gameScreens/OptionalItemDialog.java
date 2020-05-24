package gameScreens;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import gameLogic.Animal;
import gameLogic.GameEnvironment;
import gameLogic.Item;
import gameLogic.Merchandise;
import gameLogic.MerchandiseWrapper;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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

	public OptionalItemDialog(GameEnvironment game, Merchandise typeToTend, String typeOfLife) {
		setTitle("Optional item");
		setBounds(100, 100, 340, 340);
		getContentPane().setLayout(null);
		
		
		//Only add items that can be used for crop. 
		DefaultListModel<Item> itemListModel = new DefaultListModel<Item>();
		
		//using for-loop in game environment
		//Need to get the items for crops or animals depending on the merch. 
		//Here I try to seperate them by doing this. 
		if(typeOfLife.equals("animal")) 
		{
			
			for(Item item : game.getFarm().getItems())
			{
				if(item.getForAnimals())
				{
					itemListModel.addElement(item);
				}			
			}	
		}
		//must then be an item.
		else 
		{
			for(Item item : game.getFarm().getItems())
			{
				if(item.getForCrops())
				{
					itemListModel.addElement(item);
				}
			}
		}

		
		//ScrollPane for Jlist
		JScrollPane optionalItemScroll = new JScrollPane();
		optionalItemScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		optionalItemScroll.setBounds(10, 39, 304, 185);
		getContentPane().add(optionalItemScroll);
		
		//creates Jlist
		JList<Item> itemsOwnedList = new JList<Item>(itemListModel);
		optionalItemScroll.setViewportView(itemsOwnedList);
	
		String verb = (typeOfLife == "animal") ? "feed" : "give";
		JLabel lblAskHeading = new JLabel(String.format("What item do you want to %s to your %s?", verb, typeOfLife));
		lblAskHeading.setBounds(10, 9, 233, 19);
		getContentPane().add(lblAskHeading);
		
		
		//Buttons located on bottom frame are below this line.
		
		
		JButton btnNotUse = new JButton("No Item");
		btnNotUse.addActionListener(new ActionListener() {
			
			//delete this dialog when no is clicked
			public void actionPerformed(ActionEvent e) {
				game.tendCropMessage(typeToTend);
				//itemListModel.clear();		
				dispose();
			}
		});
		btnNotUse.setBounds(134, 236, 85, 37);
		getContentPane().add(btnNotUse);
		
		
		
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
				
				
				//Uses game to call the right method depending on the type of life.
				if(typeOfLife.equals("animal")) 
				{	
					game.accessFeedAnimal(typeToTend, itemsOwnedList.getSelectedValue());
					//itemListModel.clear();		
					dispose();	
				}
				
				else 
				{
					game.tendCropMessage(typeToTend, itemsOwnedList.getSelectedValue() );
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