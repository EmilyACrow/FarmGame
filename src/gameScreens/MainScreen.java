package gameScreens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.Merchandise;

/**
 * Interface to show player the actions they can do in the game
 * Currently just testing the labels and buttons are calling methods correctly so it's calling an Instance of a FarnClass
 *
 * Remember - change JFrame mainFrame to private and Farm to GameEnvironment
 * Search and replace Farm with GameEnvironment
 * 
 * last modified: 22-05-2020
 * created: 21-05-2020 
 * @author Kenn Leen Duenas Fulgencio
 *
 */
public class MainScreen {

	public JFrame mainFrame;
	private Farm gameEnvironment;

	/*
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 */
	

	/**
	 * Creates the main screen, the labels and buttons will be using the GameEnvironment to call the methods.
	 * For now it is using an Instance of a test FarmClass.
	 * 
	 * 
	 * @param newGame the Farm class created by the player.
	 */
	public MainScreen(Farm newGame) {
		
		gameEnvironment = newGame;
		initialize();
		mainFrame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("main");
		mainFrame.setBounds(100, 100, 531, 428);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		
		//text panes
		JEditorPane dtrpnSelectiondetails = new JEditorPane();
		dtrpnSelectiondetails.setEditable(false);
		dtrpnSelectiondetails.setBounds(342, 74, 141, 255);
		mainFrame.getContentPane().add(dtrpnSelectiondetails);
		

		
		
		
		// The list model that will be used by subOption
		DefaultListModel<Merchandise> ownedMerchListModel = new DefaultListModel<>();
		JList<Merchandise> subOption = new JList<Merchandise>(ownedMerchListModel);
		subOption.setBounds(198, 119, 118, 210);
		subOption.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainFrame.getContentPane().add(subOption);
		
		
		
		/*buttons begin here */
		
		
		/* 
		 * tending crops interactions
		 * */
		JButton btnTendCrop = new JButton("Tend crops");
		btnTendCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//shows crops player owns.
				
				ownedMerchListModel.removeAllElements();
				for (Crop crop : gameEnvironment.getCrops() ) {
					
					ownedMerchListModel.addElement(crop);
				}		
				
			}		
		});	
		btnTendCrop.setBounds(28, 119, 141, 21);
		mainFrame.getContentPane().add(btnTendCrop);
		
		
		/* 
		 * feeding animal interactions
		 * */
		JButton btnFeedAnimal = new JButton("Feed animals");
		btnFeedAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//show a list of animals player can feed
				
				ownedMerchListModel.removeAllElements();
				for (Animal animal : gameEnvironment.getAnimals() ) {
					
					ownedMerchListModel.addElement(animal);
				}
			}
		});
		btnFeedAnimal.setBounds(28, 169, 141, 21);
		mainFrame.getContentPane().add(btnFeedAnimal);
		
		
		/* 
		 * Playing with animal interactions
		 * */
		JButton btnPlayAnimal = new JButton("Play with animals");
		btnPlayAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ownedMerchListModel.removeAllElements();
				for (Animal animal : gameEnvironment.getAnimals() ) {
					
					ownedMerchListModel.addElement(animal);
				}
			}
		});
		btnPlayAnimal.setBounds(30, 304, 139, 25);
		mainFrame.getContentPane().add(btnPlayAnimal);
		
		
		
		/* 
		 * Harvesting crops interactions
		 * */
		JButton btnHarvestCrop = new JButton("Harvest crops");
		btnHarvestCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//shows crops player owns.
				
				ownedMerchListModel.removeAllElements();
				for (Crop crop : gameEnvironment.getCrops() ) {
					
					ownedMerchListModel.addElement(crop);
				}	
				
			}
		});
		btnHarvestCrop.setBounds(28, 256, 141, 21);
		mainFrame.getContentPane().add(btnHarvestCrop);
		
		
		
		/* 
		 * Tending land interactions
		 * */
		JButton btnTendLand = new JButton("Tend land");
		btnTendLand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Tend land clicked");
				//make a call to tendLand()
			}
		});
		btnTendLand.setBounds(28, 211, 141, 21);
		mainFrame.getContentPane().add(btnTendLand);
		
		
		
		// Should only be visible if something from subOption is selected
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(113, 349, 127, 21);
		//btnConfirm.setVisible(false);
		mainFrame.getContentPane().add(btnConfirm);
		
		
		
		
		JButton btnStore = new JButton("Store");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Do something to go to store");
			}
		});
		
		btnStore.setBounds(31, 41, 92, 39);
		mainFrame.getContentPane().add(btnStore);
		
		
		
		
		JButton btnFarmStatus = new JButton("Farm Status");
		btnFarmStatus.addActionListener(new ActionListener() {
			//Player clicks button
			public void actionPerformed(ActionEvent e) {
				
				dtrpnSelectiondetails.setText(gameEnvironment.toString());
				
			}
		});
		btnFarmStatus.setBounds(199, 41, 103, 39);
		mainFrame.getContentPane().add(btnFarmStatus);
		
		
		
		/* 
		 * Text labels begin below here
		 * */
		JLabel lblMoney = new JLabel();
		lblMoney.setText(String.format("Money: $ %d", gameEnvironment.getMoney() ) );		
		lblMoney.setBounds(167, 6, 92, 25);
		mainFrame.getContentPane().add(lblMoney);
		
		
		JLabel btnActionsRemaining = new JLabel();
		btnActionsRemaining.setText(String.format("Actions remaining: %d", gameEnvironment.getRemainingActions() ) );
		btnActionsRemaining.setBounds(28, 84, 141, 25);
		mainFrame.getContentPane().add(btnActionsRemaining);
		
		
		JLabel lblCrops = new JLabel();
		lblCrops.setText(String.format("Crops: %d", gameEnvironment.getCrops().size() ));
		lblCrops.setBounds(289, 6, 80, 25);
		mainFrame.getContentPane().add(lblCrops);
		
		
		
		JLabel lblAnimals = new JLabel("Animals");
		lblAnimals.setText(String.format("Animals: %d", gameEnvironment.getAnimals().size() ) );
		lblAnimals.setBounds(403, 6, 80, 25);
		mainFrame.getContentPane().add(lblAnimals);
		
		
		
		JLabel lblDaysRemaining = new JLabel();
		lblDaysRemaining.setText(String.format("Days remaining %d", gameEnvironment.getRemainingDays()) );
		lblDaysRemaining.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDaysRemaining.setBounds(28, 6, 129, 25);		
		mainFrame.getContentPane().add(lblDaysRemaining);
		

	}
}
