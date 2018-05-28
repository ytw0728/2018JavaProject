package Components;

import javax.swing.*;
import java.awt.*;

public class BlueButton extends JButton {
    public BlueButton(String str){
        super(str);
        setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        setForeground(Color.WHITE);
    }
}
