import java.awt.*;

import javax.swing.*;

public class ProgramFrame extends JFrame{
	private MenuBar MenuBar;
	private ToolBar ToolBar;
	private TextEditorPane 	TE;
	private MindMapPane 	MM;
	private AttributePane 	AB;

	public ProgramFrame(){
		this(MindMap.defaultSize[0], MindMap.defaultSize[1],MindMap.defaultSize[2], MindMap.defaultSize[3]);
	}
	public ProgramFrame(int width, int height) {
		this(width, height, MindMap.defaultSize[2], MindMap.defaultSize[3]);
	}
	public ProgramFrame(int width, int height, int x, int y) {
		int CompHeight = (height/20);

		MenuBar = new MenuBar(0,0,width, CompHeight);
		ToolBar = new ToolBar(0,CompHeight,width, CompHeight);

		JSplitPane split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		TE = new TextEditorPane(width/4,CompHeight*18,"Text Editor Pane");
		MM = new MindMapPane(width/2,CompHeight*18,"Mind Map Pane");
		AB = new AttributePane(width/4,CompHeight*18,"Attribute Pane");

		split1.setDividerSize(0);
		split2.setDividerSize(0);

		int splitWidth = width / 12;
		split1.setDividerLocation(3 * splitWidth);
		split2.setDividerLocation(6 * splitWidth);

		split1.setResizeWeight(0.33);
		split2.setResizeWeight(0.66);

		split2.setLeftComponent(MM);
		split2.setRightComponent(AB);
		split1.setLeftComponent(TE);
		split1.setRightComponent(split2);

		split1.setBounds(0,CompHeight*2, width, CompHeight*18);

		setLayout(null);

		setSize(width,height);
		setLocation(x, y);
		
		layout(MenuBar);
		layout(ToolBar);
		layout(split1);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layout( JComponent comp) {
		add(comp);
	}
}