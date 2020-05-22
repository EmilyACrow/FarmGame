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
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GeneralStoreScreen{

	public JFrame frame;
	private JTextField textFieldCartTotal;
	//private JScrollPane merchScrollPane;
	private StoreDisplayPanel panelStoreDisplay;
	private ArrayList<Merchandise> m_playerInventory;
	private ArrayList<Merchandise> m_cart;
	private JTextField textFieldAmtInCart;
	private GeneralStore m_backend;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GeneralStoreScreen(GeneralStore store) {
		m_backend = store;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		m_playerInventory = new ArrayList<Merchandise>();
		m_cart = new ArrayList<Merchandise>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 570);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPopupMenu popupMenuConfirmPurchase = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenuConfirmPurchase);
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
		
		textFieldAmtInCart = new JTextField();
		textFieldAmtInCart.setEditable(false);
		GridBagConstraints gbc_textFieldAmtInCart = new GridBagConstraints();
		gbc_textFieldAmtInCart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAmtInCart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAmtInCart.gridx = 5;
		gbc_textFieldAmtInCart.gridy = 0;
		frame.getContentPane().add(textFieldAmtInCart, gbc_textFieldAmtInCart);
		textFieldAmtInCart.setColumns(10);
		
		JLabel lblFilter = new JLabel("Filter");
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.EAST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 1;
		frame.getContentPane().add(lblFilter, gbc_lblFilter);
		
		JComboBox comboBoxFilter = new JComboBox();
		//Refresh store display whenever the filter changes
		comboBoxFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populateFromFilter(((StoreFilter) comboBoxFilter.getSelectedItem()).name());
			}
		});
		
		comboBoxFilter.setModel(new DefaultComboBoxModel(StoreFilter.values()));
		GridBagConstraints gbc_comboBoxFilter = new GridBagConstraints();
		gbc_comboBoxFilter.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFilter.gridx = 2;
		gbc_comboBoxFilter.gridy = 1;
		frame.getContentPane().add(comboBoxFilter, gbc_comboBoxFilter);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Merchandise> newMerch = panelStoreDisplay.addToCart();
				for(Merchandise m : newMerch)
				{
					m_cart.add(m);
				}
				updateCartSize();
				updateCartTotal();
			}
		});
		GridBagConstraints gbc_btnAddToCart = new GridBagConstraints();
		gbc_btnAddToCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToCart.gridx = 2;
		gbc_btnAddToCart.gridy = 2;
		frame.getContentPane().add(btnAddToCart, gbc_btnAddToCart);
		
		JLabel lblCartTotal = new JLabel("Total($):");
		GridBagConstraints gbc_lblCartTotal = new GridBagConstraints();
		gbc_lblCartTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartTotal.anchor = GridBagConstraints.EAST;
		gbc_lblCartTotal.gridx = 4;
		gbc_lblCartTotal.gridy = 1;
		frame.getContentPane().add(lblCartTotal, gbc_lblCartTotal);
		
		textFieldCartTotal = new JTextField();
		textFieldCartTotal.setEditable(false);
		GridBagConstraints gbc_textFieldCartTotal = new GridBagConstraints();
		gbc_textFieldCartTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCartTotal.anchor = GridBagConstraints.WEST;
		gbc_textFieldCartTotal.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCartTotal.gridx = 5;
		gbc_textFieldCartTotal.gridy = 1;
		frame.getContentPane().add(textFieldCartTotal, gbc_textFieldCartTotal);
		textFieldCartTotal.setColumns(10);		
		
		Component rightHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_rightHStrut = new GridBagConstraints();
		gbc_rightHStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightHStrut.gridx = 6;
		gbc_rightHStrut.gridy = 0;
		frame.getContentPane().add(rightHStrut, gbc_rightHStrut);
		
		JButton btnClearCart = new JButton("CLEAR");
		btnClearCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_cart.clear();
				updateCartSize();
				updateCartTotal();
				populateFromFilter(((StoreFilter) comboBoxFilter.getSelectedItem()).name());
			}
		});
		GridBagConstraints gbc_btnClearCart = new GridBagConstraints();
		gbc_btnClearCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearCart.gridx = 4;
		gbc_btnClearCart.gridy = 2;
		frame.getContentPane().add(btnClearCart, gbc_btnClearCart);
		
		JButton btnBuy = new JButton("BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add all items from cart to player's inventory
				//TODO: Don't do this until verified that the player can afford it
				//popupMenuConfirmPurchase.add(new new ConfirmPurchaseScreen(m_cart, playerMoney, purchaseConfirmed))
				Boolean purchase = false;
				if(confirmPurchase(purchase))
				{
					for(Merchandise m : m_cart)
					{
						m_playerInventory.add(m.clone());
						System.out.println(m.toString());
					}
					populateFromFilter(((StoreFilter) comboBoxFilter.getSelectedItem()).name());
				}
				
			}
		});
		GridBagConstraints gbc_btnBuy = new GridBagConstraints();
		gbc_btnBuy.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuy.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuy.gridx = 5;
		gbc_btnBuy.gridy = 2;
		frame.getContentPane().add(btnBuy, gbc_btnBuy);
		
		panelStoreDisplay = new StoreDisplayPanel(textFieldAmtInCart);
		panelStoreDisplay.setPreferredSize(new Dimension(400, 300));
		GridBagConstraints gbc_panelStoreDisplay = new GridBagConstraints();
		gbc_panelStoreDisplay.anchor = GridBagConstraints.NORTH;
		gbc_panelStoreDisplay.gridwidth = 5;
		gbc_panelStoreDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_panelStoreDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelStoreDisplay.gridx = 1;
		gbc_panelStoreDisplay.gridy = 3;
		populateFromFilter("ALL");
		frame.getContentPane().add(panelStoreDisplay, gbc_panelStoreDisplay);
	}
	
	/**
	 * Method to filter out the unwanted items from the store display.
	 * Displaying player inventory count currently broken.
	 * @param filter Store filter for items
	 */
	private void populateFromFilter(String filter)
	{
		panelStoreDisplay.removeAll();
		//Make a new merchandise wrapper that is a deep copy of the General Store's
		MerchandiseWrapper merch = m_backend.getMerchandise().clone();
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
		
		panelStoreDisplay.refreshDisplay(merch.getMerchList(), m_playerInventory);
		
		//Refresh the panel to reflect the changes
		panelStoreDisplay.revalidate();
		panelStoreDisplay.repaint();
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
		textFieldCartTotal.setText(totalPrice.toString());
	}
	
	/**
	 * Update the display of number of items in cart
	 */
	private void updateCartSize()
	{
		textFieldAmtInCart.setText(Integer.toString(m_cart.size()));
	}
	
	/**
	 * Tell the panel what to look at for the player's inventory
	 * @param inventory ArrayList<Merchandise> representing the player's inventory
	 */
	public void updatePlayerInventory(ArrayList<Merchandise> inventory)
	{
		//Shallow copy
		m_playerInventory = inventory;
	}	

	/**
	 * Setter for the attached GeneralStore
	 * @param store Instance of GeneralStore
	 */
	public void setBackend(GeneralStore store)
	{
		m_backend = store;
	}
	
	/**
	 * Getter for attached GeneralStore
	 * @return Instance of GeneralStore
	 */
	public GeneralStore getBackend()
	{
		return m_backend;
	}
	
	private Boolean confirmPurchase(Boolean confirmed)
	{
		Boolean purchaseConfirmed = false;
		int playerMoney = m_backend.getPlayerMoney();
		
		System.out.println("Dialog please");
		ConfirmPurchaseDialog confirmDialog = new ConfirmPurchaseDialog(m_cart, playerMoney, purchaseConfirmed, m_backend);
		//confirmDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		confirmDialog.setVisible(true);
		
		return purchaseConfirmed;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
