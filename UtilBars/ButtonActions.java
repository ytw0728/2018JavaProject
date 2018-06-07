package UtilBars;

import Configs.Common;
import Main.ProgramFrame;
import Panels.AttributePane;
import Panels.TextEditorPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class ButtonActions {
    private static ProgramFrame parent = null;
    public static void setParent(ProgramFrame frame){
        parent = frame;
    }
    public static void save(){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
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
                JOptionPane.showMessageDialog(null, Common.FILESAVEERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, Common.FILESAVEERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveAs(){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
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
            JOptionPane.showMessageDialog(null, Common.FILESAVEERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void openFile(){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");

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
            JOptionPane.showMessageDialog(null, Common.FILENOTFOUNDERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, Common.FILEIOERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, Common.OPENERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void newFile(){
        String[] buttons = { "예", "아니오" };
        if( parent.isModified() ){
            int reply = JOptionPane.showOptionDialog(null, "현재 파일이 저장되지 않았습니다. 정말 새로 만드시겠습니까?" + "", "경고창", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
            if (reply == JOptionPane.YES_OPTION) {
                parent.clearAll();
            }
        }
        else parent.clearAll();
    }

    public static void closeProgram(){
        if (parent.isModified()) {
            // display the JOptionPane showConfirmDialog
            String[] buttons = { "예", "아니오" };
            int reply = JOptionPane.showOptionDialog(null, "저장하지 않았습니다 정말 나가시겠습니까?" + "", "경고창", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
            if (reply == JOptionPane.YES_OPTION) {
                parent.dispose();
            }
        }
        else{
            parent.dispose();
        }
    }


    public static void apply(){
        ((TextEditorPane)parent.getComponentsMap().get("TE")).apply();
    }

    public static void modify(){
        ((AttributePane)parent.getComponentsMap().get("AB")).apply();
    }

    public static void switchTheme(){
        parent.switchTheme();
    }

    public static void setChildrenNum(){
        String input = JOptionPane.showInputDialog("자식 노드 최대 개수");
        if( input == null || input.trim().equals("")) return;
        try {
            int num = Integer.parseInt(input.trim());
            if( num <= 0 ){
                JOptionPane.showMessageDialog(null, Common.NOTNUMBERERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            parent.setChildrenNum(num);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Common.NOTNUMBERERRORMSG(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
