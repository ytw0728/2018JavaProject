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
		menu[0] = new JMenu("����");
		menu[1] = new JMenu("����");
		menu[2] = new JMenu("����");


		// menuItem ����, �ٸ�����, �ҷ����� , ���� , �ݱ� ����
		for (int i = 0; i < 3; i++) {
			menu[i].setFont(FontSwitch.init(FontSwitch.MENUBAR));
		}
		menus[0] = new JMenuItem("����");
		menus[1] = new JMenuItem("�ٸ��̸����� ����");
		menus[2] = new JMenuItem("�ҷ�����");
		menus[3] = new JMenuItem("���� �����");
		menus[4] = new JMenuItem("�ݱ�");
		menus[5] = new JMenuItem("�����ϱ�");
		menus[6] = new JMenuItem("�����ϱ�");

		// menuITem�� ����Ű ����
		menus[0].setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		menus[1].setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		menus[2].setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		menus[3].setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menus[4].setAccelerator(KeyStroke.getKeyStroke('W', Event.CTRL_MASK));
		menus[5].setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
		menus[6].setAccelerator(KeyStroke.getKeyStroke('M', Event.CTRL_MASK));

		// menu0�� �ҷ����� ���θ���� �ݱ� , menu1�� ���� �ٸ��̸����� ���� �����
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

		// ����
		menus[0].addActionListener(new ActionListener() {
			String userDir = System.getProperty("user.home");
			private JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("json ����","json"));
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

		// �ٸ��̸����� ����
		menus[1].addActionListener(new ActionListener() {
			String userDir = System.getProperty("user.home");
			private JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("json ����","json"));
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

		// �ҷ�����
		menus[2].addActionListener(new ActionListener() {
			String userDir = System.getProperty("user.home");
			private JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("json ����","json"));
				if( parent.isModified() ){
					String[] buttons = { "��", "�ƴϿ�" };
					int reply = JOptionPane.showOptionDialog(null, "���� ������ ������� �ʾҽ��ϴ�. ���� �ٸ� ������ �ҷ����ðڽ��ϱ�?" + "", "���â", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
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

		// ���θ����
		menus[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] buttons = { "��", "�ƴϿ�" };
				if( parent.isModified() ){
					int reply = JOptionPane.showOptionDialog(null, "���� ������ ������� �ʾҽ��ϴ�. ���� ���� ����ðڽ��ϱ�?" + "", "���â", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
					if (reply == JOptionPane.YES_OPTION) {
						parent.clearAll();
					}
				}
				else parent.clearAll();
			}
		});

		// �ݱ�
		menus[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parent.isModified()) {
					// display the JOptionPane showConfirmDialog
					String[] buttons = { "��", "�ƴϿ�" };
					int reply = JOptionPane.showOptionDialog(null, "�������� �ʾҽ��ϴ� ���� �����ðڽ��ϱ�?" + "", "���â",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
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

		menus[5].addActionListener(new ActionListener() { // ����
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
