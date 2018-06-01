package Components;
import Configs.Fonts.FontSwitch;
import DataStructures.JSONNode;
import Panels.MindMapPane;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class NodeLabel extends JLabel{
    private static MindMapPane MM;
    public static void setMM(MindMapPane panel){
        MM = panel;
    }
    private Shape shape;
    private int arc;
    private JSONNode parent;
    public NodeLabel (String str, JSONNode parent, int size) {
        super(str);
        this.arc = size;
        this.parent = parent;
        setHorizontalAlignment(CENTER);
        setFont(FontSwitch.init(FontSwitch.BOLD));
        setOpaque(false); // As suggested by @AVD in comment.
        setVisible(true);
        setHorizontalAlignment(SwingConstants.CENTER);

        addMouseListener(new NodeMouseListener());
        addMouseMotionListener(new NodeMouseMotionListener());
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0,  0, getWidth()-1, getHeight()-1, arc, arc);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, arc, arc);
        }
        return shape.contains(x, y);
    }

    private class NodeMouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e){ MM.dispatchEvent(e); }
    }
    private class NodeMouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
        @Override
        public void mouseExited(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
        @Override
        public void mousePressed(MouseEvent e) {
            MM.setTarget(parent);
            MM.dispatchEvent(e);
        }
        @Override
        public void mouseReleased(MouseEvent e){ MM.dispatchEvent(e);}
    }
}