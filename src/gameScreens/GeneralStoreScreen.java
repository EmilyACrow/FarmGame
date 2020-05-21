package gameScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	private JTextField amtInCartTextField;

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
		
		m_cart = new ArrayList<Merchandise>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 125, 46, 73, 74, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		
		JLabel lblItemsInCart = new JLabel("Items in Cart:");
		GridBagConstraints gbc_lblItemsInCart = new GridBagConstraints();
		gbc_lblItemsInCart.anchor = GridBagConstraints.EAST;
		gbc_lblItemsInCart.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemsInCart.gridx = 4;
		gbc_lblItemsInCart.gridy = 0;
		frame.getContentPane().add(lblItemsInCart, gbc_lblItemsInCart);
		
		amtInCartTextField = new JTextField();
		amtInCartTextField.setEditable(false);
		GridBagConstraints gbc_amtInCartTextField = new GridBagConstraints();
		gbc_amtInCartTextField.insets = new Insets(0, 0, 5, 5);
		gbc_amtInCartTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_amtInCartTextField.gridx = 5;
		gbc_amtInCartTextField.gridy = 0;
		frame.getContentPane().add(amtInCartTextField, gbc_amtInCartTextField);
		amtInCartTextField.setColumns(10);
		
		JLabel lblFilter = new JLabel("Filter");
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.EAST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 1;
		frame.getContentPane().add(lblFilter, gbc_lblFilter);
		
		JComboBox filterComboBox = new JComboBox();
		//Refresh store display whenever the filter changes
		filterComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JButton addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Merchandise> newMerch = panel.addToCart();
				for(Merchandise m : newMerch)
				{
					m_cart.add(m);
				}
				updateCartSize();
				updateCartTotal();
			}
		});
		GridBagConstraints gbc_addToCartBtn = new GridBagConstraints();
		gbc_addToCartBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addToCartBtn.gridx = 2;
		gbc_addToCartBtn.gridy = 2;
		frame.getContentPane().add(addToCartBtn, gbc_addToCartBtn);
		
		JLabel lblCartTotal = new JLabel("Total($):");
		GridBagConstraints gbc_lblCartTotal = new GridBagConstraints();
		gbc_lblCartTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartTotal.anchor = GridBagConstraints.EAST;
		gbc_lblCartTotal.gridx = 4;
		gbc_lblCartTotal.gridy = 1;
		frame.getContentPane().add(lblCartTotal, gbc_lblCartTotal);
		
		cartTotalTextField = new JTextField();
		cartTotalTextField.setEditable(false);
		GridBagConstraints gbc_cartTotalTextField = new GridBagConstraints();
		gbc_cartTotalTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cartTotalTextField.anchor = GridBagConstraints.WEST;
		gbc_cartTotalTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cartTotalTextField.gridx = 5;
		gbc_cartTotalTextField.gridy = 1;
		frame.getContentPane().add(cartTotalTextField, gbc_cartTotalTextField);
		cartTotalTextField.setColumns(10);		
		
		Component rightHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_rightHStrut = new GridBagConstraints();
		gbc_rightHStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightHStrut.gridx = 6;
		gbc_rightHStrut.gridy = 0;
		frame.getContentPane().add(rightHStrut, gbc_rightHStrut);
		
		JButton clearCartBtn = new JButton("CLEAR");
		clearCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_cart.clear();
				updateCartSize();
				updateCartTotal();
			}
		});
		GridBagConstraints gbc_clearCartBtn = new GridBagConstraints();
		gbc_clearCartBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clearCartBtn.gridx = 4;
		gbc_clearCartBtn.gridy = 2;
		frame.getContentPane().add(clearCartBtn, gbc_clearCartBtn);
		
		JButton buyBtn = new JButton("BUY");
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Merchandise m : m_cart)
				{
					System.out.println(m.toString());
				}
			}
		});
		GridBagConstraints gbc_buyBtn = new GridBagConstraints();
		gbc_buyBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_buyBtn.insets = new Insets(0, 0, 5, 5);
		gbc_buyBtn.gridx = 5;
		gbc_buyBtn.gridy = 2;
		frame.getContentPane().add(buyBtn, gbc_buyBtn);
		
		panel = new StoreDisplayPanel(amtInCartTextField);
		panel.setPreferredSize(new Dimension(400, 300));
		GridBagConstraints gbc_merchScrollPane = new GridBagConstraints();
		gbc_merchScrollPane.anchor = GridBagConstraints.NORTH;
		gbc_merchScrollPane.gridwidth = 5;
		gbc_merchScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_merchScrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_merchScrollPane.gridx = 1;
		gbc_merchScrollPane.gridy = 3;
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
		
		panel.refreshDisplay(merch.getMerchList(), playerInv.getMerchList());
		
		//Refresh the panel to reflect the changes
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * Update the Cart Total textfield to reflect cost of current cart
	 */
	private void updateCartTotal()
	{
		Integer totalPrice = 0;
		for(Merchandise m : m_cart)
		{
			totalPrice += m.getPurchasePrice();
		}
		cartTotalTextField.setText(totalPrice.toString());
	}
	
	/**
	 * Update the display of number of items in cart
	 */
	private void updateCartSize()
	{
		amtInCartTextField.setText(Integer.toString(m_cart.size()));
	}

}
