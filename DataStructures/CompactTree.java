package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class CompactTree{
    CompactNode root = null;

    public CompactTree(JSONNode head){
        if( head == null ) return;
        this.root = dfs(head);
    }
    public CompactNode dfs(JSONNode now){
        CompactNode head = new CompactNode(now);
        if( head == null ) return null;

        for( JSONNode node : now.getChildren()){
            head.c.add(dfs(node));
        }

        return head;
    }

    class CompactNode {
        public String d; // data
        public List<CompactNode> c; // children
        public CompactNode p; // parent
        public int x = -1 ,y = -1,w = -1,h = -1; // x , y ,width, height
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
            level = now.getLevel(); idx = now.getIdx();
            color = now.getColor(); tC = now.getTextColor();
            ch = now.getChanged(); //dCh = now.getDataChanged();
            wCh = now.getWidthChanged(); hCh = now.getHeightChanged();
        }
    }
}

