package UtilBars;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;
import Main.Main;
import Main.ProgramFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ToolBar extends JToolBar{
	ProgramFrame parent = null;
	public ToolBar() {
		this(0, Main.defaultSize[1]/5 , Main.defaultSize[0], Main.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);
		setBackground(ColorSwitch.init(ColorSwitch.DARK));
		setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));

		JButton[] menus = new JButton[7];
		menus[0] = new JButton("저장");
		menus[0].setToolTipText("파일을 저장합니다.");
		menus[1] = new JButton("다른이름으로 저장");
		menus[1].setToolTipText("파일을 다른이름으로 저장합니다.");
		menus[2] = new JButton("불러오기");
		menus[2].setToolTipText("편집할 파일을 불러옵니다.");
		menus[3] = new JButton("새로 만들기");
		menus[3].setToolTipText("편집할 새 파일을 만듭니다.");
		menus[4] = new JButton("닫기");
		menus[4].setToolTipText("현재 파일을 닫습니다.");
		menus[5] = new JButton("적용");
		menus[5].setToolTipText("마인드맵 양식을 적용하여 시각화합니다.");
		menus[6] = new JButton("변경");
		menus[6].setToolTipText("속성창의 변경사항을 적용합니다.");


		for( int i = 0; i < 7; i++ ){
			menus[i].setHorizontalAlignment(CENTER);
			menus[i].setFont( FontSwitch.init(FontSwitch.MENUBAR));
			menus[i].setForeground(Color.WHITE);
			menus[i].setBackground(ColorSwitch.init(ColorSwitch.DARK));
			menus[i].setBorder(new EmptyBorder(getHeight()/2,10,getHeight()/2,10));
			add(menus[i]);
		}

		setFloatable(false);
		setVisible(true);
	}
	public void setParent(ProgramFrame frame){ parent = frame;}
}




