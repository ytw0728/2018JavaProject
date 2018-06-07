package Configs;

import Configs.Colors.ColorSwitch;
import Configs.Numerics.Settings;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class Common {
    public static boolean RESIZABLE = false;
    public static String INPUTERRORMSG(){ return "올바르지 않은 입력 방식입니다.\n루트노드는 하나이어야 합니다.\n또한 각 노드는 단계를 건너뛸 수 없으며, 각 단계는 tab으로 구분됩니다.";}
    public static String OVERCHILDNUMMSG(){ return "올바르지 않은 입력입니다.\n현재 한 노드 당 허용된 자식의 갯수는 '" + Settings.CHILDRENNUM+ "개' 입니다.\n최대 자식 노드 개수를 변경해주세요.";}

    public static String FILESAVEERRORMSG(){ return "파일 저장이 정상적으로 수행되지 않았습니다. 다시 시도해주세요.";}
    public static String FILENOTFOUNDERRORMSG(){ return "해당 파일이 존재하지 않습니다.";}
    public static String FILEIOERRORMSG(){ return "해당 파일을 읽을 수 없습니다.";}
    public static String OPENERRORMSG(){return "파일 불러오기를 실패했습니다. 다시 시도해주세요.";}

    public static String NOTCOLORHEXERRORMSG(){return "올바른 컬러 헥사코드를 입력해주세요 ex) #ffffff";}
    public static String ATTRNUMBERZONEMSG(){ return "x, y, width, height는 ";}
    public static String NOTNUMBERERRORMSG(){ return "양의 정수만 입력할 수 있습니다.";}

    public static BasicScrollBarUI MindMapScrollUI() {
        return new BasicScrollBarUI(){
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = ColorSwitch.init(ColorSwitch.BRIGHTTEST);
                this.trackColor = ColorSwitch.init(ColorSwitch.DEEPDARK);
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        };
    }


    public static BasicScrollBarUI DefaultScrollBarUI() {
        return new BasicScrollBarUI(){
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = ColorSwitch.init(ColorSwitch.BRIGHTTEST);
                this.trackColor = ColorSwitch.init(ColorSwitch.BRIGHT);
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        };
    }

}
