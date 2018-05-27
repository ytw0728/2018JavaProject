import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MindMapPane extends JPanel {
    public MindMapPane(int x, int y, String str) {
        JScrollPane scroll = new JScrollPane();
        setLayout(null);

        setSize(x,y);
        setMinimumSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y) );
        setMaximumSize(new Dimension(x,y));

        JLabel label = new JLabel(str);

        int labelWidth = 130;
        label.setLocation(getWidth()/2 - labelWidth/2,0);
        label.setSize(labelWidth, y/10);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        add(label);

        setBackground(Color.BLUE);
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
//                label.setSize(getWidth(), getHeight()/20 );
                label.setLocation(getWidth()/2,0);
            }
        });

        scroll.add(this);
    }
}
