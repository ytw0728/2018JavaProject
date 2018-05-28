import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AttributePane extends JPanel{
    public AttributePane(int x, int y, String str) {
        JScrollPane scroll = new JScrollPane();
        setLayout(null);

        setBounds(0, 0, x, y);
        setMinimumSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y) );
        setMaximumSize(new Dimension(x,y));

        JLabel label = new JLabel(str);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(getWidth(), getHeight()/20);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        add(label);

        setBackground(Color.RED);
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setSize(getWidth(), getHeight()/20 );
            }
        });

        scroll.add(this);
    }
}