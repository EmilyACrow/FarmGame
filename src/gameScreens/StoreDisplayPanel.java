package gameScreens;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;

public class StoreDisplayPanel extends JPanel {
	private JTextField amountTextFIeld;
	private JTextField amtInInvTextField;
	private int numMerchInPanel = 0;

	
	/**
	 * Create the panel.
	 */
	public StoreDisplayPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{32, 105, 39, 0, 44, 0, 48, 66, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
	}
	
	/**
	 * Add a new item to the panel. Note there is no remove option.
	 * To remove an item, delete the panel and rebuild it. Slow but simple. 
	 * @param name Name of the product to be put in
	 * @param amtInInventory Amount of the product in the player's inventory
	 */
	public void addMerch(String name, int amtInInventory)
	{
		Component leftHGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_leftHGlue = new GridBagConstraints();
		gbc_leftHGlue.insets = new Insets(0, 0, 0, 5);
		gbc_leftHGlue.gridx = 0;
		gbc_leftHGlue.gridy = numMerchInPanel;
		add(leftHGlue, gbc_leftHGlue);
		
		JLabel lblProductName = new JLabel(name);
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.insets = new Insets(0, 0, 0, 5);
		gbc_lblProductName.gridx = 1;
		gbc_lblProductName.gridy = numMerchInPanel;
		add(lblProductName, gbc_lblProductName);
		
		Component productAmountHGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_productAmountHGlue = new GridBagConstraints();
		gbc_productAmountHGlue.insets = new Insets(0, 0, 0, 5);
		gbc_productAmountHGlue.gridx = 2;
		gbc_productAmountHGlue.gridy = numMerchInPanel;
		add(productAmountHGlue, gbc_productAmountHGlue);
		
		JButton decreaseAmtBtn = new JButton("-");
		GridBagConstraints gbc_decreaseAmtBtn = new GridBagConstraints();
		gbc_decreaseAmtBtn.insets = new Insets(0, 0, 0, 5);
		gbc_decreaseAmtBtn.gridx = 3;
		gbc_decreaseAmtBtn.gridy = numMerchInPanel;
		add(decreaseAmtBtn, gbc_decreaseAmtBtn);
		
		amountTextFIeld = new JTextField("0");
		amountTextFIeld.setEditable(false);
		GridBagConstraints gbc_amountTextFIeld = new GridBagConstraints();
		gbc_amountTextFIeld.fill = GridBagConstraints.HORIZONTAL;
		gbc_amountTextFIeld.insets = new Insets(0, 0, 0, 5);
		gbc_amountTextFIeld.gridx = 4;
		gbc_amountTextFIeld.gridy = numMerchInPanel;
		add(amountTextFIeld, gbc_amountTextFIeld);
		amountTextFIeld.setColumns(4);
		
		JButton increaseAmtBtn = new JButton("+");
		GridBagConstraints gbc_increaseAmtBtn = new GridBagConstraints();
		gbc_increaseAmtBtn.insets = new Insets(0, 0, 0, 5);
		gbc_increaseAmtBtn.gridx = 5;
		gbc_increaseAmtBtn.gridy = numMerchInPanel;
		add(increaseAmtBtn, gbc_increaseAmtBtn);
		
		Component amtInvHGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_amtInvHGlue = new GridBagConstraints();
		gbc_amtInvHGlue.insets = new Insets(0, 0, 0, 5);
		gbc_amtInvHGlue.gridx = 6;
		gbc_amtInvHGlue.gridy = numMerchInPanel;
		add(amtInvHGlue, gbc_amtInvHGlue);
		
		amtInInvTextField = new JTextField(amtInInventory);
		amtInInvTextField.setEditable(false);
		GridBagConstraints gbc_amtInInvTextField = new GridBagConstraints();
		gbc_amtInInvTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_amtInInvTextField.insets = new Insets(0, 0, 0, 5);
		gbc_amtInInvTextField.gridx = 7;
		gbc_amtInInvTextField.gridy = numMerchInPanel;
		add(amtInInvTextField, gbc_amtInInvTextField);
		amtInInvTextField.setColumns(4);
		
		Component rightHGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_rightHGlue = new GridBagConstraints();
		gbc_rightHGlue.gridx = 8;
		gbc_rightHGlue.gridy = numMerchInPanel;
		add(rightHGlue, gbc_rightHGlue);
		
		numMerchInPanel++;
	}

}
