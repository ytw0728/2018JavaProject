package Panels;

import Components.BlueButton;
import Components.DarkLabel;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;
import DataStructures.CompactNode;
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
    private TextEditor editor;
    public TextEditorPane(int x, int y, String str) {
        setLayout(null);
        setSize(x,y);

        DarkLabel label = new DarkLabel(str);
        label.setBounds(0,0,x, y/20);
        label.setBackground(ColorSwitch.init(ColorSwitch.DARK));
        label.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
        add(label);

        editor = new TextEditor(0,0, x, y);
        JScrollPane scroll = new JScrollPane(editor);
        scroll.setBounds(0,y/20,x,y - 3*y/20);
        scroll.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(13, Integer.MAX_VALUE));
        add(scroll);

        BlueButton applicationBtn = new BlueButton("적용하기");
        applicationBtn.setBounds(0,y - 2*y/20, x, 2*y/20);
        add(applicationBtn);

        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.BRIGHT), 1));
        setBackground(ColorSwitch.init(ColorSwitch.DARK));
        setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                label.setBounds(0,0,size.width, label.getHeight());
                scroll.setBounds(0,label.getHeight(), size.width, size.height - label.getHeight() * 3);
                editor.setBounds(0,0, size.width, size.height - label.getHeight() * 3);
                applicationBtn.setBounds(0,size.height - label.getHeight()*2,size.width, 2*label.getHeight());
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
                            if( head == null ) return;
                            break;
                        }
                    }
                }
                Gson gson = new Gson();
                MindMapPane m = (MindMapPane)parent.getComponentsMap().get("MM");
                m.setHead(head);
                m.printHead();

                CompactNode ct = CompactNode.makeTree(head); // for changing to json
                parent.setRootJson(gson.toJson(ct));
            }
        });
    }
    public void setParent(ProgramFrame frame){ parent = frame;}
    public void clearText(){ editor.clearText(); }
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
    public void clearText(){
        setText(null);
    }
}