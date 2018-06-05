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




