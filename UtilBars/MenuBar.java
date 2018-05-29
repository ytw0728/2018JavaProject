package UtilBars;

import Configs.Fonts.FontSwitch;
import Main.Main;
import Main.ProgramFrame;
import java.awt.*;
import javax.swing.*;

public class MenuBar extends JMenuBar {
    ProgramFrame parent = null;
	public MenuBar() { 	this(0,0, Main.defaultSize[0], Main.defaultSize[1]/3); }
	public MenuBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);
		JMenuItem[] menus = new JMenuItem[5];
		JMenu[] menu = new JMenu[3];
		menu[0] = new JMenu("파일");
		menu[1] = new JMenu("저장");
		for( int i = 0 ; i < 2; i ++){
			menu[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menus[0] = new JMenuItem("저장");
		menus[1] = new JMenuItem("다른이름으로 저장");
		menus[2] = new JMenuItem("불러오기");
		menus[3] = new JMenuItem("새로 만들기");
		menus[4] = new JMenuItem("닫기");

		for( int i = 0; i < 5; i++ ){
			menus[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menu[0].add(menus[2]);
		menu[0].add(menus[3]);
		menu[0].add(menus[4]);
		menu[1].add(menus[0]);
		menu[1].add(menus[1]);

		add(menu[0]);
		add(menu[1]);
		setBackground(Color.WHITE);
	}
    public void setParent(ProgramFrame frame){ parent = frame;}
}
