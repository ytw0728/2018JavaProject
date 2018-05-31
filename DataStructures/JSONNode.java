package DataStructures;
import javax.swing.*;
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
    private boolean widthChanged = false;
    private boolean heightChanged = false;
//    private boolean dataChanged = false;

    public JSONNode(String str){
        this.data = str;
        this.children = new ArrayList<JSONNode>();
        this.parent= null;
    }
//    public void setData(String data){ if( this.data != data){ this.data = data; changed = true; dataChanged = true; }}
    public void setX(int x){if( this.x != x ){ this.x=x; changed = true;}}
    public void setY(int y){if(this.y != y){this.y=y; changed = true;}}
    public void setWidth(int width){if(this.width != width){ this.width = width; changed = true; widthChanged = true; }}
    public void setHeight(int height){if(this.height != height){ this.height= height; changed = true; heightChanged = true; }}
    public void setContentX(int x){this.contentX = x;}
    public void setContentY(int y){this.contentY= y;}
    public void setContentWidth(int width){this.contentWidth = width;}
    public void setContentHeight(int height){this.contentHeight= height;}
    public void setArrowStart(int x, int y){this.arrowStartX = x; this.arrowStartY = y;}
    public void setArrowEnd(int x,int y){this.arrowEndX = x; this.arrowEndY = y;}
    public void setLevel(int level){this.level = level;}
    public void setIdx(int idx){this.idx = idx;}
    public void setSelection(boolean b){this.selection = b;}
    public void setColor(String str){if(this.color != str){this.color= str; changed = true;}}
    public void setTextColor(String str){if(this.textColor != str){this.textColor=str; changed = true;}}
    public void setChanged(boolean t){this.changed = t;}
    public void setWidthChanged(boolean t){this.widthChanged = t;}
    public void setHeightChanged(boolean t){this.heightChanged = t;}
//    public void setDataChanged(boolean t){this.dataChanged = t;}

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
    public boolean getWidthChanged(){return widthChanged ;}
    public boolean getHeightChanged(){return heightChanged ;}
//    public boolean getDataChanged(){return dataChanged;}

    public List<JSONNode> getChildren(){return children;}
    public JSONNode getLastChild(){
        if( children.size() != 0 )
            return children.get(children.size()-1);
        return null;
    }
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

    public static JSONNode addJSON(JSONNode head, String str, int idx /* is level of node*/ ){
        JSONNode now = head;
        JSONNode tmp = new JSONNode(str);

        tmp.setIdx(idx);
        if( now == null ){
            if( idx == 0 ){
                tmp.setParent(null);
                head = tmp;
            }
            else{
                JOptionPane.showMessageDialog(null, "올바르지 않은 입력 방식입니다.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return head;
        }
        while( idx > 1 && now.getChildren() != null  && now.getChildren().size() > 0 ){
            now = now.getLastChild();
            idx--;
        }
        if( idx != 1 ){
            JOptionPane.showMessageDialog(null, "올바르지 않은 입력 방식입니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        tmp.setParent(now);
        now.setChildren(tmp);
        return head;
    }
}