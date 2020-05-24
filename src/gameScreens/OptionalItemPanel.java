package gameScreens;

import javax.swing.JPanel;

import gameLogic.Item;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Attempt to use a Jpanel to get  a popup box for optional items
 *
 *
 */
public class OptionalItemPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public OptionalItemPanel() {
		setLayout(null);
		
		JList<Item> listOptionalItems = new JList<Item>();
		listOptionalItems.setBounds(58, 37, 231, 220);
		add(listOptionalItems);
		
		JButton btnNotUse = new JButton("No");
		btnNotUse.setBounds(49, 286, 85, 21);
		add(btnNotUse);
		
		JButton btnUse = new JButton("use");
		btnUse.setBounds(193, 286, 85, 21);
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnUse);

	}
	
	
	public void changeList() {
		
//		li
		
	}
}
