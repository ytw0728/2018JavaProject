package DataStructures;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JSONNode {
    private String data;
    private List<JSONNode> children;
    private JSONNode parent;
    private int x,y,width,height;

    public JSONNode(String str){
        this.data = str;
        this.children = new ArrayList<JSONNode>();
        this.parent= null;
    }
    public void setData(String data){this.data = data;}
    public void setChildren(JSONNode newNode){
        if( children.size() < 4) children.add(newNode);
        else System.out.println("You can't add the child nodes more than 4.");
    }
    public void setParent(JSONNode parent){this.parent = parent;}

    public String getData(){return data;}
    public List<JSONNode> getChildren(){return children;}
    public JSONNode getLastChild(){
        if( children.size() != 0 )
            return children.get(children.size()-1);
        return null;
    }
    public boolean hasChild(){ return children.size() != 0;}
    public JSONNode getParent(){return parent;}

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
        now.setChildren( tmp);
        return head;
    }
}