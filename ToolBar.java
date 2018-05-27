import java.awt.*;

import javax.swing.*;

public class ToolBar extends JToolBar{

	public ToolBar() {
		this(MindMap.defaultSize[0], MindMap.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y) {

		setSize(x,y);

		add(new JLabel("Tool Bar"));
		setBackground(Color.WHITE);
		setVisible(true);;
	}
}




