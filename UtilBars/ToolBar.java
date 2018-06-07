package UtilBars;

import Configs.Colors.ColorSwitch;
import Configs.Common;
import Configs.Fonts.FontSwitch;
import Main.Main;
import Main.ProgramFrame;
import Panels.AttributePane;
import Panels.TextEditorPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToolBar extends JToolBar{
	ProgramFrame parent = null;
	private JButton[] menus;
	public ToolBar() {
		this(0, Main.defaultSize[1]/5 , Main.defaultSize[0], Main.defaultSize[1]/5 );
	}
	public ToolBar(int x, int y, int width, int height) {
		setBounds(x,y, width, height);
		setBackground(ColorSwitch.init(ColorSwitch.DARK));
		setBorder(new EmptyBorder(0,0,0,0));

		menus = new JButton[9];
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
		menus[7] = new JButton("�׸�����");
		menus[7].setToolTipText("�׸��� �����մϴ�.");
		menus[8] = new JButton("�ڽĳ�尳������");
		menus[8].setToolTipText("�� ��� �� ���� ������ �ڽĳ���� �ִ� ������ �����մϴ�.");


		for( int i = 0; i < menus.length; i++ ){
			menus[i].setHorizontalAlignment(CENTER);
			menus[i].setFont( FontSwitch.init(FontSwitch.MENUBAR));
			menus[i].setForeground(ColorSwitch.init(ColorSwitch.DEFAUlT));
			menus[i].setBackground(ColorSwitch.init(ColorSwitch.DARK));
			menus[i].setBorder(new EmptyBorder(getHeight()/2,10,getHeight()/2,10));
			add(menus[i]);
		}

		setFloatable(false);
		setVisible(true);



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
			public void actionPerformed(ActionEvent e) { ButtonActions.switchTheme(); }
		});

		menus[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.setChildrenNum();}
		});
	}
	public void setParent(ProgramFrame frame){ parent = frame;}

	public void recolor(){
		setBackground(ColorSwitch.init(ColorSwitch.DARK));

		for( int i = 0; i < menus.length; i++ ){
			menus[i].setForeground(ColorSwitch.init(ColorSwitch.DEFAUlT));
			menus[i].setBackground(ColorSwitch.init(ColorSwitch.DARK));
		}
	}
}




