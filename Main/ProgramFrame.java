package Main;

import Configs.Colors.ColorSwitch;
import Panels.AttributePane;
import Panels.MindMapPane;
import Panels.TextEditorPane;
import UtilBars.MenuBar;
import UtilBars.ToolBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class ProgramFrame extends JFrame{
	private UtilBars.MenuBar MenuBar;
	private UtilBars.ToolBar ToolBar;
	private TextEditorPane TE;
	private MindMapPane 	MM;
	private AttributePane 	AB;
	private HashMap componentsMap = new HashMap();
	private JSplitPane split1, split2;

	public ProgramFrame(){
		this(Main.defaultSize[0], Main.defaultSize[1],Main.defaultSize[2], Main.defaultSize[3]);
	}
	public ProgramFrame(int width, int height) {
		this(width, height, Main.defaultSize[2], Main.defaultSize[3]);
	}
	public ProgramFrame(int width, int height, int x, int y) {
		setLayout(null);
		this.pack();
		Insets insets = getInsets();
		this.setBounds(0,0, insets.left + width + insets.right, insets.top + height + insets.bottom);
		this.setLocationRelativeTo(null);

		int CompHeight = (height / 22);

		MenuBar = new MenuBar(0, 0, width, CompHeight);
		ToolBar = new ToolBar(0, CompHeight, width, CompHeight);

		split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		TE = new TextEditorPane(width / 4, CompHeight * 20, "Text Editor Pane");
		MM = new MindMapPane(width / 2, CompHeight * 20, "Mind Map Pane");
		AB = new AttributePane(width / 4, CompHeight * 20, "Panels.Attribute Pane");

		split1.setDividerSize(0);
		split2.setDividerSize(0);

		int splitWidth = width / 12;
		split1.setDividerLocation(3 * splitWidth);
		split2.setDividerLocation(6 * splitWidth);

		split1.setResizeWeight(0.1);
		split2.setResizeWeight(0);

		split2.setLeftComponent(MM);
		split2.setRightComponent(AB);
		split1.setLeftComponent(TE);

		split1.setRightComponent(split2);

		split1.setBounds(0, CompHeight * 2, width, CompHeight * 18);
		split1.setBorder(BorderFactory.createLineBorder(ColorSwitch.init(ColorSwitch.DARK), 1));
		split2.setBorder(BorderFactory.createLineBorder(ColorSwitch.init(ColorSwitch.DARK), 1));

		layout(MenuBar);
		layout(ToolBar);
		layout(split1);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		componentsMap.put("PF", this);
		componentsMap.put("TE", TE);
		componentsMap.put("MM", MM);
		componentsMap.put("AB", AB);
		componentsMap.put("MenuBar", MenuBar);
		componentsMap.put("ToolBar", ToolBar);

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension size = getContentPane().getSize();
				MenuBar.setSize(size.width, MenuBar.getHeight());
				ToolBar.setSize(size.width, ToolBar.getHeight());
				split1.setSize(size.width, size.height - MenuBar.getHeight() - ToolBar.getHeight());
				split2.setSize(size.width - TE.getWidth(), size.height);
				TE.setSize(3*size.width/12, size.height );
				AB.setSize( 3*size.width/12, size.height);
				MM.setSize(split2.getWidth() - AB.getWidth(), size.height);
				split1.setDividerLocation(TE.getWidth());
				split2.setDividerLocation(split2.getWidth() - AB.getWidth());
			}
		});
	}
	private void layout( JComponent comp) {
		add(comp);
	}
	public HashMap getComponentsMap(){return componentsMap;}
	public void setComponentsParent(){
		MenuBar.setParent(this);
		ToolBar.setParent(this);
		MM.setParent(this);
		AB.setParent(this);
		TE.setParent(this);
	}
}
