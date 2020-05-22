package gameScreens;

import javax.swing.JPanel;

import gameLogic.Merchandise;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Component;
import javax.swing.Box;

public class CartPanel extends JPanel {
	ArrayList<Merchandise> merch;

	/**
	 * Create the panel.
	 */
	public CartPanel(ArrayList<Merchandise> cart) {
		merch = cart;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Component hGlueLeft = Box.createHorizontalGlue();
		GridBagConstraints gbc_hGlueLeft = new GridBagConstraints();
		gbc_hGlueLeft.insets = new Insets(0, 0, 0, 5);
		gbc_hGlueLeft.gridx = 0;
		gbc_hGlueLeft.gridy = 0;
		add(hGlueLeft, gbc_hGlueLeft);
		
		JLabel lblProductHeader = new JLabel("Product");
		GridBagConstraints gbc_lblProductHeader = new GridBagConstraints();
		gbc_lblProductHeader.insets = new Insets(0, 0, 0, 5);
		gbc_lblProductHeader.gridx = 1;
		gbc_lblProductHeader.gridy = 0;
		add(lblProductHeader, gbc_lblProductHeader);
		
		Component hStrutProductAmt = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_hStrutProductAmt = new GridBagConstraints();
		gbc_hStrutProductAmt.insets = new Insets(0, 0, 0, 5);
		gbc_hStrutProductAmt.gridx = 2;
		gbc_hStrutProductAmt.gridy = 0;
		add(hStrutProductAmt, gbc_hStrutProductAmt);
		
		JLabel lblAmountHeader = new JLabel("Amount");
		GridBagConstraints gbc_lblAmountHeader = new GridBagConstraints();
		gbc_lblAmountHeader.insets = new Insets(0, 0, 0, 5);
		gbc_lblAmountHeader.gridx = 3;
		gbc_lblAmountHeader.gridy = 0;
		add(lblAmountHeader, gbc_lblAmountHeader);
		
		Component hStrutAmtPrice = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_hStrutAmtPrice = new GridBagConstraints();
		gbc_hStrutAmtPrice.insets = new Insets(0, 0, 0, 5);
		gbc_hStrutAmtPrice.gridx = 4;
		gbc_hStrutAmtPrice.gridy = 0;
		add(hStrutAmtPrice, gbc_hStrutAmtPrice);
		
		JLabel lblPrice = new JLabel("Price ($)");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrice.gridx = 5;
		gbc_lblPrice.gridy = 0;
		add(lblPrice, gbc_lblPrice);
		
		Component hGlueRight = Box.createHorizontalGlue();
		GridBagConstraints gbc_hGlueRight = new GridBagConstraints();
		gbc_hGlueRight.gridx = 6;
		gbc_hGlueRight.gridy = 0;
		add(hGlueRight, gbc_hGlueRight);
		
		populateCart();

	}
	
	/**
	 * Populate the panel with one row per uniquely named merchandise in cart
	 */
	private void populateCart()
	{
		//De-duped copy of merch
		ArrayList<Merchandise> compactedList = new ArrayList<Merchandise>();
		int index = 0;
		//De-duping here has worst-case n^2, to be fixed if there's enough time
		for(Merchandise m : merch)
		{
			index = 0;
			boolean found = false;
			while(index < compactedList.size())
			{
				if(m.getName() == compactedList.get(index).getName())
				{
					found = true;
					break;
				}
			}
			if(!found)
			{
				compactedList.add(m.clone());
			}
		}
		
		int currentRow;
		for(int i = 0; i < compactedList.size(); i++)
		{
			Merchandise current = compactedList.get(i);
			currentRow = i + 1;
			int invAmount = 0;
			//Count how many of the particular merch exists in the player inventory
			for(Merchandise inv : merch)
			{
				if (current.getName() == inv.getName())
				{
					invAmount++;
				}
			}
			
			Component hGlueLeft = Box.createHorizontalGlue();
			GridBagConstraints gbc_hGlueLeft = new GridBagConstraints();
			gbc_hGlueLeft.insets = new Insets(0, 0, 0, 5);
			gbc_hGlueLeft.gridx = 0;
			gbc_hGlueLeft.gridy = currentRow;
			add(hGlueLeft, gbc_hGlueLeft);
			
			JLabel lblProductHeader = new JLabel(current.getName());
			GridBagConstraints gbc_lblProductHeader = new GridBagConstraints();
			gbc_lblProductHeader.insets = new Insets(0, 0, 0, 5);
			gbc_lblProductHeader.gridx = 1;
			gbc_lblProductHeader.gridy = currentRow;
			add(lblProductHeader, gbc_lblProductHeader);
			
			Component hStrutProductAmt = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_hStrutProductAmt = new GridBagConstraints();
			gbc_hStrutProductAmt.insets = new Insets(0, 0, 0, 5);
			gbc_hStrutProductAmt.gridx = 2;
			gbc_hStrutProductAmt.gridy = currentRow;
			add(hStrutProductAmt, gbc_hStrutProductAmt);
			
			JLabel lblAmountHeader = new JLabel(Integer.toString(invAmount));
			GridBagConstraints gbc_lblAmountHeader = new GridBagConstraints();
			gbc_lblAmountHeader.insets = new Insets(0, 0, 0, 5);
			gbc_lblAmountHeader.gridx = 3;
			gbc_lblAmountHeader.gridy = currentRow;
			add(lblAmountHeader, gbc_lblAmountHeader);
			
			Component hStrutAmtPrice = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_hStrutAmtPrice = new GridBagConstraints();
			gbc_hStrutAmtPrice.insets = new Insets(0, 0, 0, 5);
			gbc_hStrutAmtPrice.gridx = 4;
			gbc_hStrutAmtPrice.gridy = currentRow;
			add(hStrutAmtPrice, gbc_hStrutAmtPrice);
			
			JLabel lblPrice = new JLabel(Integer.toString(invAmount * current.getPurchasePrice()));
			GridBagConstraints gbc_lblPrice = new GridBagConstraints();
			gbc_lblPrice.insets = new Insets(0, 0, 0, 5);
			gbc_lblPrice.gridx = 5;
			gbc_lblPrice.gridy = currentRow;
			add(lblPrice, gbc_lblPrice);
			
			Component hGlueRight = Box.createHorizontalGlue();
			GridBagConstraints gbc_hGlueRight = new GridBagConstraints();
			gbc_hGlueRight.gridx = 6;
			gbc_hGlueRight.gridy = currentRow;
			add(hGlueRight, gbc_hGlueRight);
		}
	}

}
