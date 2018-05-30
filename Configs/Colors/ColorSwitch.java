package Configs.Colors;

import java.awt.*;

public class ColorSwitch extends Color {
    public static final int KEYCOLOR= 0, BRIGHT = 1, DARK = 2, DEEPDARK = 3, LIGHTFONT = 4, BRIGHTFONT =5, BRIGHTTEST = 6;
    public static ColorSwitch init(int option){
        switch(option){
            case KEYCOLOR :
                return new ColorSwitch(100,148,237 );
            case LIGHTFONT :
                return new ColorSwitch(102, 102, 102);
            case BRIGHTFONT :
                return new ColorSwitch(204,208,213);
            case BRIGHTTEST :
                return new ColorSwitch(61,65,76);
            case BRIGHT:
                return new ColorSwitch(47,51,61);
            case DEEPDARK :
                return new ColorSwitch(33,37,43);
            case DARK :
            default:
                return new ColorSwitch(40,44,52);
        }
    }
    public ColorSwitch(){
        this(40,44,52);
    }
    private ColorSwitch(int r,int g, int b){
        super(r,g,b);
    }
}
