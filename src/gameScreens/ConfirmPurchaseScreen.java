package gameScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gameLogic.Merchandise;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmPurchaseScreen {

	private JFrame frame;
	private JTextField textFieldTotalCost;
	private JTextField textFieldPlayerMoney;
	private ArrayList<Merchandise> m_cart;
	private Integer m_playerMoney;


	/**
	 * Create the application.
	 */
	public ConfirmPurchaseScreen(ArrayList<Merchandise> cart, int playerMoney) {
		m_playerMoney = playerMoney;
		m_cart = new ArrayList<Merchandise>();
		for(Merchandise m : cart)
		{
			m_cart.add(m);
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 515);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 125, 50, 108, 53, 0};
		gridBagLayout.rowHeights = new int[]{56, 0, 0, 0, 60, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblCartList = new JLabel("Your cart");
		lblCartList.setFont(new Font("Sitka Text", Font.BOLD, 18));
		GridBagConstraints gbc_lblCartList = new GridBagConstraints();
		gbc_lblCartList.gridwidth = 3;
		gbc_lblCartList.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartList.gridx = 1;
		gbc_lblCartList.gridy = 0;
		frame.getContentPane().add(lblCartList, gbc_lblCartList);
		
		JScrollPane scrollPaneCart = new JScrollPane();
		GridBagConstraints gbc_scrollPaneCart = new GridBagConstraints();
		gbc_scrollPaneCart.gridwidth = 3;
		gbc_scrollPaneCart.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneCart.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCart.gridx = 1;
		gbc_scrollPaneCart.gridy = 1;
		frame.getContentPane().add(scrollPaneCart, gbc_scrollPaneCart);
		
		JLabel lblTotalCost = new JLabel("Total Cost:");
		lblTotalCost.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
		gbc_lblTotalCost.anchor = GridBagConstraints.EAST;
		gbc_lblTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCost.gridx = 1;
		gbc_lblTotalCost.gridy = 2;
		frame.getContentPane().add(lblTotalCost, gbc_lblTotalCost);
		
		textFieldTotalCost = new JTextField(Integer.toString(getCartPrice()));
		textFieldTotalCost.setEditable(false);
		GridBagConstraints gbc_textFieldTotalCost = new GridBagConstraints();
		gbc_textFieldTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTotalCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTotalCost.gridx = 2;
		gbc_textFieldTotalCost.gridy = 2;
		frame.getContentPane().add(textFieldTotalCost, gbc_textFieldTotalCost);
		textFieldTotalCost.setColumns(10);
		
		JLabel lblPlayerMoney = new JLabel("Your money:");
		GridBagConstraints gbc_lblPlayerMoney = new GridBagConstraints();
		gbc_lblPlayerMoney.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerMoney.gridx = 1;
		gbc_lblPlayerMoney.gridy = 3;
		frame.getContentPane().add(lblPlayerMoney, gbc_lblPlayerMoney);
		
		textFieldPlayerMoney = new JTextField(m_playerMoney.toString());
		textFieldPlayerMoney.setEditable(false);
		GridBagConstraints gbc_textFieldPlayerMoney = new GridBagConstraints();
		gbc_textFieldPlayerMoney.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPlayerMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPlayerMoney.gridx = 2;
		gbc_textFieldPlayerMoney.gridy = 3;
		frame.getContentPane().add(textFieldPlayerMoney, gbc_textFieldPlayerMoney);
		textFieldPlayerMoney.setColumns(10);
		
		Component leftHGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_leftHGlue = new GridBagConstraints();
		gbc_leftHGlue.insets = new Insets(0, 0, 5, 5);
		gbc_leftHGlue.gridx = 0;
		gbc_leftHGlue.gridy = 4;
		frame.getContentPane().add(leftHGlue, gbc_leftHGlue);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
		
		Component buttonHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_buttonHStrut = new GridBagConstraints();
		gbc_buttonHStrut.insets = new Insets(0, 0, 5, 5);
		gbc_buttonHStrut.gridx = 2;
		gbc_buttonHStrut.gridy = 4;
		frame.getContentPane().add(buttonHStrut, gbc_buttonHStrut);
		
		JButton btnConfirm = new JButton("CONFIRM");
		if(!playerHasEnoughMoney())
		{
			btnConfirm.setEnabled(false);
		}
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.fill = GridBagConstraints.BOTH;
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 4;
		frame.getContentPane().add(btnConfirm, gbc_btnConfirm);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue.gridx = 4;
		gbc_horizontalGlue.gridy = 4;
		frame.getContentPane().add(horizontalGlue, gbc_horizontalGlue);
		
		scrollPaneCart.add(new CartPanel(m_cart));
	}
	
	private int getCartPrice()
	{
		int price = 0;
		for(Merchandise m : m_cart)
		{
			price += m.getPurchasePrice();
		}
		return price;
	}
	
	private boolean playerHasEnoughMoney()
	{
		return (m_playerMoney > getCartPrice()); 
	}

}
