package Components;

import Configs.Colors.ColorSwitch;
import Configs.Fonts.FontSwitch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BlueButton extends JButton {
    public BlueButton(String str){
        super(str);
        setFont(FontSwitch.init(FontSwitch.LABELTEXT));
        setBackground(ColorSwitch.init(ColorSwitch.KEYCOLOR));
        setBorder(new EmptyBorder(0,0,0,0));
        setForeground(Color.WHITE);
    }
}
