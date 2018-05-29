package UtilBars;

import Main.Main;
import Main.ProgramFrame;
import java.awt.*;
import javax.swing.*;

public class MenuBar extends JMenuBar {
    ProgramFrame parent = null;
	public MenuBar() { 	this(0,0, Main.defaultSize[0], Main.defaultSize[1]/3); }
	public MenuBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);
		JMenu[] menus = new JMenu[5];
		menus[0] = new JMenu("����");
		menus[1] = new JMenu("�ٸ��̸����� ����");
		menus[2] = new JMenu("�ҷ�����");
		menus[3] = new JMenu("���� �����");
		menus[4] = new JMenu("�ݱ�");

		for( int i = 0; i < 5; i++ ){
			menus[i].setFont(new Font("���� ���",  Font.PLAIN, 13));
			add(menus[i]);
		}
		setBackground(Color.WHITE);
	}
    public void setParent(ProgramFrame frame){ parent = frame;}
}
