package Panels;

import Components.BlueButton;
import Components.DarkLabel;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;
import DataStructures.JSONNode;

import Main.ProgramFrame;
import com.google.gson.Gson;

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
        label.setBackground(ColorSwitch.init(ColorSwitch.DARK));
        label.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
        add(label);

        TextEditor editor = new TextEditor(0,0, x, y - y/20);
        JScrollPane scroll = new JScrollPane(editor);
        scroll.setBounds(0,y/20,x,y - 4*y/20);
        scroll.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
        add(scroll);

        BlueButton applicationBtn = new BlueButton("적용하기");
        applicationBtn.setBounds(0,y - 3*y/20, x, 2*y/20);
        applicationBtn.setBackground(ColorSwitch.init(ColorSwitch.KEYCOLOR));
        applicationBtn.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.KEYCOLOR), 1));
        add(applicationBtn);

        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.BRIGHT), 1));
        setBackground(ColorSwitch.init(ColorSwitch.DARK));
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
        setBackground( ColorSwitch.init(ColorSwitch.BRIGHT) );
        setTabSize(3);
        setFont(FontSwitch.init(FontSwitch.EDITOR));
        setLineWrap(true);
        setWrapStyleWord(true);
        setCaretColor(Color.WHITE);

        setBorder(BorderFactory.createLineBorder ( ColorSwitch.init(ColorSwitch.BRIGHT), 1));
        setForeground(Color.WHITE);
        setVisible(true);
    }
}