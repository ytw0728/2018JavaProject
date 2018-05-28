package Components;

import javax.swing.*;
import java.awt.*;

public class DarkLabel extends JLabel {
    public DarkLabel(String str){
        super(str);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        setForeground(Color.WHITE);
        setOpaque(true);
    }
}
