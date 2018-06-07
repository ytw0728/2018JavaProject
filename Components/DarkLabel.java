package Components;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;

import javax.swing.*;
import java.awt.*;

public class DarkLabel extends JLabel {
    public DarkLabel(String str){
        super(str);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(FontSwitch.init(FontSwitch.LABELTEXT));
        setForeground(ColorSwitch.init(ColorSwitch.DEFAUlT));
        setOpaque(true);
    }
}
