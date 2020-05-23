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


/**
 * 
 * A dialog box that will give the player the option to use an Item on the selected crop.
 * @author Kenn Leen Duenas Fulgencio
 *
 */
public class OptionalItemDialog extends JDialog {
	
	private Item optionalItem;
	

	/**
	 * Sets up the components inside the AskItemDialog
	 */
	public OptionalItemDialog(ArrayList<Item> itemList ) {
		setTitle("Optional item");
		setBounds(100, 100, 301, 329);
		getContentPane().setLayout(null);
		
		
		//Only add items that can be used for crop. 
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		
		for(Item crop: itemList) {
			
		itemListModel.addElement(crop);
		}
		
		
		//ScrollPane for Jlist
		JScrollPane optionalItemScroll = new JScrollPane();
		optionalItemScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		optionalItemScroll.setBounds(25, 39, 220, 185);
		getContentPane().add(optionalItemScroll);
		
		//creates Jlist
		JList<Item> itemsForCropList = new JList<Item>(itemListModel);
		optionalItemScroll.setViewportView(itemsForCropList);
		
		
		
		JLabel lblAskHeading = new JLabel("Do you want to use an item on the crop?");
		lblAskHeading.setBounds(25, 10, 286, 19);
		getContentPane().add(lblAskHeading);
		
		
		//Buttons located on bottom frame are below this line.
		
		
		JButton btnNotUse = new JButton("No");
		btnNotUse.addActionListener(new ActionListener() {
			
			//delete this box when no is clicked
			public void actionPerformed(ActionEvent e) {
				optionalItem = null;
				dispose();
			}
		});
		btnNotUse.setBounds(25, 234, 85, 37);
		getContentPane().add(btnNotUse);
		
		
		
		JButton btnUse = new JButton("Confirm");
		//Uses the selected item object in the Jlist
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				optionalItem = itemsForCropList.getSelectedValue();
				btnUse.setVisible(false);
				m_game.tendCrops(typeToTend, item); 
				
				
			}
		});
		btnUse.setBounds(158, 232, 85, 39);
		getContentPane().add(btnUse);
		btnUse.setVisible(false);
		
		
		
		//Jlist itemsForCropList, mouse Clicked. makes button appear.
		itemsForCropList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnUse.setVisible(true);
				
			}
		});

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