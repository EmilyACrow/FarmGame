package gameScreens;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class CompatibleItemsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CompatibleItemsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);

	}

}
