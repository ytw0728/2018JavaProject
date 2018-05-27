import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

	
	public MenuBar() {
		this(MindMap.defaultSize[0], MindMap.defaultSize[1]/3);
	}
	public MenuBar(int x, int y) {

		setSize(x,y);

		JLabel label = new JLabel("Menu Bar");
		add(label);
		setBackground(Color.BLACK);
	}
}
