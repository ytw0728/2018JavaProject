package Components;

import Configs.Fonts.FontSwitch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class WhiteTextField extends JTextField {
    private Shape shape;
    private int arc;
    public WhiteTextField (int size) {
        super(size);
        this.arc = size;
        setHorizontalAlignment(CENTER);
        setFont(FontSwitch.init(FontSwitch.LABELTEXT));
        setOpaque(false); // As suggested by @AVD in comment.
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
}