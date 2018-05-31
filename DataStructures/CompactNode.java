package DataStructures;

import java.util.ArrayList;
import java.util.List;
public class CompactNode {
    public String d; // data
    public List<CompactNode> c; // children
    public CompactNode p; // parent
    public int x = -1 ,y = -1,w = -1,h = -1; // x , y ,width, height
    public int cX = -1, cY = -1, cW = -1, cH = -1;
    public int level = -1, idx = -1;
    public String color = "";
    public String tC = ""; //textColor
    public boolean ch = false; // changed
    public boolean wCh = false; // widthChanged
    public boolean hCh = false; // heightChanged
//        public boolean dCh = false; // dataChanged

    public CompactNode(JSONNode now){
        if( now == null ) return;
        d = now.getData();
        c = new ArrayList<CompactNode>();
        x = now.getX(); y = now.getY();
        w = now.getWidth(); h = now.getHeight();
        cX = now.getContentX(); cY = now.getContentY(); cW = now.getContentWidth(); cH = now.getContentHeight();
        level = now.getLevel(); idx = now.getIdx();
        String textColor = now.getTextColor();
        color = now.getColor(); tC = (textColor  == "#000000" ? "" : textColor);
        ch = now.getChanged(); //dCh = now.getDataChanged();
        wCh = now.getWidthChanged(); hCh = now.getHeightChanged();
    }

    public static CompactNode makeTree(JSONNode now){
        if( now == null ) return null;
        CompactNode head = dfs(now);
        return head;
    }
    private static CompactNode dfs(JSONNode now){
        CompactNode head = new CompactNode(now);
        if( head == null ) return null;
        for( JSONNode node : now.getChildren()){
            head.c.add(dfs(node));
        }
        return head;
    }

    public JSONNode convertToJSONNode(){
        JSONNode head = null;
        head = dfs(head,this);
        return head;
    }
    private JSONNode dfs(JSONNode parent, CompactNode now){
        JSONNode head = new JSONNode(now.d);
        if( head == null ) return null;
        head.setX(now.x); head.setY(now.y); head.setWidth(now.w); head.setHeight(now.h);
        head.setContentX(now.cX); head.setContentY(now.cY);
        head.setContentWidth(now.cW); head.setContentHeight(now.cH);
        head.setLevel(now.level); head.setIdx(now.idx);
        head.setColor(now.color); head.setTextColor(now.tC);
        head.setChanged(now.ch); head.setWidthChanged(now.wCh);
        head.setHeightChanged(now.hCh);
        head.setParent(parent);
        for( CompactNode node : now.c){
            head.getChildren().add(dfs(head,node));
        }
        return head;
    }
}