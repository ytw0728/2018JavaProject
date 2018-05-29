package Panels;

import Components.BlueButton;
import Components.DarkLabel;

import DataStructures.JSONNode;

import Main.ProgramFrame;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TextEditorPane extends JPanel {
    ProgramFrame parent = null;
    public TextEditorPane(int x, int y, String str) {
        setLayout(null);
        setSize(x,y);

        DarkLabel label = new DarkLabel(str);
        label.setBounds(0,0,x, y/20);
        label.setBackground(new Color(40,44,52));
        label.setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        add(label);

        TextEditor editor = new TextEditor(0,0, x, y - y/20);
        editor.setFont(new Font("", Font.PLAIN, 28));
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(editor);
        scroll.setBounds(0,y/20,x,y - 4*y/20);
        scroll.setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        add(scroll);

        BlueButton applicationBtn = new BlueButton("Àû¿ëÇÏ±â");
        applicationBtn.setBounds(0,y - 3*y/20, x, 2*y/20);
        applicationBtn.setBackground(new Color(100,148,237));
        applicationBtn.setBorder(BorderFactory.createLineBorder (new Color(100,148,237), 1));
        add(applicationBtn);

        setBorder(BorderFactory.createLineBorder (new Color(47,51,61), 1));
        setBackground(new Color(40,44,52));
        setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setSize(getWidth(), getHeight()/20 );
            }
        });
        applicationBtn.addActionListener(new ActionListener() {
            private String[] data;
            JSONNode head = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                data = editor.getText().split("\n");
                head = null;
                for( String line : data){
                    String[] subData = line.split("\\t");
                    for( int i = 0 ; i< subData.length;i++ ){
                        if(!subData[i].equals("")){
                            head = JSONNode.addJSON(head,subData[i],i);
                            break;
                        }
                    }
                }
                Gson gson = new Gson();
//                System.out.println(gson.toJson(head));
                MindMapPane m = (MindMapPane)parent.getComponentsMap().get("MM");
                m.setHead(head);
                m.printHead();
            }

        });
    }
    public void setParent(ProgramFrame frame){ parent = frame;}
}

class TextEditor extends JTextArea {
    public TextEditor(int x, int y, int width, int height) {
        setBounds(x,y, width, height);
        setBackground(new Color(47,51,61));
        setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 10));
        setTabSize(3);

        setCaretColor(Color.WHITE);

        setBorder(BorderFactory.createLineBorder (new Color(47,51,61), 1));
        setForeground(Color.WHITE);
        setVisible(true);
    }
}