import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TextEditorPane extends JPanel{
    public TextEditorPane(int x, int y, String str) {
        setLayout(null);
        setSize(x,y);

        JLabel label = new JLabel(str);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0,0,x, y/20);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        add(label);

        TextEditor editor = new TextEditor(0,0, x, y - y/20);
        editor.setFont(new Font("", Font.PLAIN, 28));
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(editor);
        scroll.setBounds(0,y/20,x,y - 3*y/20);
        add(scroll);

        JButton applicationBtn = new JButton("적용하기");
        add(applicationBtn);
        setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setSize(getWidth(), getHeight()/20 );
            }
        });
    }
}

class TextEditor extends JTextArea {
    public TextEditor(int x, int y, int width, int height) {
        setBounds(x,y, width, height);
        setBackground(Color.GREEN);
        setVisible(true);
    }
}
