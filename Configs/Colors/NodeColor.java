package Configs.Colors;

import java.awt.*;

public class NodeColor extends Color {

    public static NodeColor init(int level){
        switch(level){
            case 0 :
                return new NodeColor(126,190,247);
            case 1:
                return new NodeColor(146,216,194);
            case 2 :
                return new NodeColor(220,211,255);
            case 3 :
                return new NodeColor(247,242,200);
            case 4 :
                return new NodeColor(203,233,195);
            default:
                return new NodeColor(100,148,237 );
        }
    }
    public NodeColor(){
        this(100,148,237 );
    }
    private NodeColor(int r,int g, int b){
        super(r,g,b);
    }
}
