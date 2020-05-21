package gameScreens;

/**
 * Use code from the following for help iterating over a hashmap.
 * https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.GeneralStore;
import gameLogic.Item;
import gameLogic.Merchandise;
import gameLogic.MerchandiseWrapper;
import javafx.scene.control.ComboBox;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class GeneralStoreScreen extends GeneralStore{

	public JFrame frame;
	private JTextField cartTotalTextField;
	//private JScrollPane merchScrollPane;
	private StoreDisplayPanel panel;
	private MerchandiseWrapper playerInv;
	private ArrayList<Merchandise> m_cart;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GeneralStoreScreen window = new GeneralStoreScreen();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public GeneralStoreScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 125, 46, 73, 74, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		Component leftHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_leftHStrut = new GridBagConstraints();
		gbc_leftHStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftHStrut.gridx = 0;
		gbc_leftHStrut.gridy = 0;
		frame.getContentPane().add(leftHStrut, gbc_leftHStrut);
		
		Component middleHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_middleHStrut = new GridBagConstraints();
		gbc_middleHStrut.insets = new Insets(0, 0, 5, 5);
		gbc_middleHStrut.gridx = 3;
		gbc_middleHStrut.gridy = 0;
		frame.getContentPane().add(middleHStrut, gbc_middleHStrut);
		
		JLabel lblCartTotal = new JLabel("Total:");
		GridBagConstraints gbc_lblCartTotal = new GridBagConstraints();
		gbc_lblCartTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartTotal.anchor = GridBagConstraints.EAST;
		gbc_lblCartTotal.gridx = 4;
		gbc_lblCartTotal.gridy = 0;
		frame.getContentPane().add(lblCartTotal, gbc_lblCartTotal);
		
		cartTotalTextField = new JTextField();
		cartTotalTextField.setEditable(false);
		GridBagConstraints gbc_cartTotalTextField = new GridBagConstraints();
		gbc_cartTotalTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cartTotalTextField.anchor = GridBagConstraints.WEST;
		gbc_cartTotalTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cartTotalTextField.gridx = 5;
		gbc_cartTotalTextField.gridy = 0;
		frame.getContentPane().add(cartTotalTextField, gbc_cartTotalTextField);
		cartTotalTextField.setColumns(10);
		
		Component rightHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_rightHStrut = new GridBagConstraints();
		gbc_rightHStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightHStrut.gridx = 6;
		gbc_rightHStrut.gridy = 0;
		frame.getContentPane().add(rightHStrut, gbc_rightHStrut);
		
		JLabel lblFilter = new JLabel("Filter");
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.EAST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 1;
		frame.getContentPane().add(lblFilter, gbc_lblFilter);
		
		JComboBox filterComboBox = new JComboBox();
		filterComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Filter action performed");
				populateFromFilter(((StoreFilter) filterComboBox.getSelectedItem()).name());
			}
		});
		filterComboBox.setModel(new DefaultComboBoxModel(StoreFilter.values()));
		GridBagConstraints gbc_filterComboBox = new GridBagConstraints();
		gbc_filterComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_filterComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterComboBox.gridx = 2;
		gbc_filterComboBox.gridy = 1;
		frame.getContentPane().add(filterComboBox, gbc_filterComboBox);
		
		JButton buyButton = new JButton("BUY");
		GridBagConstraints gbc_buyButton = new GridBagConstraints();
		gbc_buyButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_buyButton.insets = new Insets(0, 0, 5, 5);
		gbc_buyButton.gridx = 5;
		gbc_buyButton.gridy = 1;
		frame.getContentPane().add(buyButton, gbc_buyButton);
		
//		merchScrollPane = new JScrollPane();
//		GridBagConstraints gbc_merchScrollPane = new GridBagConstraints();
//		gbc_merchScrollPane.gridwidth = 5;
//		gbc_merchScrollPane.insets = new Insets(0, 0, 0, 5);
//		gbc_merchScrollPane.fill = GridBagConstraints.BOTH;
//		gbc_merchScrollPane.gridx = 1;
//		gbc_merchScrollPane.gridy = 2;
//		frame.getContentPane().add(merchScrollPane, gbc_merchScrollPane);
		
		panel = new StoreDisplayPanel(m_cart);
		panel.setPreferredSize(new Dimension(400, 300));
		GridBagConstraints gbc_merchScrollPane = new GridBagConstraints();
		gbc_merchScrollPane.anchor = GridBagConstraints.NORTH;
		gbc_merchScrollPane.gridwidth = 5;
		gbc_merchScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_merchScrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_merchScrollPane.gridx = 1;
		gbc_merchScrollPane.gridy = 2;
		populateFromFilter("ALL");
		frame.getContentPane().add(panel, gbc_merchScrollPane);
	}
	
	/**
	 * Method to filter out the unwanted items from the store display.
	 * Displaying player inventory count currently broken.
	 * @param filter Store filter for items
	 */
	private void populateFromFilter(String filter)
	{
		panel.removeAll();
		//Make a new merchandise wrapper that is a deep copy of the General Store's
		MerchandiseWrapper merch = super.getMerchandise().clone();
		//Temporary; makes playerInv match the store inventory
		playerInv = merch.clone();		
		if(filter != "ALL")
		{
			if(filter != "ANIMALS")
			{
				for(Animal animal : merch.getAnimals())
				{
					merch.remove((Merchandise) animal);
				}
			}
			if(filter != "CROPS")
			{
				for(Crop crop : merch.getCrops())
				{
					merch.remove((Merchandise) crop);
				}
			}
			if(filter != "ITEMS")
			{
				for(Item item : merch.getItems())
				{
					merch.remove((Merchandise) item);
				}
			}
		}
		
		panel.refreshDisplay(merch, playerInv);
		
		//Refresh the panel to reflect the changes
		panel.revalidate();
		panel.repaint();
	}
	

	
	
	
	
//	private void populateScrollPane(ArrayList<Merchandise> merch)
//	{
//		StoreDisplayPanel newDisplay = new StoreDisplayPanel();
//		merchScrollPane.removeAll();
//		
//		
//		//Finally, add populated display to scroll pane
//        merchScrollPane.add(newDisplay);
//		
//	}

}
