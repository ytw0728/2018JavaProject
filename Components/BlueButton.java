package Components;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;

import javax.swing.*;
import java.awt.*;

public class BlueButton extends JButton {
    public BlueButton(String str){
        super(str);
        setFont(FontSwitch.init(FontSwitch.LABELTEXT));
        setBackground(ColorSwitch.init(ColorSwitch.KEYCOLOR));
        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.KEYCOLOR), 1));
        setForeground(Color.WHITE);
    }
}
