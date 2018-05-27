import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TextEditorPane extends JTextArea {
    public TextEditorPane(int x, int y, String str) {
        JScrollPane scroll = new JScrollPane();
        setLayout(null);

        setSize(x,y);
        setMinimumSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y) );
        setMaximumSize(new Dimension(x,y));

        JLabel label = new JLabel(str);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        add(label);

        setBackground(Color.GREEN);
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
//                label.setSize(getWidth(), getHeight()/20 );
            }
        });

        scroll.add(this);
    }
}
