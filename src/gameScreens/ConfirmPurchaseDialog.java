package gameScreens;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import gameLogic.GeneralStore;
import gameLogic.MerchandiseWrapper;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

public class ConfirmPurchaseDialog extends JDialog {
	
	private JTextField textFieldTotalCost;
	private JTextField textFieldPlayerMoney;
	private JTextPane textPaneMsgDisplay;
	private MerchandiseWrapper m_cart;
	private Integer m_playerMoney;
	private GeneralStore m_backend;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ConfirmPurchaseDialog dialog = new ConfirmPurchaseDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param store Reference to GeneralStore this is attached to
	 * @param cart Cart of Merchandise player wants to buy
	 * @param playerMoney How much money the player has
	 * @param finalPrice Price of cart, adjusted for player discount
	 */
	public ConfirmPurchaseDialog(GeneralStore store, MerchandiseWrapper cart, int playerMoney, int finalPrice) {
		setTitle("Checkout");
		m_backend = store;
		m_playerMoney = playerMoney;
		m_cart = cart.clone();
		
		setBounds(100, 100, 450, 530);
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 125, 50, 108, 53, 0};
		gridBagLayout.rowHeights = new int[]{56, 0, 0, 0, 60, 50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblCartList = new JLabel("Your cart");
		lblCartList.setFont(new Font("Sitka Text", Font.BOLD, 18));
		GridBagConstraints gbc_lblCartList = new GridBagConstraints();
		gbc_lblCartList.gridwidth = 3;
		gbc_lblCartList.insets = new Insets(0, 0, 5, 5);
		gbc_lblCartList.gridx = 1;
		gbc_lblCartList.gridy = 0;
		getContentPane().add(lblCartList, gbc_lblCartList);
		
		JScrollPane scrollPaneCart = new JScrollPane(new CartPanel(m_cart.getMerchList()));
		GridBagConstraints gbc_scrollPaneCart = new GridBagConstraints();
		gbc_scrollPaneCart.gridwidth = 3;
		gbc_scrollPaneCart.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneCart.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCart.gridx = 1;
		gbc_scrollPaneCart.gridy = 1;
		getContentPane().add(scrollPaneCart, gbc_scrollPaneCart);
		
		JLabel lblTotalCost = new JLabel("Total Cost:");
		lblTotalCost.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
		gbc_lblTotalCost.anchor = GridBagConstraints.EAST;
		gbc_lblTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCost.gridx = 1;
		gbc_lblTotalCost.gridy = 2;
		getContentPane().add(lblTotalCost, gbc_lblTotalCost);
		
		textFieldTotalCost = new JTextField(Integer.toString(finalPrice));
		textFieldTotalCost.setEditable(false);
		GridBagConstraints gbc_textFieldTotalCost = new GridBagConstraints();
		gbc_textFieldTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTotalCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTotalCost.gridx = 2;
		gbc_textFieldTotalCost.gridy = 2;
		getContentPane().add(textFieldTotalCost, gbc_textFieldTotalCost);
		textFieldTotalCost.setColumns(10);
		
		JLabel lblPlayerMoney = new JLabel("Your money:");
		GridBagConstraints gbc_lblPlayerMoney = new GridBagConstraints();
		gbc_lblPlayerMoney.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerMoney.gridx = 1;
		gbc_lblPlayerMoney.gridy = 3;
		getContentPane().add(lblPlayerMoney, gbc_lblPlayerMoney);
		
		textFieldPlayerMoney = new JTextField(m_playerMoney.toString());
		textFieldPlayerMoney.setEditable(false);
		GridBagConstraints gbc_textFieldPlayerMoney = new GridBagConstraints();
		gbc_textFieldPlayerMoney.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPlayerMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPlayerMoney.gridx = 2;
		gbc_textFieldPlayerMoney.gridy = 3;
		getContentPane().add(textFieldPlayerMoney, gbc_textFieldPlayerMoney);
		textFieldPlayerMoney.setColumns(10);
		
		Component leftHGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_leftHGlue = new GridBagConstraints();
		gbc_leftHGlue.insets = new Insets(0, 0, 5, 5);
		gbc_leftHGlue.gridx = 0;
		gbc_leftHGlue.gridy = 4;
		getContentPane().add(leftHGlue, gbc_leftHGlue);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		Component buttonHStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_buttonHStrut = new GridBagConstraints();
		gbc_buttonHStrut.insets = new Insets(0, 0, 5, 5);
		gbc_buttonHStrut.gridx = 2;
		gbc_buttonHStrut.gridy = 4;
		getContentPane().add(buttonHStrut, gbc_buttonHStrut);
		
		JButton btnConfirm = new JButton("CONFIRM");
		if(finalPrice > playerMoney)
		{
			btnConfirm.setEnabled(false);
		}
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(m_backend.canPurchaseCart(playerMoney, finalPrice, m_cart))
					{
						m_backend.purchaseCart(cart, finalPrice);
						dispose();
					}
				}
				catch(Exception except)
				{
					textPaneMsgDisplay.setEnabled(true);
					textPaneMsgDisplay.setText(except.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.fill = GridBagConstraints.BOTH;
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 4;
		getContentPane().add(btnConfirm, gbc_btnConfirm);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue.gridx = 4;
		gbc_horizontalGlue.gridy = 4;
		getContentPane().add(horizontalGlue, gbc_horizontalGlue);
		
		scrollPaneCart.add(new CartPanel(m_cart.getMerchList()));
		
		textPaneMsgDisplay = new JTextPane();
		textPaneMsgDisplay.setEnabled(false);
		textPaneMsgDisplay.setEditable(false);
		GridBagConstraints gbc_textPaneMsgDisplay = new GridBagConstraints();
		gbc_textPaneMsgDisplay.gridwidth = 3;
		gbc_textPaneMsgDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_textPaneMsgDisplay.fill = GridBagConstraints.BOTH;
		gbc_textPaneMsgDisplay.gridx = 1;
		gbc_textPaneMsgDisplay.gridy = 5;
		getContentPane().add(textPaneMsgDisplay, gbc_textPaneMsgDisplay);
	}

}
