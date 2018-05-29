package UtilBars;

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
		setBackground(new Color(40,44,52));
		setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));

		JButton[] menus = new JButton[5];
		menus[0] = new JButton("����");
		menus[1] = new JButton("�ٸ��̸����� ����");
		menus[2] = new JButton("�ҷ�����");
		menus[3] = new JButton("���� �����");
		menus[4] = new JButton("�ݱ�");

		for( int i = 0; i < 5; i++ ){
			menus[i].setHorizontalAlignment(CENTER);
			menus[i].setFont(new Font("���� ���",  Font.PLAIN, 13));
			menus[i].setForeground(Color.WHITE);
			menus[i].setBackground(new Color(40,44,52) );
			menus[i].setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
			add(menus[i]);
		}

		setFloatable(false);
		setVisible(true);
	}
	public void setParent(ProgramFrame frame){ parent = frame;}
}




