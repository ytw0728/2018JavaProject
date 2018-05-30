package Panels;

import Components.BlueButton;
import Components.DarkLabel;
import Components.WhiteTextField;
import Configs.Colors.ColorSwitch;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AttributePane extends JPanel{
    JFrame parent = null;
    public AttributePane(int x, int y, String str) {
        setLayout(null);

        setBounds(0, 0, x, y);
        setMinimumSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y) );
        setMaximumSize(new Dimension(x,y));

        DarkLabel label = new DarkLabel(str);
        label.setSize(getWidth(), getHeight()/20);
        label.setBackground(ColorSwitch.init(ColorSwitch.DARK));
        label.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
        add(label);

        Attribute attr = new Attribute(0,y/20, x, y- y/20);
        JScrollPane scroll = new JScrollPane(attr);
        scroll.setBounds(0,y/20,x,y - 3*y/20);
        scroll.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.BRIGHT), 1));
        add(scroll);

        BlueButton applicationBtn = new BlueButton("∫Ø∞Ê«œ±‚");
        applicationBtn.setBounds(0,y - 2*y/20, x, 2*y/20);
        add(applicationBtn);


        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.BRIGHT), 1));
        setBackground(new Color(47,51,61));
        setVisible(true);


        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                label.setBounds(0,0,size.width, label.getHeight());
                scroll.setBounds(0,label.getHeight(), size.width, size.height - label.getHeight() * 3);
                applicationBtn.setBounds(0,size.height - label.getHeight()*2,size.width, 2*label.getHeight());
            }
        });
    }
    public void setParent(JFrame frame){ parent = frame;}
}

class Attribute extends JPanel{
    public Attribute(int x, int y, int width, int height) {
        setLayout(null);
        setBounds(x,y, width, height);
        setBackground(ColorSwitch.init(ColorSwitch.BRIGHT));
        setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.BRIGHT), 1));
        setForeground(Color.WHITE);

        // by KH
        /*
        DarkLabel label1 = new DarkLabel("TEXT : ");
        label1.setSize(getWidth() / 3, getHeight() / 20);
        label1.setLocation(30, 55);
        label1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 30));
        add(label1);
        */
/*
        WhiteTextField intext = new WhiteTextField();
        intext.setSize(getWidth() / 3, getHeight() / 20);
        intext.setLocation(160, 60);
        intext.setOpaque(true);
        add(intext);
        */

        DarkLabel[] labelArr = new DarkLabel[6];
        labelArr[0]= new DarkLabel("TEXT");
        labelArr[1] = new DarkLabel("X");
        labelArr[2] = new DarkLabel("Y");
        labelArr[3] = new DarkLabel("WIDTH");
        labelArr[4] = new DarkLabel("HEIGHT");
        labelArr[5] = new DarkLabel("COLOR");

        WhiteTextField[] whitefield = new WhiteTextField[6];
        for (int i = 0; i < 5; i++) {
            labelArr[i].setSize(getWidth() / 4, getHeight() / 20);
            labelArr[i].setBackground( ColorSwitch.init(ColorSwitch.BRIGHT));
            labelArr[i].setForeground(ColorSwitch.init(ColorSwitch.BRIGHTFONT));
            labelArr[i].setLocation(30,  50 + i * 90);
            add(labelArr[i]);

            whitefield[i] = new WhiteTextField(getHeight()/20);
            whitefield[i].setSize(getWidth() / 3, getHeight() / 20);
            whitefield[i].setBackground(ColorSwitch.init(ColorSwitch.BRIGHTTEST));
            whitefield[i].setForeground(ColorSwitch.init(ColorSwitch.BRIGHTFONT));
            whitefield[i].setCaretColor(ColorSwitch.init(ColorSwitch.BRIGHTFONT));
            whitefield[i].setLocation(160, 50 + 90 * i);
            add(whitefield[i]);
        }
        setVisible(true);
    }

}




