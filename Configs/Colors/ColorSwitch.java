package Configs.Colors;

import java.awt.*;

public class ColorSwitch extends Color {
    public static final int KEYCOLOR= 0, BRIGHT = 1, DARK = 2, DEEPDARK = 3, LIGHTFONT = 4, BRIGHTFONT =5, BRIGHTTEST = 6, DEFAUlT = 7, OPPOSITION = 8;
    public static boolean isDark = true;
    public static ColorSwitch init(int option){
        if( isDark ) {
            switch (option) {
                case DEFAUlT :
                    return new ColorSwitch( 255, 255, 255);
                case OPPOSITION:
                    return new ColorSwitch( 0,0,0);
                case KEYCOLOR:
                    return new ColorSwitch(100, 148, 237);
                case LIGHTFONT:
                    return new ColorSwitch(102, 102, 102);
                case BRIGHTFONT:
                    return new ColorSwitch(204, 208, 213);
                case BRIGHTTEST:
                    return new ColorSwitch(61, 65, 76);
                case BRIGHT:
                    return new ColorSwitch(47, 51, 61);
                case DEEPDARK:
                    return new ColorSwitch(33, 37, 43);
                case DARK:
                default:
                    return new ColorSwitch(40, 44, 52);
            }
        }
        else{
            switch (option) {
                case DEFAUlT :
                    return new ColorSwitch(0,0,0);
                case OPPOSITION:
                    return new ColorSwitch( 255, 255, 255);
                case KEYCOLOR:
                    return new ColorSwitch(100, 148, 237);
                case LIGHTFONT:
                    return new ColorSwitch(10, 10, 10);
                case BRIGHTFONT:
                    return new ColorSwitch(30, 30, 30);
                case BRIGHTTEST:
                    return new ColorSwitch(255, 255, 255);
                case BRIGHT:
                    return new ColorSwitch(200, 200, 200);
                case DEEPDARK:
                    return new ColorSwitch(150, 150, 150);
                case DARK:
                default:
                    return new ColorSwitch(255, 255, 255);
            }
        }
    }
    public ColorSwitch(){ this(40,44,52); }
    private ColorSwitch(int r,int g, int b){
        super(r,g,b);
    }

    public static void switchTheme(){
        isDark = !isDark;
    }
}
