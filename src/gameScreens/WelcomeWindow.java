package gameScreens;

import gameLogic.Farm;
import gameLogic.FarmType;

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

public class WelcomeWindow {

	private JFrame frmCreateAFarm;
	private JTextField farmerNameTextField;
	private JTextField farmerAgeTextField;
	private JTextField farmNameTextField;
	private JTextField farmModifiersTextBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeWindow window = new WelcomeWindow();
					window.frmCreateAFarm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCreateAFarm = new JFrame();
		frmCreateAFarm.setTitle("Begin Game");
		frmCreateAFarm.setBounds(100, 100, 400, 450);
		frmCreateAFarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 124, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 21, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblMakeFarmer = new JLabel("Make a Farmer:");
		GridBagConstraints gbc_lblMakeFarmer = new GridBagConstraints();
		gbc_lblMakeFarmer.insets = new Insets(0, 0, 5, 5);
		gbc_lblMakeFarmer.gridx = 1;
		gbc_lblMakeFarmer.gridy = 1;
		frmCreateAFarm.getContentPane().add(lblMakeFarmer, gbc_lblMakeFarmer);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 2;
		frmCreateAFarm.getContentPane().add(verticalStrut, gbc_verticalStrut);
		
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
		
		farmerNameTextField = new JTextField();
		GridBagConstraints gbc_farmerNameTextField = new GridBagConstraints();
		gbc_farmerNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_farmerNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_farmerNameTextField.gridx = 2;
		gbc_farmerNameTextField.gridy = 3;
		frmCreateAFarm.getContentPane().add(farmerNameTextField, gbc_farmerNameTextField);
		farmerNameTextField.setColumns(10);
		
		JLabel lblFarmerAge = new JLabel("Age:");
		GridBagConstraints gbc_lblFarmerAge = new GridBagConstraints();
		gbc_lblFarmerAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmerAge.gridx = 1;
		gbc_lblFarmerAge.gridy = 4;
		frmCreateAFarm.getContentPane().add(lblFarmerAge, gbc_lblFarmerAge);
		
		farmerAgeTextField = new JTextField();
		GridBagConstraints gbc_farmerAgeTextField = new GridBagConstraints();
		gbc_farmerAgeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_farmerAgeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_farmerAgeTextField.gridx = 2;
		gbc_farmerAgeTextField.gridy = 4;
		frmCreateAFarm.getContentPane().add(farmerAgeTextField, gbc_farmerAgeTextField);
		farmerAgeTextField.setColumns(10);
		
		JLabel lblFarmBenefits = new JLabel("Farm Bonuses:");
		GridBagConstraints gbc_lblFarmBenefits = new GridBagConstraints();
		gbc_lblFarmBenefits.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmBenefits.gridx = 3;
		gbc_lblFarmBenefits.gridy = 4;
		frmCreateAFarm.getContentPane().add(lblFarmBenefits, gbc_lblFarmBenefits);
		
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
		
		JComboBox farmTypeComboBox = new JComboBox();
		farmTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String output = "";
				double[] modifiers = Farm.getFarmModifierValues((FarmType)farmTypeComboBox.getSelectedItem());
				String[] names = Farm.getFarmModifierNames();
				if(modifiers.length == names.length)
				{
					for(int i = 0; i < modifiers.length; i++)
					{
						output.concat(String.format("%s: %f\n", names[i],modifiers[i]));
					}
				}
				else
				{
					output = "Could not load modifiers.";
				}
				
				farmModifiersTextBox.setText(output);
			}
		});
		farmTypeComboBox.setModel(new DefaultComboBoxModel(FarmType.values()));
		GridBagConstraints gbc_farmTypeComboBox = new GridBagConstraints();
		gbc_farmTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_farmTypeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_farmTypeComboBox.gridx = 2;
		gbc_farmTypeComboBox.gridy = 6;
		frmCreateAFarm.getContentPane().add(farmTypeComboBox, gbc_farmTypeComboBox);
		
		farmModifiersTextBox = new JTextField();
		farmModifiersTextBox.setEditable(false);
		GridBagConstraints gbc_farmModifiersTextBox = new GridBagConstraints();
		gbc_farmModifiersTextBox.gridheight = 4;
		gbc_farmModifiersTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_farmModifiersTextBox.fill = GridBagConstraints.BOTH;
		gbc_farmModifiersTextBox.gridx = 3;
		gbc_farmModifiersTextBox.gridy = 5;
		frmCreateAFarm.getContentPane().add(farmModifiersTextBox, gbc_farmModifiersTextBox);
		farmModifiersTextBox.setColumns(10);
		
		JLabel lblFarmName = new JLabel("Name your farm: ");
		GridBagConstraints gbc_lblFarmName = new GridBagConstraints();
		gbc_lblFarmName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarmName.gridx = 1;
		gbc_lblFarmName.gridy = 7;
		frmCreateAFarm.getContentPane().add(lblFarmName, gbc_lblFarmName);
		
		farmNameTextField = new JTextField();
		GridBagConstraints gbc_farmNameTextField = new GridBagConstraints();
		gbc_farmNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_farmNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_farmNameTextField.gridx = 2;
		gbc_farmNameTextField.gridy = 7;
		frmCreateAFarm.getContentPane().add(farmNameTextField, gbc_farmNameTextField);
		farmNameTextField.setColumns(10);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 1;
		gbc_verticalStrut_3.gridy = 8;
		frmCreateAFarm.getContentPane().add(verticalStrut_3, gbc_verticalStrut_3);
		
		JLabel lblDaysToPlay = new JLabel("How many days do you want to play?");
		GridBagConstraints gbc_lblDaysToPlay = new GridBagConstraints();
		gbc_lblDaysToPlay.gridwidth = 3;
		gbc_lblDaysToPlay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDaysToPlay.gridx = 1;
		gbc_lblDaysToPlay.gridy = 9;
		frmCreateAFarm.getContentPane().add(lblDaysToPlay, gbc_lblDaysToPlay);
		
		JSlider numDaysSlider = new JSlider();
		numDaysSlider.setMinorTickSpacing(1);
		numDaysSlider.setPaintLabels(true);
		numDaysSlider.setPaintTicks(true);
		numDaysSlider.setSnapToTicks(true);
		numDaysSlider.setValue(7);
		numDaysSlider.setMinimum(4);
		numDaysSlider.setMaximum(10);
		GridBagConstraints gbc_numDaysSlider = new GridBagConstraints();
		gbc_numDaysSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_numDaysSlider.gridwidth = 3;
		gbc_numDaysSlider.insets = new Insets(0, 0, 5, 5);
		gbc_numDaysSlider.gridx = 1;
		gbc_numDaysSlider.gridy = 10;
		frmCreateAFarm.getContentPane().add(numDaysSlider, gbc_numDaysSlider);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_6 = new GridBagConstraints();
		gbc_verticalStrut_6.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_6.gridx = 0;
		gbc_verticalStrut_6.gridy = 11;
		frmCreateAFarm.getContentPane().add(verticalStrut_6, gbc_verticalStrut_6);
		
		JButton btnNewButton = new JButton("Start Game");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 12;
		frmCreateAFarm.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
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

}
