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
		menus[7] = new JButton("테마변경");
		menus[7].setToolTipText("테마를 변경합니다.");
		menus[8] = new JButton("자식노드개수변경");
		menus[8].setToolTipText("각 노드 당 연결 가능한 자식노드의 최대 개수를 변경합니다.");


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



		// 저장
		menus[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.save(); }
		});
		// 다른이름으로 저장
		menus[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ ButtonActions.saveAs(); }
		});

		// 불러오기
		menus[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ ButtonActions.openFile(); }
		});

		// 새로만들기
		menus[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ ButtonActions.newFile(); }
		});

		// 닫기
		menus[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ButtonActions.closeProgram(); }
		});

		menus[5].addActionListener(new ActionListener() { // 적용
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




