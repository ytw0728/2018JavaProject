package UtilBars;

import Configs.Common;
import Configs.Fonts.FontSwitch;
import Main.Main;
import Main.ProgramFrame;
import Panels.AttributePane;
import Panels.TextEditorPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JMenuBar {
    ProgramFrame parent = null;
	public MenuBar() { 	this(0,0, Main.defaultSize[0], Main.defaultSize[1]/3); }
	public MenuBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);
		JMenuItem[] menus = new JMenuItem[9];
		JMenu[] menu = new JMenu[3];
		menu[0] = new JMenu("����");
		menu[1] = new JMenu("����");
		menu[2] = new JMenu("����");


		// menuItem ����, �ٸ�����, �ҷ����� , ���� , �ݱ� ����
		for (int i = 0; i < menu.length; i++) {
			menu[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menus[0] = new JMenuItem("����");
		menus[1] = new JMenuItem("�ٸ��̸����� ����");
		menus[2] = new JMenuItem("�ҷ�����");
		menus[3] = new JMenuItem("���� �����");
		menus[4] = new JMenuItem("�ݱ�");
		menus[5] = new JMenuItem("�����ϱ�");
		menus[6] = new JMenuItem("�����ϱ�");
		menus[7] = new JMenuItem("�׸�����");
		menus[8] = new JMenuItem("�ڽĳ�尳������");

		// menuITem�� ����Ű ����
		menus[0].setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		menus[1].setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		menus[2].setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		menus[3].setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menus[4].setAccelerator(KeyStroke.getKeyStroke('W', Event.CTRL_MASK));
		menus[5].setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
		menus[6].setAccelerator(KeyStroke.getKeyStroke('M', Event.CTRL_MASK));
		menus[7].setAccelerator(KeyStroke.getKeyStroke('T', Event.CTRL_MASK));
		menus[8].setAccelerator(KeyStroke.getKeyStroke('I', Event.CTRL_MASK));

		for (int i = 0; i < menus.length; i++) {
			menus[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menu[0].add(menus[2]);
		menu[0].add(menus[3]);
		menu[0].add(menus[4]);
		menu[1].add(menus[0]);
		menu[1].add(menus[1]);
		menu[2].add(menus[5]);
		menu[2].add(menus[6]);
		menu[2].add(menus[7]);
		menu[2].add(menus[8]);

		add(menu[0]);
		add(menu[1]);
		add(menu[2]);
		setBackground(Color.WHITE);

		// ����
		menus[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.save(); }
		});
		// �ٸ��̸����� ����
		menus[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ ButtonActions.saveAs(); }
		});
		// �ҷ�����
		menus[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ ButtonActions.openFile(); }
		});
		// ���θ����
		menus[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ ButtonActions.newFile(); }
		});
		// �ݱ�
		menus[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.closeProgram(); }
		});

		menus[5].addActionListener(new ActionListener() { // ����
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.apply(); }
		});
		menus[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.modify(); }
		});
		menus[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.switchTheme();}
		});
		menus[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.setChildrenNum();}
		});
	}
    public void setParent(ProgramFrame frame){ parent = frame;}
}
