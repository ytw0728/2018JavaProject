package Configs;

import Configs.Colors.ColorSwitch;
import Configs.Numerics.Settings;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class Common {
    public static boolean RESIZABLE = false;
    public static String INPUTERRORMSG(){ return "�ùٸ��� ���� �Է� ����Դϴ�.\n��Ʈ���� �ϳ��̾�� �մϴ�.\n���� �� ���� �ܰ踦 �ǳʶ� �� ������, �� �ܰ�� tab���� ���е˴ϴ�.";}
    public static String OVERCHILDNUMMSG(){ return "�ùٸ��� ���� �Է��Դϴ�.\n���� �� ��� �� ���� �ڽ��� ������ '" + Settings.CHILDRENNUM+ "��' �Դϴ�.\n�ִ� �ڽ� ��� ������ �������ּ���.";}

    public static String FILESAVEERRORMSG(){ return "���� ������ ���������� ������� �ʾҽ��ϴ�. �ٽ� �õ����ּ���.";}
    public static String FILENOTFOUNDERRORMSG(){ return "�ش� ������ �������� �ʽ��ϴ�.";}
    public static String FILEIOERRORMSG(){ return "�ش� ������ ���� �� �����ϴ�.";}
    public static String OPENERRORMSG(){return "���� �ҷ����⸦ �����߽��ϴ�. �ٽ� �õ����ּ���.";}

    public static String NOTCOLORHEXERRORMSG(){return "�ùٸ� �÷� ����ڵ带 �Է����ּ��� ex) #ffffff";}
    public static String ATTRNUMBERZONEMSG(){ return "x, y, width, height�� ";}
    public static String NOTNUMBERERRORMSG(){ return "���� ������ �Է��� �� �ֽ��ϴ�.";}

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
