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




