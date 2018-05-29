package Panels;

import Components.BlueButton;
import Components.DarkLabel;

import javax.swing.*;
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
        label.setBackground(new Color(40,44,52));
        label.setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        add(label);

        Attribute attr = new Attribute(0,y/20, x, y- y/20);
        JScrollPane scroll = new JScrollPane(attr);
        scroll.setBounds(0,y/20,x,y - 4*y/20);
        scroll.setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        add(scroll);

        BlueButton applicationBtn = new BlueButton("∫Ø∞Ê«œ±‚");
        applicationBtn.setBounds(0,y - 3*y/20, x, 2*y/20);
        applicationBtn.setBackground(new Color(100,148,237));
        applicationBtn.setBorder(BorderFactory.createLineBorder (new Color(100,148,237), 1));
        add(applicationBtn);


        setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        setBackground(new Color(47,51,61));
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setSize(getWidth(), getHeight()/20 );
            }
        });
    }
    public void setParent(JFrame frame){ parent = frame;}
}

class Attribute extends JPanel{
    public Attribute(int x, int y, int width, int height) {
        setBounds(x,y, width, height);
        setBackground(new Color(47,51,61));
        setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
        setBorder(BorderFactory.createLineBorder (new Color(47,51,61), 1));
        setForeground(Color.WHITE);
        setVisible(true);
    }
}