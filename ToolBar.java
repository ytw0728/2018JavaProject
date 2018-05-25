import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class ToolBar {
	private JToolBar 	toolBar;
	
	public ToolBar() {
		this(MindMap.defaultSize[0], MindMap.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y) {
		toolBar = new JToolBar("ToolBar");
		
		toolBar.add(new JLabel("Tool Bar"));
//		toolBar.setSize(x,y);
		
		toolBar.setBackground(Color.WHITE);
		toolBar.setVisible(true);;
	}
	
	public JToolBar component() {
		return toolBar; 
	}
}


class TextEditorPane{
	private JTextArea panel;
	public TextEditorPane() {
		panel = new JTextArea();
	}
	public TextEditorPane(String str) {
		this((MindMap.defaultSize[0]/4), MindMap.defaultSize[1], str);
	}
	public TextEditorPane(int x, int y, String str) {
		panel = new JTextArea();
		panel.add(new JLabel(str));
//		panel.setSize(x,y);
		
		
		panel.setBackground(Color.GREEN);
		panel.setVisible(true);
	}
	
	public void setVisible(Boolean b) {
		panel.setVisible(b);
	}

	public JTextArea component() {
		return panel; 
	}
}

class MindMapPane{
	private JPanel panel;
	public MindMapPane() {
		panel = new JPanel();
	}
	public MindMapPane(String str) {
		this((MindMap.defaultSize[0]/2), MindMap.defaultSize[1], str);
	}
	public MindMapPane(int x, int y, String str) {
		panel = new JPanel();
		panel.add(new JLabel(str));
//		panel.setSize(x,y);
		panel.setBackground(Color.BLUE);
		panel.setVisible(true);
	}

	public void setVisible(Boolean b) {
		panel.setVisible(b);
	}

	public JPanel component() {
		return panel; 
	}
}

class AttributePane{
	private JPanel panel;
	public AttributePane() {
		panel  = new JPanel();
	}
	public AttributePane(String str) {
		this((MindMap.defaultSize[0]/4), MindMap.defaultSize[1], str);
	}
	public AttributePane(int x, int y, String str) {
		panel = new JPanel();
		panel.add(new JLabel(str));
//		panel.setSize(x,y);
		panel.setBackground(Color.RED);
		panel.setVisible(true);
	}

	public void setVisible(Boolean b) {
		panel.setVisible(b);
	}

	public JPanel component() {
		return panel; 
	}
}