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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import gameLogic.Animal;
import gameLogic.Crop;
import gameLogic.Farm;
import gameLogic.GameEnvironment;
import gameLogic.Item;
import gameLogic.Merchandise;
import gameLogic.MerchandiseWrapper;
import gameLogic.PossibleAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

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
	private JPanel buttonPanel;
	private JLabel lblMoney;
	private JLabel lblActionsRemaining;
	private JLabel lblDaysRemaining;
	private JLabel lblCrops;
	private JLabel lblAnimals;
	JTextArea textAreaSelectionDetails;

	private OptionalItemDialog askForItem;
	private PossibleAction chosenAction;
	//private Item optionalItem;
	private DefaultListModel<String> listModelSubOptions;
	private GameEnvironment m_game;
	

	/**
	 * Creates the main screen, the labels and buttons will be using the GameEnvironment to call the methods.
	 * For now it is using an Instance of a test FarmClass.
	 * 
	 * 
	 * @param newGame the Farm class created by the player.
	 */
	public MainScreen(GameEnvironment game) {
		
		//a default value for chosenAction
		chosenAction = PossibleAction.FEED_ANIMAL;
		//optionalItem = null;
		m_game = game;
		//askForItem = new OptionalItemDialog(m_game.getPlayerItems());
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
		textAreaSelectionDetails = new JTextArea();
		textAreaSelectionDetails.setWrapStyleWord(true);
		textAreaSelectionDetails.setLineWrap(true);
		DetailsScrollPane.setViewportView(textAreaSelectionDetails);
		textAreaSelectionDetails.setEditable(false);
		
			
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
		listModelSubOptions = new DefaultListModel<>();
		
		//ScrollPane for subOption
		JScrollPane subOpionScrollPane = new JScrollPane();
		subOpionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		subOpionScrollPane.setBounds(198, 119, 118, 210);
		frmSelectActivity.getContentPane().add(subOpionScrollPane);
		JList<String> subOption = new JList<String>(listModelSubOptions);
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
				textAreaSelectionDetails.setText(subOption.getSelectedValue().toString() );
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
				populateSubOptions(m_game.getPlayerMerchandise(), MerchandiseWrapper.CROP);
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
				
				//MainScreen.java
				//m_game.feedAnimal
				
				
				chosenAction = PossibleAction.FEED_ANIMAL;
				//show a list of animals player can feed
				populateSubOptions(m_game.getPlayerMerchandise(), MerchandiseWrapper.ANIMAL);
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
				populateSubOptions(m_game.getPlayerMerchandise(), MerchandiseWrapper.ANIMAL);
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
				populateSubOptions(m_game.getPlayerMerchandise(), MerchandiseWrapper.CROP);
				
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
				textAreaSelectionDetails.setText("Tend land is selected. This will increase crop and animal capacity on farm.");
				listModelSubOptions.removeAllElements();
				chosenAction = PossibleAction.TEND_LAND;
				m_game.takeAction(chosenAction, null);
			}
		});
		btnTendLand.setBounds(28, 211, 141, 21);
		frmSelectActivity.getContentPane().add(btnTendLand);
		
		
		
		JButton btnStore = new JButton("Store");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				m_game.displayGeneralStore();
			}
		});
		
		btnStore.setBounds(31, 41, 92, 39);
		frmSelectActivity.getContentPane().add(btnStore);
		
		
		
		
		JButton btnFarmStatus = new JButton("Farm Status");
		btnFarmStatus.addActionListener(new ActionListener() {
			//Player clicks button
			public void actionPerformed(ActionEvent e) {
				
				textAreaSelectionDetails.setText(m_game.getFarm().toString());
				updateStatusBar();
				
			}
		});
		btnFarmStatus.setBounds(199, 41, 103, 39);
		frmSelectActivity.getContentPane().add(btnFarmStatus);
		
		
		
		/* 
		 * Upper frame labels for game status begin below here
		 * */
		
		
		lblMoney = new JLabel();
		lblMoney.setText(String.format("Money: $ %d", m_game.getPlayerMoney()));		
		lblMoney.setBounds(167, 6, 92, 25);
		frmSelectActivity.getContentPane().add(lblMoney);
		
		
		lblActionsRemaining = new JLabel();
		lblActionsRemaining.setText(String.format("Actions remaining: %d", m_game.getRemainingActions()));
		lblActionsRemaining.setBounds(28, 84, 141, 25);
		frmSelectActivity.getContentPane().add(lblActionsRemaining);
		
		
		lblCrops = new JLabel();
		lblCrops.setText(String.format("Crops: %d", m_game.getPlayerCrops().size()));
		lblCrops.setBounds(289, 6, 80, 25);
		frmSelectActivity.getContentPane().add(lblCrops);
		
		
		
		lblAnimals = new JLabel("Animals");
		lblAnimals.setText(String.format("Animals: %d", m_game.getPlayerAnimals().size()));
		lblAnimals.setBounds(403, 6, 80, 25);
		frmSelectActivity.getContentPane().add(lblAnimals);
		
		
		
		lblDaysRemaining = new JLabel();
		lblDaysRemaining.setText(String.format("Days remaining %d", m_game.getRemainingDays()));
		lblDaysRemaining.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDaysRemaining.setBounds(28, 6, 129, 25);		
		frmSelectActivity.getContentPane().add(lblDaysRemaining);
		
		
		
		//move to next day button 
		JButton btnNextDay = new JButton("end day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//have to move this to game environ	
				
				m_game.endDay();			
			}
		});
		btnNextDay.setBounds(356, 349, 127, 21);
		frmSelectActivity.getContentPane().add(btnNextDay);
		
		
		
		
		// confirm button actionListener here. btnConfirm actionListener must be below subOption and lblActionsRemaining, otherwise it'll read the as uncreated.
		btnConfirm.addActionListener(new ActionListener() {
			
			/**
			 * confirmation button will use the selected animal/crop as arguments to feed into takeAction() in GameEnvironment class
			 * If tend crop is selected, OptionalItemDialog pops up to ask player if they want to use an item on the crop.
			 */
			public void actionPerformed(ActionEvent e) {
				
				m_game.takeAction(chosenAction, subOption.getSelectedValue() );
				
				
			}
		});
		
	}
	
	/**
	 * Populate a DefaultListModel<String> with names from a list of Merchandise.
	 * Can display multiple class types by formatting filter string as [classStringA]|[classStringB].
	 * @param merch list of merchandise to display by type
	 * @param merchType MerchandiseWrapper constant, either .ANIMAL, .CROP, or .ITEM based on which class you want to display
	 */
	public void populateSubOptions(MerchandiseWrapper merch, String merchType)
	{
		//TODO: Move to GameEnvironment.java
		ArrayList<Merchandise> compactedList = new ArrayList<Merchandise>();
		//Quick little regex to filter out merch we want
		String filterRegex = String.format("(%s)", merchType);
		//Worst n^2 - I'm sorry, I was in a rush
		for(int i = 0; i < merch.getMerchList().size(); i++)
		{
			
			//System.out.println(String.format("className=%s, regex=%s, filteredOut=%d", merch.getMerchList().get(i).getClass().getSimpleName(), filterRegex,(merch.getMerchList().get(i).getClass().getSimpleName().matches(filterRegex))));
			//If the class name of merch @ index i doesn't match the filter, skip this iteration.
			if(!(merch.getMerchList().get(i).getClass().getSimpleName().matches(filterRegex)))
			{
				continue;
			}
			boolean alreadyAdded = false;
			Merchandise m = merch.getMerchList().get(i);
			for(int j = compactedList.size() - 1; j >= 0; j--)
			{
				if(compactedList.get(j).getName() == m.getName())
				{
					alreadyAdded = true;
					break;
				}
			}
			if(!alreadyAdded)
			{
				compactedList.add(m);
			}
		}
		listModelSubOptions.removeAllElements();
		for(Merchandise m : compactedList)
		{
			listModelSubOptions.addElement(m.getName());
		}
	}
	
	/**
	 * Updates the 4 farm status labels across the top of the main screen
	 */
	public void updateStatusBar()
	{
		lblMoney.setText(String.format("Money: $%d", m_game.getPlayerMoney()));
		lblDaysRemaining.setText(String.format("Days Remaining: %d", m_game.getRemainingDays()));
		lblCrops.setText(String.format("Crops: %d", m_game.getPlayerCrops().size()));
		lblAnimals.setText(String.format("Animals: %d", m_game.getPlayerAnimals().size()));
		lblActionsRemaining.setText(String.format("Actions remaining: %d", m_game.getRemainingActions()));
		lblDaysRemaining.setText(String.format("Days remaining %d", m_game.getRemainingDays()));
		
		updateStatusBar();
		
	}
	
	/**
	 * Populate a DefaultListModel<String> with names from a list of Merchandise.
	 * Can display multiple class types by formatting filter string as [classStringA]|[classStringB].
	 * @param merch list of merchandise to display by type
	 * @param merchType MerchandiseWrapper constant, either .ANIMAL, .CROP, or .ITEM based on which class you want to display
	 */
	public void populateSubOptions(MerchandiseWrapper merch, String merchType)
	{
		//TODO: Move to GameEnvironment.java
		ArrayList<Merchandise> compactedList = new ArrayList<Merchandise>();
		//Quick little regex to filter out merch we want
		String filterRegex = String.format("(%s)", merchType);
		//Worst n^2 - I'm sorry, I was in a rush
		for(int i = 0; i < merch.getMerchList().size(); i++)
		{
			
			//System.out.println(String.format("className=%s, regex=%s, filteredOut=%d", merch.getMerchList().get(i).getClass().getSimpleName(), filterRegex,(merch.getMerchList().get(i).getClass().getSimpleName().matches(filterRegex))));
			//If the class name of merch @ index i doesn't match the filter, skip this iteration.
			if(!(merch.getMerchList().get(i).getClass().getSimpleName().matches(filterRegex)))
			{
				continue;
			}
			boolean alreadyAdded = false;
			Merchandise m = merch.getMerchList().get(i);
			for(int j = compactedList.size() - 1; j >= 0; j--)
			{
				if(compactedList.get(j).getName() == m.getName())
				{
					alreadyAdded = true;
					break;
				}
			}
			if(!alreadyAdded)
			{
				compactedList.add(m);
			}
		}
		listModelSubOptions.removeAllElements();
		for(Merchandise m : compactedList)
		{
			listModelSubOptions.addElement(m.getName());
		}
	}
	
	/**
	 * Updates the 4 farm status labels across the top of the main screen
	 */
	public void updateStatusBar()
	{
		lblMoney.setText(String.format("Money: $%d", m_game.getPlayerMoney()));
		lblDaysRemaining.setText(String.format("Days Remaining: %d", m_game.getRemainingDays()));
		lblCrops.setText(String.format("Crops: %d", m_game.getPlayerCrops().size()));
		lblAnimals.setText(String.format("Animals: %d", m_game.getPlayerAnimals().size()));
	}
	
	/**
	 * Sets the text in textAreaSelectionDetails
	 * @param text
	 */
	public void setDetailText(String text)
	{
		textAreaSelectionDetails.setText(text);
	}
	
	/**
	 * Sets the text in textAreaSelectionDetails. gameEnvironment uses it.
	 * @param text
	 */
	public void setDetailText(String text)
	{
		textAreaSelectionDetails.setText(text);
	}
	

}