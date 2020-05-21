package gameScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class MainScreen {

	private JFrame mainFrame;
	private final JTextPane textPane = new JTextPane();

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
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
		
		
		//The buttons
		JButton btnTendCrop = new JButton("Tend crops");
		btnTendCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	
		
		btnTendCrop.setBounds(28, 119, 141, 21);
		mainFrame.getContentPane().add(btnTendCrop);
		
		JButton btnFeedAnimal = new JButton("Feed animals");
		btnFeedAnimal.setBounds(28, 169, 141, 21);
		mainFrame.getContentPane().add(btnFeedAnimal);
		
		JButton btnPlayAnimal = new JButton("Play with animals");
		btnPlayAnimal.setBounds(30, 304, 139, 25);
		mainFrame.getContentPane().add(btnPlayAnimal);
		
		JButton btnHarvestCrop = new JButton("Harvest crops");
		btnHarvestCrop.setBounds(28, 256, 141, 21);
		mainFrame.getContentPane().add(btnHarvestCrop);
		
		JButton btnTendLand = new JButton("Tend land");
		btnTendLand.setBounds(28, 211, 141, 21);
		mainFrame.getContentPane().add(btnTendLand);
		
		JLabel btnActionsRemaining = new JLabel("Actions remaining: ");
		btnActionsRemaining.setBounds(28, 84, 141, 25);
		mainFrame.getContentPane().add(btnActionsRemaining);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(113, 349, 127, 21);
		mainFrame.getContentPane().add(btnConfirm);
		
		JList subOption = new JList();
		subOption.setBounds(198, 119, 118, 210);
		mainFrame.getContentPane().add(subOption);
		
		JButton btnStore = new JButton("Store");
		btnStore.setBounds(31, 41, 92, 39);
		mainFrame.getContentPane().add(btnStore);
		
		JButton btnFarmStatus = new JButton("Farm Status");
		btnFarmStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFarmStatus.setBounds(199, 41, 103, 39);
		mainFrame.getContentPane().add(btnFarmStatus);
		
		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setBounds(167, 6, 92, 25);
		mainFrame.getContentPane().add(lblMoney);
		
		JLabel lblCrops = new JLabel("Crops:");
		lblCrops.setBounds(289, 6, 80, 25);
		mainFrame.getContentPane().add(lblCrops);
		
		JLabel lblAnimals = new JLabel("Animals:");
		lblAnimals.setBounds(403, 6, 80, 25);
		mainFrame.getContentPane().add(lblAnimals);
		
		JTextArea textAreaInfo = new JTextArea();
		textAreaInfo.setBounds(115, 4, -86, 22);
		
		mainFrame.getContentPane().add(textAreaInfo);
		
		JLabel lblDaysRemaining = new JLabel("Days remaining:");
		lblDaysRemaining.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDaysRemaining.setBounds(28, 6, 129, 25);
		mainFrame.getContentPane().add(lblDaysRemaining);
		textPane.setBounds(356, 84, 127, 245);
		mainFrame.getContentPane().add(textPane);
	}
}
