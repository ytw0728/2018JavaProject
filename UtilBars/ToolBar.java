package UtilBars;

import Main.Main;
import java.awt.*;
import javax.swing.*;

public class ToolBar extends JToolBar{

	public ToolBar() {
		this(0, Main.defaultSize[1]/5 , Main.defaultSize[0], Main.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);
		setBackground(new Color(40,44,52));
		setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));

		JMenu[] menus = new JMenu[5];
		menus[0] = new JMenu("저장");
		menus[1] = new JMenu("다른이름으로 저장");
		menus[2] = new JMenu("불러오기");
		menus[3] = new JMenu("새로 만들기");
		menus[4] = new JMenu("닫기");

		for( int i = 0; i < 5; i++ ){
			menus[i].setFont(new Font("맑은 고딕",  Font.PLAIN, 13));
			menus[i].setForeground(Color.WHITE);
			add(menus[i]);
		}

		setFloatable(false);
		setVisible(true);
	}
}




