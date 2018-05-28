package Panels;

import Components.DarkLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MindMapPane extends JPanel {
    public MindMapPane(int x, int y, String str) {
        JScrollPane scroll = new JScrollPane();
        setLayout(null);

        setBounds(0,0, x, y);
        setMinimumSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y));
        setMaximumSize(new Dimension(x,y));

        DarkLabel label = new DarkLabel(str);
        label.setBounds(0,0,150, y/20);
        label.setBackground(new Color(33,37,43) );
        label.setBorder(BorderFactory.createLineBorder (new Color(33,37,43) , 1));
        add(label);

        MindMap mindMap = new MindMap(0, y/20,x,y-y/20);
        add(mindMap);

        setBackground(new Color(40,44,52));
        setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setBounds(0,0,150, y/20);
            }
        });

        scroll.add(this);
    }
}

class MindMap extends JPanel{
    public MindMap(int x, int y, int width, int height) {
        setBounds(x,y, width, height);
        setBackground(new Color(33,37,43) );
        setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        setBorder(BorderFactory.createLineBorder (new Color(33,37,43) , 1));
        setForeground(Color.WHITE);
        setVisible(true);
    }
}