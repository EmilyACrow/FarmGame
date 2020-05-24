package gameScreens;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import gameLogic.Merchandise;
import gameLogic.MerchandiseWrapper;

public class StoreDisplayPanel extends JPanel {
	private ArrayList<JTextField> amountTextFields;
	private JTextField amtInInvTextField;
	private int numMerchInPanel = 0;
	private ArrayList<Merchandise> merchDisplayed;
	private JTextField amtSelectedTextField;
	private double m_discount;
	//Textfield from containing panel - can attach listener to use as flag for when to update outer values

	
	/**
	 * Create the panel.
	 * @param discountMod Player's store discount, determined off of FarmType
	 */
	public StoreDisplayPanel(double discountMod) {
		
		amountTextFields = new ArrayList<JTextField>();
		merchDisplayed = new ArrayList<Merchandise>();
		m_discount = discountMod;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{32, 105, 39, 0, 44, 0, 48, 66, 30, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
	}
	
	/**
	 * Add a new item to the panel. Note there is no remove option.
	 * To remove an item, delete the panel and rebuild it. Slow but simple. 
	 * @param filter Currently selected filter for store
	 * @param storeMerch ArrayList of all store Merchandise
	 * @param playerMerch ArrayList of all of the player's merchandise
	 */
	public void refreshDisplay(StoreFilter filter, ArrayList<Merchandise> storeMerch, ArrayList<Merchandise> playerMerch)
	{
		//Clear current display
		merchDisplayed.clear();
		amountTextFields.clear();
		removeAll();
		numMerchInPanel = 0;
		
		addHeader();
		
		//Use some regex to figure out what gets displayed
		String allowedMerchRegex = "";
		switch(filter)
		{
		case ALL:
			allowedMerchRegex = "(Animal|Crop|Item)";
			break;
		case ANIMALS:
			allowedMerchRegex = "(Animal)";
			break;
		case CROPS:
			allowedMerchRegex = "(Crop)";
			break;
		case ITEMS:
			allowedMerchRegex = "(Item)";
			break;	
		}

		for(Merchandise m : storeMerch)
		{
			//Filter out undesired Merchandise objects
			if(!(m.getClass().getSimpleName().matches(allowedMerchRegex)))
			{
				continue;
			}
			int invAmount = 0;
			//Count how many of the particular merch exists in the player inventory
			for(Merchandise inv : playerMerch)
			{
				if (m.getName() == inv.getName())
				{
					invAmount++;
				}
			}
			
			JLabel lblProductIndex = new JLabel(String.format("%d:",numMerchInPanel + 1));
			GridBagConstraints gbc_lblProductIndex = new GridBagConstraints();
			gbc_lblProductIndex.insets = new Insets(0, 0, 0, 5);
			gbc_lblProductIndex.gridx = 0;
			gbc_lblProductIndex.gridy = numMerchInPanel + 1;
			add(lblProductIndex, gbc_lblProductIndex);
			
			JLabel lblProductName = new JLabel(m.getName());
			GridBagConstraints gbc_lblProductName = new GridBagConstraints();
			gbc_lblProductName.insets = new Insets(0, 0, 0, 5);
			gbc_lblProductName.gridx = 1;
			gbc_lblProductName.gridy = numMerchInPanel + 1;
			add(lblProductName, gbc_lblProductName);
			
			Component productAmountHGlue = Box.createHorizontalGlue();
			GridBagConstraints gbc_productAmountHGlue = new GridBagConstraints();
			gbc_productAmountHGlue.insets = new Insets(0, 0, 0, 5);
			gbc_productAmountHGlue.gridx = 2;
			gbc_productAmountHGlue.gridy = numMerchInPanel + 1;
			add(productAmountHGlue, gbc_productAmountHGlue);
			
			JTextField fieldSelectionBox = new JTextField("0");
			amountTextFields.add(fieldSelectionBox);
			fieldSelectionBox.setEditable(true);
			GridBagConstraints gbc_AmountTextField = new GridBagConstraints();
			gbc_AmountTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_AmountTextField.insets = new Insets(0, 0, 0, 5);
			gbc_AmountTextField.gridx = 4;
			gbc_AmountTextField.gridy = numMerchInPanel + 1;
			fieldSelectionBox.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					//More regex!
					String validInput = "[0-9]+";
					if(!fieldSelectionBox.getText().matches(validInput))
					{
						fieldSelectionBox.setText("0");
					}
					refreshSelectedCount();
				}
			});
			fieldSelectionBox.addActionListener(new ActionListener() {
				//Make sure that the number input is actually a number
				public void actionPerformed(ActionEvent arg0) {
					boolean valid = true;
					for(char c : fieldSelectionBox.getText().toCharArray())
					{
						if(!Character.isDigit(c))
						{
							valid = false;
						}
					}
					if(!valid)
					{
						fieldSelectionBox.setText("0");
					}
					refreshSelectedCount();
				}
			});
			add(fieldSelectionBox, gbc_AmountTextField);
			fieldSelectionBox.setColumns(4);
			
			//calculation for any discounts.
			double amountRemoved = m.getPurchasePrice() * m_discount;
			int finalCost = (int) (m.getPurchasePrice() - amountRemoved); 
			if(finalCost == 0)
			{
				finalCost = 1;
			}
			JLabel lblUnitLabel = new JLabel(String.format("($%d)", finalCost));
			GridBagConstraints gbc_lblUnitLabel = new GridBagConstraints();
			gbc_lblUnitLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblUnitLabel.gridx = 5;
			gbc_lblUnitLabel.gridy = numMerchInPanel + 1;
			add(lblUnitLabel, gbc_lblUnitLabel);
			
			Component amtInvHGlue = Box.createHorizontalGlue();
			GridBagConstraints gbc_amtInvHGlue = new GridBagConstraints();
			gbc_amtInvHGlue.insets = new Insets(0, 0, 0, 5);
			gbc_amtInvHGlue.gridx = 6;
			gbc_amtInvHGlue.gridy = numMerchInPanel + 1;
			add(amtInvHGlue, gbc_amtInvHGlue);
			
			amtInInvTextField = new JTextField(Integer.toString(invAmount));
			amtInInvTextField.setEditable(false);
			GridBagConstraints gbc_amtInInvTextField = new GridBagConstraints();
			gbc_amtInInvTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_amtInInvTextField.insets = new Insets(0, 0, 0, 5);
			gbc_amtInInvTextField.gridx = 7;
			gbc_amtInInvTextField.gridy = numMerchInPanel + 1;
			add(amtInInvTextField, gbc_amtInInvTextField);
			amtInInvTextField.setColumns(4);
			
			Component rightHGlue = Box.createHorizontalGlue();
			GridBagConstraints gbc_rightHGlue = new GridBagConstraints();
			gbc_rightHGlue.gridx = 8;
			gbc_rightHGlue.gridy = numMerchInPanel + 1;
			add(rightHGlue, gbc_rightHGlue);
			
			merchDisplayed.add(m);
			numMerchInPanel++;
			refreshSelectedCount();
		}
		
	}
	
	public void refreshSelectedCount()
	{
		Integer amt = 0;
		for(JTextField field : amountTextFields)
		{
			if(field.getText() == "")
			{
				field.setText("0");
			}
			amt += Integer.parseInt(field.getText());
		}
		amtSelectedTextField.setText(amt.toString());
	}
	
	private void addHeader()
	{
		JLabel lblItemsSelected = new JLabel("Items selected:");
		GridBagConstraints gbc_lblItemsSelected = new GridBagConstraints();
		gbc_lblItemsSelected.anchor = GridBagConstraints.EAST;
		gbc_lblItemsSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemsSelected.gridx = 1;
		gbc_lblItemsSelected.gridy = 0;
		add(lblItemsSelected, gbc_lblItemsSelected);
		
		amtSelectedTextField = new JTextField();
		amtSelectedTextField.setEditable(false);
		GridBagConstraints gbc_amtSelectedTextField = new GridBagConstraints();
		gbc_amtSelectedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_amtSelectedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_amtSelectedTextField.gridx = 2;
		gbc_amtSelectedTextField.gridy = 0;
		gbc_amtSelectedTextField.gridwidth = 3;
		add(amtSelectedTextField, gbc_amtSelectedTextField);
		amtSelectedTextField.setColumns(6);
		
		JLabel lblUnitPrice = new JLabel("(Unit Price)");
		GridBagConstraints gbc_lblUnitPrice = new GridBagConstraints();
		gbc_lblUnitPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnitPrice.gridx = 5;
		gbc_lblUnitPrice.gridy = 0;
		gbc_lblUnitPrice.anchor = GridBagConstraints.WEST;
		add(lblUnitPrice, gbc_lblUnitPrice);
		
		JLabel lblInventoryAmt = new JLabel("Your Inventory");
		GridBagConstraints gbc_lblInventoryAmt = new GridBagConstraints();
		gbc_lblInventoryAmt.gridwidth = 3;
		gbc_lblInventoryAmt.insets = new Insets(0, 0, 5, 5);
		gbc_lblInventoryAmt.gridx = 6;
		gbc_lblInventoryAmt.gridy = 0;
		add(lblInventoryAmt, gbc_lblInventoryAmt);
		
		
		
	}
	
	public ArrayList<Merchandise> addToCart()
	{
		ArrayList<Merchandise> cart = new ArrayList<Merchandise>();
		
		for(int i = 0; i < merchDisplayed.size(); i++)
		{
			int amtToAdd = Integer.parseInt(amountTextFields.get(i).getText());
			for(int j = 0; j < amtToAdd; j++)
			{
				//Add deep copy of merch
				cart.add(merchDisplayed.get(i).clone());
			}
		}
		
		//Clear selection after adding to cart
		//refreshDisplay(merchDisplayed, cart);
		
		return cart;
	}

}