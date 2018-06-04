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
		menus[0] = new JButton("����");
		menus[0].setToolTipText("������ �����մϴ�.");
		menus[1] = new JButton("�ٸ��̸����� ����");
		menus[1].setToolTipText("������ �ٸ��̸����� �����մϴ�.");
		menus[2] = new JButton("�ҷ�����");
		menus[2].setToolTipText("������ ������ �ҷ��ɴϴ�.");
		menus[3] = new JButton("���� �����");
		menus[3].setToolTipText("������ �� ������ ����ϴ�.");
		menus[4] = new JButton("�ݱ�");
		menus[4].setToolTipText("���� ������ �ݽ��ϴ�.");
		menus[5] = new JButton("����");
		menus[5].setToolTipText("���ε�� ����� �����Ͽ� �ð�ȭ�մϴ�.");
		menus[6] = new JButton("����");
		menus[6].setToolTipText("�Ӽ�â�� ��������� �����մϴ�.");


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




