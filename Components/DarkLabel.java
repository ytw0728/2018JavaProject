package Components;

import Configs.Fonts.FontSwitch;

import javax.swing.*;
import java.awt.*;

public class DarkLabel extends JLabel {
    public DarkLabel(String str){
        super(str);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(FontSwitch.init(FontSwitch.LABELTEXT));
        setForeground(Color.WHITE);
        setOpaque(true);
    }
}
