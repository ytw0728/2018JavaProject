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
		MenuBar = new MenuBar(width, height/10);
		ToolBar = new ToolBar(width, height/10);
		
		int mainCompHeight = (height/5) * 2;

		JSplitPane split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		TE = new TextEditorPane(width/4,mainCompHeight,"Text Editor Pane");
		MM = new MindMapPane(width/2,mainCompHeight,"Mind Map Pane");
		AB = new AttributePane(width/4,mainCompHeight,"Attribute Pane");

		split1.setDividerSize(0);
		split2.setDividerSize(0);
		int splitWidth = width / 10;
		split1.setDividerLocation(3 * splitWidth);
		split2.setDividerLocation(4 * splitWidth);

		split1.setResizeWeight(0.33);
		split2.setResizeWeight(0.66);

		split2.setLeftComponent(MM);
		split2.setRightComponent(AB);
		split1.setLeftComponent(TE);
		split1.setRightComponent(split2);


		setLayout( new GridBagLayout() );

		setSize(width,height);
		setLocation(x, y);
		
		layout(MenuBar,0,0,8,1,8,1);
		layout(ToolBar,0,1,8,1,8,1);
		layout(split1,0,2,8,24,8,24);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layout( JComponent comp, int x, int y, double width, double height, int gWidth, int gHeight) {
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = width;
		c.weighty = height;
		c.fill = c.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = gWidth;
		c.gridheight = gHeight;

		add(comp, c);
	}
}