package Main;

import Components.NodeLabel;
import DataStructures.CompactNode;
import DataStructures.JSONNode;
import Panels.AttributePane;
import Panels.MindMapPane;
import Panels.TextEditorPane;
import UtilBars.ButtonActions;
import UtilBars.MenuBar;
import UtilBars.ToolBar;
import com.google.gson.Gson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ProgramFrame extends JFrame{
	private String title = "∏∂¿ŒµÂ∏ ";

	private UtilBars.MenuBar MenuBar;
	private UtilBars.ToolBar ToolBar;
	private TextEditorPane TE;
	private MindMapPane 	MM;
	private AttributePane 	AB;
	private HashMap componentsMap = new HashMap();
	private JSplitPane split1, split2;
	private JSONNode rootHead = null;
	private String rootJson = "";
	private boolean modified = false;
	private boolean jsonNeedModifying = false;
	private Gson gson = new Gson();

	private String fileName = "";

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

		split1.setResizeWeight(0);
		split2.setResizeWeight(0);

		split2.setLeftComponent(MM);
		split2.setRightComponent(AB);
		split2.setBounds(0,0,splitWidth * 9 , CompHeight * 18);

		split1.setLeftComponent(TE);
		split1.setRightComponent(split2);

		split1.setBounds(0, CompHeight * 2, width, CompHeight * 18);
		split1.setBorder(new EmptyBorder(0,0,0,0));
		split2.setBorder(new EmptyBorder(0,0,0,0));

		layout(MenuBar);
		layout(ToolBar);
		layout(split1);

		setTitle("∏∂¿ŒµÂ∏ ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		componentsMap.put("PF", this);
		componentsMap.put("TE", TE);
		componentsMap.put("MM", MM);
		componentsMap.put("AB", AB);
		componentsMap.put("MenuBar", MenuBar);
		componentsMap.put("ToolBar", ToolBar);
		NodeLabel.setMM(MM);

		timer.start();
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				Dimension size = getContentPane().getSize();
				MenuBar.setSize(size.width, MenuBar.getHeight());
				ToolBar.setSize(size.width, ToolBar.getHeight());

				int splitWidth = size.width / 12;
				split1.setSize(size.width, size.height - MenuBar.getHeight() - ToolBar.getHeight());
				split2.setSize(size.width - 3*splitWidth, size.height - MenuBar.getHeight() - ToolBar.getHeight());

				TE.setSize(3*splitWidth, size.height );
				AB.setSize( 3*splitWidth, size.height);
				MM.setSize(6*splitWidth, size.height);
				split1.setDividerLocation(TE.getWidth());
				split2.setDividerLocation(MM.getWidth());
			}
		});
	}

	private void layout( JComponent comp) {
		add(comp);
	}
	public HashMap getComponentsMap(){return componentsMap;}
	public void setRootHead(JSONNode head){this.rootHead = head; timer.start(); }
	public void setRootJson(String json){
		if( jsonNeedModifying ){
			jsonNeedModifying = false;
			CompactNode ct = CompactNode.makeTree(rootHead); // for changing to json
			setRootJson(gson.toJson(ct));
		}
		this.rootJson = json;
	}
	public String getRootJson(){return this.rootJson;}
	public void setModified(boolean t){this.modified = (this.modified || t); this.jsonNeedModifying = (this.jsonNeedModifying || t);}
	public void setModifiedForce(boolean t){this.modified = t;}
	public boolean isModified(){return this.modified;}
	public void setFileName(String str){this.fileName =str; if( !str.equals("") ) title = ("∏∂¿ŒµÂ∏  - "+str); }
	public String getFileName(){return fileName;}
	public void setComponentsParent(){
		MenuBar.setParent(this);
		ToolBar.setParent(this);
		MM.setParent(this);
		AB.setParent(this);
		TE.setParent(this);
		ButtonActions.setParent(this);
	}
	public void clearAll(){
		timer.stop();
		fileName = "";
		title = "∏∂¿ŒµÂ∏ ";
		setTitle(title);
		this.rootHead = null;
		this.rootJson = "";
		this.modified = false;
		this.jsonNeedModifying = false;
		TE.clearText();
		MM.clear();
		AB.clear();
	}
	public void setWithJSON(String json){
		clearAll();
		if(json.equals("")){
			rootJson = json;
			timer.start();
			return;
		}
		JSONNode head = getInJSONNode(json);
		TE.setTextWithNode(head);
		MM.setHead(head);
		MM.printHead();
		rootJson = json;
		modified = jsonNeedModifying = false;
		timer.start();
	}
	public JSONNode getInJSONNode(String json){
		CompactNode node = gson.fromJson(json, CompactNode.class );
		JSONNode head = node.convertToJSONNode();
		return head;
	}
	Timer timer = new Timer(500, new ActionListener() {
		public void actionPerformed (ActionEvent e) {
			if( jsonNeedModifying ){
				jsonNeedModifying = false;
				CompactNode ct = CompactNode.makeTree(rootHead); // for changing to json
				setRootJson(gson.toJson(ct));
			}
			if( modified ) setTitle(title + "*");
			else setTitle(title);
		}
	});
}
