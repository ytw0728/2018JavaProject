package UtilBars;

import Configs.Common;
import Main.ProgramFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ButtonActions {
    private static ProgramFrame parent = null;
    public static void setParent(ProgramFrame frame){
        parent = frame;
    }
    public static void save(){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
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
                JOptionPane.showMessageDialog(null, Common.FILESAVEERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void saveAs(){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
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

    public static void openFile(){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");

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
            JOptionPane.showMessageDialog(null, Common.OPENERRORMSG, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void newFile(){
        String[] buttons = { "��", "�ƴϿ�" };
        if( parent.isModified() ){
            int reply = JOptionPane.showOptionDialog(null, "���� ������ ������� �ʾҽ��ϴ�. ���� ���� ����ðڽ��ϱ�?" + "", "���â", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
            if (reply == JOptionPane.YES_OPTION) {
                parent.clearAll();
            }
        }
        else parent.clearAll();
    }

    public static void closeProgram(){
        if (parent.isModified()) {
            // display the JOptionPane showConfirmDialog
            String[] buttons = { "��", "�ƴϿ�" };
            int reply = JOptionPane.showOptionDialog(null, "�������� �ʾҽ��ϴ� ���� �����ðڽ��ϱ�?" + "", "���â",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
            if (reply == JOptionPane.YES_OPTION) {
                parent.dispose();
            }
        }
        else{
            parent.dispose();
        }
    }
}
