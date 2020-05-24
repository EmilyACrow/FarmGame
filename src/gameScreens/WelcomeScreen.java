package gameScreens;

/**
 * Regex matching based on code found here:
 * https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
 * 
 */

import gameLogic.Farm;
import gameLogic.FarmType;
import gameLogic.Welcome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import gameLogic.FarmType;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class WelcomeScreen {

	private JFrame frmCreateAFarm;
	private JTextField textFieldFarmerName;
	private JTextField textFieldFarmerAge;
	private JTextField textFieldFarmName;
	private JComboBox comboBoxFarmType;
	private JSlider sliderNumDays;
	private JButton btnStartGame;
	private Welcome m_backend;
	private final int MAX_NAME_LENGTH = 64;

	/**
	 * Create the application.
	 */
	public WelcomeScreen(Welcome backend) {
		m_backend = backend;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		String validAgeRegex = "\\d{2}";
		
		frmCreateAFarm = new JFrame();
		frmCreateAFarm.setTitle("Begin Game");
		frmCreateAFarm.setBounds(100, 100, 640, 500);
		frmCreateAFarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 205, 124, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 32, 0, 0, 21, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmCreateAFarm.getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 1;
		gbc_verticalStrut_4.gridy = 0;
		frmCreateAFarm.getContentPane().add(verticalStrut_4, gbc_verticalStrut_4);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		frmCreateAFarm.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel lblTitle = new JLabel("Farm Simulator");
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		frmCreateAFarm.getContentPane().add(lblTitle, gbc_lblTitle);
		
		JLabel lblMakeFarmer = new JLabel("Make a Farmer:");
		GridBagConstraints gbc_lblMakeFarmer = new GridBagConstraints();
		gbc_lblMakeFarmer.insets = new Insets(0, 0, 5, 5);
		gbc_lblMakeFarmer.gridx = 1;
		gbc_lblMakeFarmer.gridy = 2;
		frmCreateAFarm.getContentPane().add(lblMakeFarmer, gbc_lblMakeFarmer);
		
		JLabel lblFarmBenefits = new JLabel("Farm Bonuses:");
		GridBagConstraints gbc_lblFarmBenefits = new GridBagConstraints();
		gbc_lblFarmBenefits.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmBenefits.gridx = 3;
		gbc_lblFarmBenefits.gridy = 2;
		frmCreateAFarm.getContentPane().add(lblFarmBenefits, gbc_lblFarmBenefits);
		
		JLabel lblFarmerName = new JLabel("Name: ");
		GridBagConstraints gbc_lblFarmerName = new GridBagConstraints();
		gbc_lblFarmerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmerName.gridx = 1;
		gbc_lblFarmerName.gridy = 3;
		frmCreateAFarm.getContentPane().add(lblFarmerName, gbc_lblFarmerName);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 3;
		frmCreateAFarm.getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		
		textFieldFarmerName = new JTextField();
		textFieldFarmerName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				verifyName(textFieldFarmerName);
				checkReadyToStart();
			}
		});
		textFieldFarmerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifyName(textFieldFarmerName);
				checkReadyToStart();
			}
		});
		GridBagConstraints gbc_textFieldFarmerName = new GridBagConstraints();
		gbc_textFieldFarmerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFarmerName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFarmerName.gridx = 2;
		gbc_textFieldFarmerName.gridy = 3;
		frmCreateAFarm.getContentPane().add(textFieldFarmerName, gbc_textFieldFarmerName);
		textFieldFarmerName.setColumns(10);
		
		JTextPane textPaneModifiers = new JTextPane();
		textPaneModifiers.setEditable(false);
		GridBagConstraints gbc_textPaneModifiers = new GridBagConstraints();
		gbc_textPaneModifiers.gridheight = 5;
		gbc_textPaneModifiers.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneModifiers.fill = GridBagConstraints.BOTH;
		gbc_textPaneModifiers.gridx = 3;
		gbc_textPaneModifiers.gridy = 3;
		frmCreateAFarm.getContentPane().add(textPaneModifiers, gbc_textPaneModifiers);
		
		JLabel lblFarmerAge = new JLabel("Age (10-99):");
		GridBagConstraints gbc_lblFarmerAge = new GridBagConstraints();
		gbc_lblFarmerAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmerAge.gridx = 1;
		gbc_lblFarmerAge.gridy = 4;
		frmCreateAFarm.getContentPane().add(lblFarmerAge, gbc_lblFarmerAge);
		
		textFieldFarmerAge = new JTextField();
		textFieldFarmerAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				verifyAge(textFieldFarmerAge);
				checkReadyToStart();
			}
		});
		textFieldFarmerAge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifyAge(textFieldFarmerAge);
				checkReadyToStart();
			}
		});
		GridBagConstraints gbc_textFieldFarmerAge = new GridBagConstraints();
		gbc_textFieldFarmerAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFarmerAge.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFarmerAge.gridx = 2;
		gbc_textFieldFarmerAge.gridy = 4;
		frmCreateAFarm.getContentPane().add(textFieldFarmerAge, gbc_textFieldFarmerAge);
		textFieldFarmerAge.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 5;
		frmCreateAFarm.getContentPane().add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lblSelectFarmType = new JLabel("Select a Farm: ");
		GridBagConstraints gbc_lblSelectFarmType = new GridBagConstraints();
		gbc_lblSelectFarmType.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectFarmType.gridx = 1;
		gbc_lblSelectFarmType.gridy = 6;
		frmCreateAFarm.getContentPane().add(lblSelectFarmType, gbc_lblSelectFarmType);
		
		comboBoxFarmType = new JComboBox();
		comboBoxFarmType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String output = "";
				double[] modifiers = Farm.getFarmModifierValues((FarmType)comboBoxFarmType.getSelectedItem());
				String[] names = Farm.getFarmModifierNames();
				if(modifiers.length == names.length)
				{
					for(int i = 0; i < modifiers.length; i++)
					{
						output += String.format("%s: %.2f\n", names[i],modifiers[i]);
					}
				}
				else
				{
					output = "Could not load modifiers.";
				}
				textPaneModifiers.setText(output);
			}
		});
		comboBoxFarmType.setModel(new DefaultComboBoxModel(FarmType.values()));
		GridBagConstraints gbc_comboBoxFarmType = new GridBagConstraints();
		gbc_comboBoxFarmType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFarmType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFarmType.gridx = 2;
		gbc_comboBoxFarmType.gridy = 6;
		frmCreateAFarm.getContentPane().add(comboBoxFarmType, gbc_comboBoxFarmType);
		
		JLabel lblFarmName = new JLabel("Name your farm: ");
		GridBagConstraints gbc_lblFarmName = new GridBagConstraints();
		gbc_lblFarmName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmName.gridx = 1;
		gbc_lblFarmName.gridy = 7;
		frmCreateAFarm.getContentPane().add(lblFarmName, gbc_lblFarmName);
		
		textFieldFarmName = new JTextField();
		textFieldFarmName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				verifyName(textFieldFarmName);
				checkReadyToStart();
			}
		});
		textFieldFarmerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifyName(textFieldFarmName);
				checkReadyToStart();
			}
		});
		GridBagConstraints gbc_textFieldFarmName = new GridBagConstraints();
		gbc_textFieldFarmName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFarmName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFarmName.gridx = 2;
		gbc_textFieldFarmName.gridy = 7;
		frmCreateAFarm.getContentPane().add(textFieldFarmName, gbc_textFieldFarmName);
		textFieldFarmName.setColumns(10);
		
		Component textSliderVStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_textSliderVStrut = new GridBagConstraints();
		gbc_textSliderVStrut.insets = new Insets(0, 0, 5, 5);
		gbc_textSliderVStrut.gridx = 2;
		gbc_textSliderVStrut.gridy = 8;
		frmCreateAFarm.getContentPane().add(textSliderVStrut, gbc_textSliderVStrut);
		
		JLabel lblDaysToPlay = new JLabel("How many days do you want to play?");
		GridBagConstraints gbc_lblDaysToPlay = new GridBagConstraints();
		gbc_lblDaysToPlay.gridwidth = 3;
		gbc_lblDaysToPlay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDaysToPlay.gridx = 1;
		gbc_lblDaysToPlay.gridy = 9;
		frmCreateAFarm.getContentPane().add(lblDaysToPlay, gbc_lblDaysToPlay);
		
		sliderNumDays = new JSlider();
		sliderNumDays.setMajorTickSpacing(1);
		sliderNumDays.setMinorTickSpacing(1);
		sliderNumDays.setPaintLabels(true);
		sliderNumDays.setPaintTicks(true);
		sliderNumDays.setSnapToTicks(true);
		sliderNumDays.setValue(7);
		sliderNumDays.setMinimum(4);
		sliderNumDays.setMaximum(10);
		GridBagConstraints gbc_sliderNumDays = new GridBagConstraints();
		gbc_sliderNumDays.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderNumDays.gridwidth = 3;
		gbc_sliderNumDays.insets = new Insets(0, 0, 5, 5);
		gbc_sliderNumDays.gridx = 1;
		gbc_sliderNumDays.gridy = 10;
		frmCreateAFarm.getContentPane().add(sliderNumDays, gbc_sliderNumDays);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_6 = new GridBagConstraints();
		gbc_verticalStrut_6.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_6.gridx = 0;
		gbc_verticalStrut_6.gridy = 11;
		frmCreateAFarm.getContentPane().add(verticalStrut_6, gbc_verticalStrut_6);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Double check that the user hasn't tried something sneaky
				//Like entering a valid string, then changing it to an invalid one and hitting start before focusLost or ActionEvent trigger
				if(checkReadyToStart())
				{
					startGame();
				}
			}
		});
		btnStartGame.setEnabled(false);
		GridBagConstraints gbc_btnStartGame = new GridBagConstraints();
		gbc_btnStartGame.fill = GridBagConstraints.BOTH;
		gbc_btnStartGame.gridwidth = 3;
		gbc_btnStartGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartGame.gridx = 1;
		gbc_btnStartGame.gridy = 12;
		frmCreateAFarm.getContentPane().add(btnStartGame, gbc_btnStartGame);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 4;
		gbc_horizontalStrut_1.gridy = 12;
		frmCreateAFarm.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_5 = new GridBagConstraints();
		gbc_verticalStrut_5.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_5.gridx = 0;
		gbc_verticalStrut_5.gridy = 13;
		frmCreateAFarm.getContentPane().add(verticalStrut_5, gbc_verticalStrut_5);
	}
	
	private boolean verifyName(JTextField str)
	{
		String validNameRegex = "[a-zA-Z][a-zA-Z\\d\\s.]+";
		if(!str.getText().matches(validNameRegex))
		{
			str.setText("");
			return false;
		}
		if(str.getText().length() > MAX_NAME_LENGTH)
		{
			str.setText(str.getText().substring(0, MAX_NAME_LENGTH));
		}
		return true;
	}
	
	private boolean verifyAge(JTextField age)
	{
		String validAgeRegex = "\\d{2}";
		if(!age.getText().matches(validAgeRegex))
		{
			age.setText("");
			return false;
		}
		return true;
	}
	
	/**
	 * Enable the start game button if all fields have valid inputs
	 */
	private boolean checkReadyToStart()
	{
		boolean ready = (verifyName(textFieldFarmerName) && verifyAge(textFieldFarmerAge) && verifyName(textFieldFarmName));
		btnStartGame.setEnabled(ready);
		return ready;

	}
	
	public void setVisible(boolean visible)
	{
		frmCreateAFarm.setVisible(visible);
	}
	
	public String getFarmerName()
	{
		return textFieldFarmerName.getText();
	}
	
	public int getFarmerAge()
	{
		return Integer.parseInt(textFieldFarmerAge.getText());
	}
	
	public FarmType getFarmType()
	{
		return (FarmType)comboBoxFarmType.getSelectedItem();
	}
	
	public String getFarmName()
	{
		return textFieldFarmName.getText();
	}
	
	public int getNumDays()
	{
		return sliderNumDays.getValue();
	}
	
	private void startGame()
	{
		m_backend.startGame();
	}

}
