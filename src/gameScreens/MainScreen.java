package gameScreens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.Item;
import gameLogic.Merchandise;
import gameLogic.PossibleAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;

/**
 * shows player the actions they can do in the game
 * Currently just testing the labels and buttons are calling methods correctly so it's calling an Instance of a FarnClass
 *
 * Remember - change JFrame frmSelectActivity to private and Farm to GameEnvironment
 * Search and replace Farm with GameEnvironment
 * 
 * last modified: 23-05-2020
 * created: 21-05-2020 
 * @author Kenn Leen Duenas Fulgencio
 *
 */
public class MainScreen {

	public JFrame frmSelectActivity;
	private Farm gameEnvironment;
	private OptionalItemDialog askForItem;
	private PossibleAction chosenAction;
	
	

	/*
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmSelectActivity.setVisible(true);
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
		
		//a default value for chosenAction
		chosenAction = PossibleAction.FEED_ANIMAL;
		gameEnvironment = newGame;
		initialize();
		frmSelectActivity.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelectActivity = new JFrame();
		frmSelectActivity.setTitle("main");
		//frame.setBounds(100, 100, 530, 570);
		frmSelectActivity.setBounds(100, 100, 531, 428);
		frmSelectActivity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectActivity.getContentPane().setLayout(null);
		
		
		
		
		/*
		 * Components to aid in player selecting one crop or animal are below here.
		 */
		
		JScrollPane DetailsScrollPane = new JScrollPane();
		DetailsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DetailsScrollPane.setBounds(342, 74, 141, 255);
		frmSelectActivity.getContentPane().add(DetailsScrollPane);
		
		//text panes
		JEditorPane dtrpnSelectiondetails = new JEditorPane();
		DetailsScrollPane.setViewportView(dtrpnSelectiondetails);
		dtrpnSelectiondetails.setEditable(false);
		
			
		
		/* The confirm button appears when an animal or crop from subOption is selected
		 * It will become invisible again when it has been clicked. 
		*/
		JButton btnConfirm = new JButton("Confirm");
		
		btnConfirm.addMouseListener(new MouseAdapter() {
		});
		btnConfirm.setBounds(113, 349, 127, 21);
		btnConfirm.setVisible(false);
		frmSelectActivity.getContentPane().add(btnConfirm);
		
		
		
		// The list model that will be used by the subOption JList.
		DefaultListModel<Merchandise> ownedMerchListModel = new DefaultListModel<>();
		
		//ScrollPane for subOption
		JScrollPane subOpionScrollPane = new JScrollPane();
		subOpionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		subOpionScrollPane.setBounds(198, 119, 118, 210);
		frmSelectActivity.getContentPane().add(subOpionScrollPane);
		JList<Merchandise> subOption = new JList<Merchandise>(ownedMerchListModel);
		subOption.setBorder(null);
		subOpionScrollPane.setViewportView(subOption);
		subOption.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		subOption.addMouseListener(new MouseAdapter() {		
			/**
			 * If subOption is clicked, confirm button becomes visible and selection detail appears.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {				
				btnConfirm.setVisible(true);
				//shows items info in selection Details
				dtrpnSelectiondetails.setText(subOption.getSelectedValue().toString() );				
			}
		});
		
		
		
		/*buttons on the left of the screen are below */
		
		
		/* 
		 * tending crops interactions
		 * */
		JButton btnTendCrop = new JButton("Tend crops");
		btnTendCrop.addActionListener(new ActionListener() {
			
			/**
			 * Tend crop button will set selectedAction to a value that will call tendCrop() from Farm class
			 * 
			 */
			public void actionPerformed(ActionEvent e) {				
				
				chosenAction = PossibleAction.TEND_CROP;
				//shows crops player owns into Jlist.				
				ownedMerchListModel.removeAllElements();
				for (Merchandise crop : gameEnvironment.getCrops() ) {
					
					ownedMerchListModel.addElement(crop);
				}		
				
			}		
		});	
		btnTendCrop.setBounds(28, 119, 141, 21);
		//btnTendCrop.
		frmSelectActivity.getContentPane().add(btnTendCrop);
		
		
		/* 
		 * feeding animal interactions
		 * */
		JButton btnFeedAnimal = new JButton("Feed animals");
		
		
		btnFeedAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chosenAction = PossibleAction.FEED_ANIMAL;
				//show a list of animals player can feed			
				ownedMerchListModel.removeAllElements();
				
				for (Animal animal : gameEnvironment.getAnimals() ) {
					
					ownedMerchListModel.addElement(animal);
				}
			}
		});
		btnFeedAnimal.setBounds(28, 169, 141, 21);
		frmSelectActivity.getContentPane().add(btnFeedAnimal);
		
		
		/* 
		 * Playing with animal interactions
		 * */
		JButton btnPlayAnimal = new JButton("Play with animals");
		btnPlayAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chosenAction = PossibleAction.PLAY_WITH_ANIMAL;
				ownedMerchListModel.removeAllElements();
				for (Animal animal : gameEnvironment.getAnimals() ) {
					
					ownedMerchListModel.addElement(animal);
				}
			}
		});
		btnPlayAnimal.setBounds(30, 304, 139, 25);
		frmSelectActivity.getContentPane().add(btnPlayAnimal);
		
		
		
		/* 
		 * Harvesting crops interactions
		 * */
		JButton btnHarvestCrop = new JButton("Harvest crops");
		btnHarvestCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosenAction = PossibleAction.HARVEST_CROP;
				//shows crops player owns.
				
				ownedMerchListModel.removeAllElements();
				for (Crop crop : gameEnvironment.getCrops() ) {
					
					ownedMerchListModel.addElement(crop);
				}	
				
			}
		});
		btnHarvestCrop.setBounds(28, 256, 141, 21);
		frmSelectActivity.getContentPane().add(btnHarvestCrop);
		
		
		
		/* 
		 * Tending land interactions
		 * */
		JButton btnTendLand = new JButton("Tend land");
		btnTendLand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chosenAction = PossibleAction.TEND_LAND;
				//empties anything in selection details
				dtrpnSelectiondetails.setText("Tend land is selected. This will increase crop and animal capacity on farm.");
				ownedMerchListModel.removeAllElements();
				System.out.println("Tend land clicked");			
			}
		});
		btnTendLand.setBounds(28, 211, 141, 21);
		frmSelectActivity.getContentPane().add(btnTendLand);
		
		
		
		JButton btnStore = new JButton("Store");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Do something to go to store");
			}
		});
		
		btnStore.setBounds(31, 41, 92, 39);
		frmSelectActivity.getContentPane().add(btnStore);
		
		
		
		
		JButton btnFarmStatus = new JButton("Farm Status");
		btnFarmStatus.addActionListener(new ActionListener() {
			//Player clicks button
			public void actionPerformed(ActionEvent e) {
				
				dtrpnSelectiondetails.setText(gameEnvironment.toString());
				
			}
		});
		btnFarmStatus.setBounds(199, 41, 103, 39);
		frmSelectActivity.getContentPane().add(btnFarmStatus);
		
		
		
		/* 
		 * Upper frame labels for game status begin below here
		 * */
		
		
		JLabel lblMoney = new JLabel();
		lblMoney.setText(String.format("Money: $ %d", gameEnvironment.getMoney() ) );		
		lblMoney.setBounds(167, 6, 92, 25);
		frmSelectActivity.getContentPane().add(lblMoney);
		
		
		JLabel lblActionsRemaining = new JLabel();
		lblActionsRemaining.setText(String.format("Actions remaining: %d", gameEnvironment.getRemainingActions() ) );
		lblActionsRemaining.setBounds(28, 84, 141, 25);
		frmSelectActivity.getContentPane().add(lblActionsRemaining);
		
		
		JLabel lblCrops = new JLabel();
		lblCrops.setText(String.format("Crops: %d", gameEnvironment.getCrops().size() ));
		lblCrops.setBounds(289, 6, 80, 25);
		frmSelectActivity.getContentPane().add(lblCrops);
		
		
		
		JLabel lblAnimals = new JLabel("Animals");
		lblAnimals.setText(String.format("Animals: %d", gameEnvironment.getAnimals().size() ) );
		lblAnimals.setBounds(403, 6, 80, 25);
		frmSelectActivity.getContentPane().add(lblAnimals);
		
		
		
		JLabel lblDaysRemaining = new JLabel();
		lblDaysRemaining.setText(String.format("Days remaining %d", gameEnvironment.getRemainingDays()) );
		lblDaysRemaining.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDaysRemaining.setBounds(28, 6, 129, 25);		
		frmSelectActivity.getContentPane().add(lblDaysRemaining);
		
		
		
		// confirm button actionListener here. btnConfirm actionListener must be below subOption and lblActionsRemaining, otherwise it'll read the as uncreated.
		btnConfirm.addActionListener(new ActionListener() {
			
			/**
			 * confirmation button will use the selected animal/crop as arguments to feed into takeAction() in GameEnvironment class
			 * If tend crop is selected, OptionalItemDialog pops up to ask player if they want to use an item on the crop.
			 */
			public void actionPerformed(ActionEvent e) {								
				
				if(chosenAction.equals(PossibleAction.TEND_CROP)) {
					
				askForItem = new OptionalItemDialog(gameEnvironment.getItems() ); 
				
				askForItem.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);				
				askForItem.setVisible(true);
				
				}
								
				//clears the selectionDetails text. 
				dtrpnSelectiondetails.setText("");
				System.out.println("Button pressed! Currently selected crop/animal is:");
				
				//this gets value from subOption Jlist.
				System.out.println(subOption.getSelectedValue());
				
				
				//calls the method of the button and uses the selected animal as the required argument
				// ActionCount = gameEnvironment.takeAction(chosenAction, itemSelection));
				
				//lblActionsRemaining.setText(String.format("Actions remaining: %d", gameEnvironment.getRemainingActions() ) );
				
				System.out.println("Chosen action is " + chosenAction);
				
				ownedMerchListModel.removeAllElements();
				//turn invisible again
				btnConfirm.setVisible(false);
				
			}
		});
		

	}
}
