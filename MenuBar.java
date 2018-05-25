import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class MenuBar {
	JMenuBar menu;
	
	public MenuBar() {
		this(MindMap.defaultSize[0], MindMap.defaultSize[1]/3);
	}
	public MenuBar(int x, int y) {
		menu = new JMenuBar();
		menu.setSize(x,y);
		menu.add(new JLabel("Menu Bar"));
		
		menu.setBackground(Color.BLACK);
	}
	
	public JMenuBar component() {
		return menu;
	}
}
