package Components;

import Configs.Fonts.FontSwitch;

import javax.swing.*;
import java.awt.*;

public class BlueButton extends JButton {
    public BlueButton(String str){
        super(str);
        setFont(FontSwitch.init(FontSwitch.LABELTEXT));
        setForeground(Color.WHITE);
    }
}
