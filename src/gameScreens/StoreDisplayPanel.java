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
	private ArrayList<Merchandise> m_cart;
	private JTextField amtSelectedTextField;

	
	/**
	 * Create the panel.
	 */
	public StoreDisplayPanel(ArrayList<Merchandise> cart) {
		
		amountTextFields = new ArrayList<JTextField>();
		merchDisplayed = new ArrayList<Merchandise>();
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{32, 105, 39, 0, 44, 0, 48, 66, 30, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		m_cart = cart;
		
	}
	
	/**
	 * Add a new item to the panel. Note there is no remove option.
	 * To remove an item, delete the panel and rebuild it. Slow but simple. 
	 * @param name Name of the product to be put in
	 * @param amtInInventory Amount of the product in the player's inventory
	 */
	public void refreshDisplay(MerchandiseWrapper storeMerch, MerchandiseWrapper playerMerch)
	{
		//Clear current display
		merchDisplayed.clear();
		amountTextFields.clear();
		numMerchInPanel = 0;
		
		addHeader();
		
		for(Merchandise display : storeMerch.getMerchList())
		{
			int invAmount = 0;
			//Count how many of the particular merch exists in the player inventory
			for(Merchandise inv : playerMerch.getMerchList())
			{
				if (display.getName() == inv.getName())
				{
					invAmount++;
				}
			}
			
			JLabel lblProductIndex = new JLabel(String.format("%d:",numMerchInPanel));
			GridBagConstraints gbc_lblProductIndex = new GridBagConstraints();
			gbc_lblProductIndex.insets = new Insets(0, 0, 0, 5);
			gbc_lblProductIndex.gridx = 0;
			gbc_lblProductIndex.gridy = numMerchInPanel + 1;
			add(lblProductIndex, gbc_lblProductIndex);
			
			JLabel lblProductName = new JLabel(display.getName());
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
			
			JTextField newField = new JTextField("0");
			amountTextFields.add(newField);
			newField.setEditable(true);
			GridBagConstraints gbc_AmountTextField = new GridBagConstraints();
			gbc_AmountTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_AmountTextField.insets = new Insets(0, 0, 0, 5);
			gbc_AmountTextField.gridx = 4;
			gbc_AmountTextField.gridy = numMerchInPanel + 1;
			newField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					boolean valid = true;
					for(char c : newField.getText().toCharArray())
					{
						if(!Character.isDigit(c))
						{
							valid = false;
						}
					}
					if(!valid)
					{
						newField.setText("0");
					}
					refreshSelectedCount();
				}
			});
			newField.addActionListener(new ActionListener() {
				//Make sure that the number input is actually a number
				public void actionPerformed(ActionEvent arg0) {
					boolean valid = true;
					for(char c : newField.getText().toCharArray())
					{
						if(!Character.isDigit(c))
						{
							valid = false;
						}
					}
					if(!valid)
					{
						newField.setText("0");
					}
					refreshSelectedCount();
				}
			});
			add(newField, gbc_AmountTextField);
			newField.setColumns(4);
			
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
			
			merchDisplayed.add(display);
			numMerchInPanel++;
			refreshSelectedCount();
		}
		
	}
	
	public void refreshSelectedCount()
	{
		Integer amt = 0;
		for(JTextField field : amountTextFields)
		{
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
		add(amtSelectedTextField, gbc_amtSelectedTextField);
		amtSelectedTextField.setColumns(6);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		GridBagConstraints gbc_btnAddToCart = new GridBagConstraints();
		gbc_btnAddToCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToCart.gridx = 4;
		gbc_btnAddToCart.gridy = 0;
		add(btnAddToCart, gbc_btnAddToCart);
	}

}
