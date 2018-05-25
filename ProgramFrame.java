import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class ProgramFrame {
	private JFrame frame;
	
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
		frame = new JFrame("∏∂¿ŒµÂ∏ ");
		MenuBar = new MenuBar(width, height/5);
		ToolBar = new ToolBar(width, height/5);
		
		int mainCompHeight = (height/5) * 2;
		
		TE = new TextEditorPane(width/4,mainCompHeight,"Text Editor Pane");
		MM = new MindMapPane(width/2,mainCompHeight,"Mind Map Pane");
		AB = new AttributePane(width/4,mainCompHeight,"Attribute Pane");
		
		
		frame.setLayout( new GridBagLayout() );

		frame.setSize(width,height);
		frame.setLocation(x, y);
		
		layout(MenuBar.component(),0,0,5,1);
		layout(ToolBar.component(),0,1,5,1);
		layout(TE.component(),0,2,1,5);
		layout(MM.component(),1,2,3,5);
		layout(AB.component(),4,2,1,5);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layout( JComponent comp, int x, int y, int width, int height) {
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = c.weighty= 1.0;
		c.fill = c.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
		
		frame.add(comp, c);
	}
	public JFrame component() {
		return frame;
	}
	
	public void setVisible(Boolean b) {
		frame.setVisible(b);
	}
}
