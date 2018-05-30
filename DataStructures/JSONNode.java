package DataStructures;
import Configs.Fonts.FontSwitch;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JSONNode {
    private String data;
    private List<JSONNode> children;
    private JSONNode parent;
    private int x = -1 ,y = -1,width = -1,height = -1;
    private int contentX = -1, contentY = -1, contentWidth = -1, contentHeight = -1;
    private int arrowStartX = 0, arrowStartY = 0;
    private int arrowEndX = 0, arrowEndY = 0;
    private int level = -1, idx = -1;
    private boolean selection = false;
    private String color = "";
    private String textColor = "";
    private boolean changed = false;
    private boolean xChanged = false;
    private boolean yChanged = false;

    public JSONNode(String str){
        this.data = str;
        this.children = new ArrayList<JSONNode>();
        this.parent= null;
    }
    public void setData(String data){this.data = data; changed = true;}
    public void setX(int x){this.x=x; changed = true;}
    public void setY(int y){this.y=y; changed = true;}
    public void setWidth(int width){this.width = width; changed = true; xChanged = true;}
    public void setHeight(int height){this.height= height; changed = true; yChanged = true;}
    public void setContentX(int x){this.contentX = x;}
    public void setContentY(int y){this.contentY= y;}
    public void setContentWidth(int width){this.contentWidth = width;}
    public void setContentHeight(int height){this.contentHeight= height;}
    public void setArrowStart(int x, int y){this.arrowStartX = x; this.arrowStartY = y;}
    public void setArrowEnd(int x,int y){this.arrowEndX = x; this.arrowEndY = y;}
    public void setLevel(int level){this.level = level;}
    public void setIdx(int idx){this.idx = idx;}
    public void setSelection(boolean b){this.selection = b;}
    public void setColor(String str){this.color= str; changed = true;}
    public void setTextColor(String str){this.textColor=str; changed = true;}
    public void setChanged(boolean t){this.changed = t;}
    public void setXChanged(boolean t){this.xChanged = t;}
    public void setYChanged(boolean t){this.yChanged = t;}

    public void setChildren(JSONNode newNode){
        if( children.size() < 4) children.add(newNode);
        else System.out.println("You can't add the child nodes more than 4.");
    }
    public void setParent(JSONNode parent){this.parent = parent; changed = true;}

    public String getData(){return data;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getContentX(){return this.contentX;}
    public int getContentY(){return this.contentY;}
    public int getContentWidth(){return this.contentWidth;}
    public int getContentHeight(){return this.contentHeight;}
    public int getArrowStartX(){return arrowStartX;}
    public int getArrowStartY(){return arrowStartY;}
    public int getArrowEndX(){return arrowEndX;}
    public int getArrowEndY(){return arrowEndY;}
    public int getLevel(){return level;}
    public int getIdx(){return idx;}
    public boolean getSelection(){return selection;}
    public String getColor(){return color;}
    public String getTextColor(){return textColor;}
    public boolean getChanged(){return changed;}
    public boolean getXChanged(){return xChanged ;}
    public boolean getYChanged(){return yChanged ;}

    public List<JSONNode> getChildren(){return children;}
    public JSONNode getLastChild(){
        if( children.size() != 0 )
            return children.get(children.size()-1);
        return null;
    }
    public boolean hasChild(){ return children.size() != 0;}
    public JSONNode getParent(){return parent;}
    public static JSONNode findInXY(JSONNode node, int x, int y){
        if( node == null ) return null;
        if( node.getX() <= x && node.getY() <= y && x <= node.getX() + node.getWidth() && y <= node.getY() + node.getHeight() ) return node;
        if( node.getChildren() == null ) return null;

        JSONNode tmp = null;
        for( int i = 0 ; i  < node.getChildren().size() ; i++){
            if( (tmp = findInXY(node.getChildren().get(i), x, y) ) != null ){
                return tmp;
            }
        }
        return tmp;
    }

    public static JSONNode addJSON(JSONNode head, String str, int idx){
        JSONNode now = head;
        if( now == null ){
            if( idx == 0 ){
                JSONNode tmp = new JSONNode(str);
                head = tmp;
            }
            return head;
        }
        while( idx > 1 && now.getChildren() !=null  ){
            now = now.getLastChild();
            idx--;
        }
        if( idx > 1 ){
            System.out.println("The error is detected in addJSON of JSON class.");
            return head;
        }
        JSONNode tmp = new JSONNode(str);
        tmp.setParent(now);
        now.setChildren(tmp);
        return head;
    }
}