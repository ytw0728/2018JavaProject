import java.awt.*;

import javax.swing.*;

public class ToolBar extends JToolBar{

	public ToolBar() {
		this(0,MindMap.defaultSize[1]/5 ,MindMap.defaultSize[0], MindMap.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);

		add(new JLabel("Tool Bar"));
		setBackground(Color.WHITE);
		setVisible(true);;
	}
}




