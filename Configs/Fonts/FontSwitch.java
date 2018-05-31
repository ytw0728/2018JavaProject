package Configs.Fonts;

import java.awt.*;

public class FontSwitch extends Font {
    public static final int EDITOR = 1, MENUBAR = 2, NORMAL=3, LABELTEXT = 4, SMALL = 5, BOLD = 6;
    public static FontSwitch init(int option){
        switch(option){
            case EDITOR:
                return new FontSwitch("¸¼Àº °íµñ", Font.PLAIN, 20);
            case LABELTEXT:
                return new FontSwitch("¸¼Àº °íµñ", Font.PLAIN, 16);
            case SMALL :
                return new FontSwitch("¸¼Àº °íµñ", Font.PLAIN, 10);
            case BOLD :
                return new FontSwitch("¸¼Àº °íµñ", Font.BOLD, 16);
            case MENUBAR :
            case NORMAL :
            default :
                return new FontSwitch("Dotum",Font.PLAIN,13);
        }
    }
    public FontSwitch(){
        super("¸¼Àº °íµñ", Font.PLAIN, 13);
    }
    private FontSwitch(String attr, int type, int size){
        super(attr, type, size);
    }
}
