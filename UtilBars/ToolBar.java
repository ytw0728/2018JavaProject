package UtilBars;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;
import Main.Main;
import Main.ProgramFrame;
import java.awt.*;
import javax.swing.*;

public class ToolBar extends JToolBar{
	ProgramFrame parent = null;
	public ToolBar() {
		this(0, Main.defaultSize[1]/5 , Main.defaultSize[0], Main.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y, int width, int height) {
		setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
		setBounds(x,y, width, height);
		setBackground(ColorSwitch.init(ColorSwitch.DARK));
		setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));

		JButton[] menus = new JButton[5];
		menus[0] = new JButton("저장");
		menus[1] = new JButton("다른이름으로 저장");
		menus[2] = new JButton("불러오기");
		menus[3] = new JButton("새로 만들기");
		menus[4] = new JButton("닫기");

		for( int i = 0; i < 5; i++ ){
			menus[i].setHorizontalAlignment(CENTER);
			menus[i].setFont( FontSwitch.init(FontSwitch.MENUBAR));
			menus[i].setForeground(Color.WHITE);
			menus[i].setBackground(ColorSwitch.init(ColorSwitch.DARK));
			menus[i].setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
			add(menus[i]);
		}

		setFloatable(false);
		setVisible(true);
	}
	public void setParent(ProgramFrame frame){ parent = frame;}
}




