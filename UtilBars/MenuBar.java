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
		JMenuItem[] menus = new JMenuItem[7];
		JMenu[] menu = new JMenu[3];
		menu[0] = new JMenu("파일");
		menu[1] = new JMenu("저장");
		menu[2] = new JMenu("실행");


		// menuItem 저장, 다른저장, 불러오기 , 새만 , 닫기 생성
		for (int i = 0; i < 3; i++) {
			menu[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menus[0] = new JMenuItem("저장");
		menus[1] = new JMenuItem("다른이름으로 저장");
		menus[2] = new JMenuItem("불러오기");
		menus[3] = new JMenuItem("새로 만들기");
		menus[4] = new JMenuItem("닫기");
		menus[5] = new JMenuItem("적용하기");
		menus[6] = new JMenuItem("변경하기");

		// menuITem에 단축키 지정
		menus[0].setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		menus[1].setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		menus[2].setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		menus[3].setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menus[4].setAccelerator(KeyStroke.getKeyStroke('W', Event.CTRL_MASK));
		menus[5].setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
		menus[6].setAccelerator(KeyStroke.getKeyStroke('M', Event.CTRL_MASK));

		// menu0에 불러오기 새로만들기 닫기 , menu1에 저장 다른이름으로 저장 만들기
		for (int i = 0; i < 7; i++) {
			menus[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menu[0].add(menus[2]);
		menu[0].add(menus[3]);
		menu[0].add(menus[4]);
		menu[1].add(menus[0]);
		menu[1].add(menus[1]);
		menu[2].add(menus[5]);
		menu[2].add(menus[6]);

		add(menu[0]);
		add(menu[1]);
		add(menu[2]);
		setBackground(Color.WHITE);

		// 저장
		menus[0].addActionListener(new ActionListener() {
			String userDir = System.getProperty("user.home");
			private JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("json 파일","json"));
				String fileName = parent.getFileName();
				if( fileName.equals("")){
					int returnVal = fileChooser.showSaveDialog(parent);
					returnVal = JFileChooser.APPROVE_OPTION;
					File file = fileChooser.getSelectedFile();
					if( file == null ) return;
					try {
						FileWriter filewrite = new FileWriter(file,false);
						filewrite.write(parent.getRootJson());
						filewrite.flush();
						filewrite.close();
						parent.setFileName(file.toString());
						parent.setModifiedForce(false);
					} catch (IOException e1) {

					}
				}
				else if( parent.isModified() ){
					File file = new File(fileName);
					if( file == null ) return;
					try {
						FileWriter filewrite = new FileWriter(file, false);
						filewrite.write(parent.getRootJson());
						filewrite.flush();
						filewrite.close();
						parent.setModifiedForce(false);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, Common.FILESAVEERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// 다른이름으로 저장
		menus[1].addActionListener(new ActionListener() {
			String userDir = System.getProperty("user.home");
			private JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("json 파일","json"));
				int returnVal = fileChooser.showSaveDialog(parent);
				returnVal = JFileChooser.APPROVE_OPTION;
				File file = fileChooser.getSelectedFile();
				if( file == null ) return;
				try {
					FileWriter filewrite = new FileWriter(file,false);
					filewrite.write(parent.getRootJson());
					filewrite.flush();
					filewrite.close();
					parent.setFileName(file.toString());
					parent.setModifiedForce(false);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, Common.FILESAVEERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		// 불러오기
		menus[2].addActionListener(new ActionListener() {
			String userDir = System.getProperty("user.home");
			private JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("json 파일","json"));
				if( parent.isModified() ){
					String[] buttons = { "예", "아니오" };
					int reply = JOptionPane.showOptionDialog(null, "현재 파일이 저장되지 않았습니다. 정말 다른 파일을 불러오시겠습니까?" + "", "경고창", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
					if( reply != JOptionPane.YES_OPTION) return;
				}
				int returnVal = fileChooser.showOpenDialog(parent);
				returnVal = JFileChooser.APPROVE_OPTION;
				File file = fileChooser.getSelectedFile();
				FileReader reader = null;
				String json = "";
				if( file == null ) return;
				try {
					reader = new FileReader(file);
					char[] buf = new char[8192];
					while(true) {
						int ret = reader.read(buf);
						if( ret == -1) break;
						json += new String(buf);
						json = json.trim();
					}
					reader.close();
					parent.setWithJSON(json.trim());
					parent.setFileName(file.toString());
				} catch (FileNotFoundException e2) {
					JOptionPane.showMessageDialog(null, Common.FILENOTFOUNDERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, Common.FILEIOERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, Common.OPENERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 새로만들기
		menus[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] buttons = { "예", "아니오" };
				if( parent.isModified() ){
					int reply = JOptionPane.showOptionDialog(null, "현재 파일이 저장되지 않았습니다. 정말 새로 만드시겠습니까?" + "", "경고창", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
					if (reply == JOptionPane.YES_OPTION) {
						parent.clearAll();
					}
				}
				else parent.clearAll();
			}
		});

		// 닫기
		menus[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parent.isModified()) {
					// display the JOptionPane showConfirmDialog
					String[] buttons = { "예", "아니오" };
					int reply = JOptionPane.showOptionDialog(null, "저장하지 않았습니다 정말 나가시겠습니까?" + "", "경고창",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
					if (reply == JOptionPane.YES_OPTION) {
						setVisible(false);
						parent.dispose();
					}
				} else {
					setVisible(false);
					parent.dispose();
				}
			}
		});

		menus[5].addActionListener(new ActionListener() { // 적용
			@Override
			public void actionPerformed(ActionEvent e) {
				((TextEditorPane)parent.getComponentsMap().get("TE")).apply();
			}
		});
		menus[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((AttributePane)parent.getComponentsMap().get("AB")).apply();
			}
		});
	}
    public void setParent(ProgramFrame frame){ parent = frame;}
}
