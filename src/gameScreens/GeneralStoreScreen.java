
package gameScreens;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import gameLogic.GeneralStore;
import gameLogic.Merchandise;
import gameLogic.MerchandiseWrapper;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneralStoreScreen{

	public JFrame frameGeneralStore;
	private JTextField textFieldCartTotal;
	private StoreDisplayPanel panelStoreDisplay;
	private JComboBox<StoreFilter> comboBoxFilter;
	private MerchandiseWrapper m_cart;
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

		m_cart = m_backend.getShoppingCart();
		
		frameGeneralStore = new JFrame();
		frameGeneralStore.setTitle("General Store");
		frameGeneralStore.setBounds(100, 100, 401, 570);
		frameGeneralStore.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 63, 81, 35, 74, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frameGeneralStore.getContentPane().setLayout(gridBagLayout);
		
		Component leftHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_leftHStrut = new GridBagConstraints();
		gbc_leftHStrut.insets = new Insets(0, 0, 5, 5);
		gbc_leftHStrut.gridx = 0;
		gbc_leftHStrut.gridy = 0;
		frameGeneralStore.getContentPane().add(leftHStrut, gbc_leftHStrut);
		
		Component middleHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_middleHStrut = new GridBagConstraints();
		gbc_middleHStrut.insets = new Insets(0, 0, 5, 5);
		gbc_middleHStrut.gridx = 3;
		gbc_middleHStrut.gridy = 0;
		frameGeneralStore.getContentPane().add(middleHStrut, gbc_middleHStrut);
		
		JLabel lblItemsInCart = new JLabel("Items in Cart:");
		GridBagConstraints gbc_lblItemsInCart = new GridBagConstraints();
		gbc_lblItemsInCart.anchor = GridBagConstraints.EAST;
		gbc_lblItemsInCart.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemsInCart.gridx = 4;
		gbc_lblItemsInCart.gridy = 0;
		frameGeneralStore.getContentPane().add(lblItemsInCart, gbc_lblItemsInCart);
		
		textFieldAmtInCart = new JTextField();
		textFieldAmtInCart.setEditable(false);
		GridBagConstraints gbc_textFieldAmtInCart = new GridBagConstraints();
		gbc_textFieldAmtInCart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAmtInCart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAmtInCart.gridx = 5;
		gbc_textFieldAmtInCart.gridy = 0;
		frameGeneralStore.getContentPane().add(textFieldAmtInCart, gbc_textFieldAmtInCart);
		textFieldAmtInCart.setColumns(10);
		
		JLabel lblFilter = new JLabel("Filter");
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.EAST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 1;
		frameGeneralStore.getContentPane().add(lblFilter, gbc_lblFilter);
		
		comboBoxFilter = new JComboBox<StoreFilter>();
		//Refresh store display whenever the filter changes
		comboBoxFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshDisplay();
			}
		});
		
		comboBoxFilter.setModel(new DefaultComboBoxModel<StoreFilter>(StoreFilter.values()));
		GridBagConstraints gbc_comboBoxFilter = new GridBagConstraints();
		gbc_comboBoxFilter.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFilter.gridx = 2;
		gbc_comboBoxFilter.gridy = 1;
		frameGeneralStore.getContentPane().add(comboBoxFilter, gbc_comboBoxFilter);
		
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
				refreshDisplay();
			}
		});
		GridBagConstraints gbc_btnAddToCart = new GridBagConstraints();
		gbc_btnAddToCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToCart.gridx = 2;
		gbc_btnAddToCart.gridy = 2;
		frameGeneralStore.getContentPane().add(btnAddToCart, gbc_btnAddToCart);
		
		JLabel lblCartTotal = new JLabel("Total($):");
		GridBagConstraints gbc_lblCartTotal = new GridBagConstraints();
		gbc_lblCartTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartTotal.anchor = GridBagConstraints.EAST;
		gbc_lblCartTotal.gridx = 4;
		gbc_lblCartTotal.gridy = 1;
		frameGeneralStore.getContentPane().add(lblCartTotal, gbc_lblCartTotal);
		
		textFieldCartTotal = new JTextField();
		textFieldCartTotal.setEditable(false);
		GridBagConstraints gbc_textFieldCartTotal = new GridBagConstraints();
		gbc_textFieldCartTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCartTotal.anchor = GridBagConstraints.WEST;
		gbc_textFieldCartTotal.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCartTotal.gridx = 5;
		gbc_textFieldCartTotal.gridy = 1;
		frameGeneralStore.getContentPane().add(textFieldCartTotal, gbc_textFieldCartTotal);
		textFieldCartTotal.setColumns(10);		
		
		Component rightHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_rightHStrut = new GridBagConstraints();
		gbc_rightHStrut.insets = new Insets(0, 0, 5, 0);
		gbc_rightHStrut.gridx = 6;
		gbc_rightHStrut.gridy = 0;
		frameGeneralStore.getContentPane().add(rightHStrut, gbc_rightHStrut);
		
		JButton btnClearCart = new JButton("CLEAR");
		btnClearCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_backend.clearCart();
			}
		});
		GridBagConstraints gbc_btnClearCart = new GridBagConstraints();
		gbc_btnClearCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearCart.gridx = 4;
		gbc_btnClearCart.gridy = 2;
		frameGeneralStore.getContentPane().add(btnClearCart, gbc_btnClearCart);
		
		JButton btnBuy = new JButton("BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					m_backend.checkout();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}				
			}
		});
		GridBagConstraints gbc_btnBuy = new GridBagConstraints();
		gbc_btnBuy.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuy.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuy.gridx = 5;
		gbc_btnBuy.gridy = 2;
		frameGeneralStore.getContentPane().add(btnBuy, gbc_btnBuy);
		
		panelStoreDisplay = new StoreDisplayPanel(m_backend.getPurchaseDiscountMod());
		panelStoreDisplay.setPreferredSize(new Dimension(400, 300));
		GridBagConstraints gbc_panelStoreDisplay = new GridBagConstraints();
		gbc_panelStoreDisplay.anchor = GridBagConstraints.NORTH;
		gbc_panelStoreDisplay.gridwidth = 5;
		gbc_panelStoreDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_panelStoreDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelStoreDisplay.gridx = 1;
		gbc_panelStoreDisplay.gridy = 3;
		refreshDisplay();
		frameGeneralStore.getContentPane().add(panelStoreDisplay, gbc_panelStoreDisplay);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_backend.clearCart();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.anchor = GridBagConstraints.NORTH;
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 5;
		gbc_btnExit.gridy = 4;
		frameGeneralStore.getContentPane().add(btnExit, gbc_btnExit);
	}
	
	/**
	 * Method to filter out the unwanted items from the store display.
	 * Displaying player inventory count currently broken.
	 * @param filter Store filter for items
	 */
	public void refreshDisplay()
	{
		panelStoreDisplay.refreshDisplay(getStoreFilter()
											, m_backend.getMerchandise().getMerchList()
											, m_backend.getPlayerMerchandise().getMerchList());
		
		//Refresh the panel to reflect the changes
		panelStoreDisplay.revalidate();
		panelStoreDisplay.repaint();
	}
	
	/**
	 * Update the Cart Total textfield to reflect cost of current cart
	 */
	private void updateCartTotal()
	{
		textFieldCartTotal.setText(Integer.toString(m_cart.getCartPrice()));
	}
	
	/**
	 * Update the display of number of items in cart
	 */
	private void updateCartSize()
	{
		textFieldAmtInCart.setText(Integer.toString(m_cart.size()));
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

	/**
	 * Set visibility of the GeneralStoreScreen
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		frameGeneralStore.setVisible(visible);
	}
	
	/**
	 * Get store filter
	 */
	public StoreFilter getStoreFilter()
	{
		return (StoreFilter)comboBoxFilter.getSelectedItem();
	}
	
	public void refreshCart() {
		updateCartSize();
		updateCartTotal();
	}
}

