package Panels;

import Components.DarkLabel;
import DataStructures.JSONNode;
import Main.ProgramFrame;
import sun.awt.geom.Curve;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class MindMapPane extends JPanel {
    ProgramFrame parent = null;
    private MindMap mindMap = null;
    public MindMapPane(int x, int y, String str) {
        JScrollPane scroll = new JScrollPane();
        setLayout(null);

        setBounds(0,0, x, y);
        setMinimumSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y));
        setMaximumSize(new Dimension(x,y));

        DarkLabel label = new DarkLabel(str);
        label.setBounds(0,0,150, y/20);
        label.setBackground(new Color(33,37,43) );
        label.setBorder(BorderFactory.createLineBorder (new Color(33,37,43) , 1));
        add(label);

        mindMap = new MindMap(0, y/20,x,y-y/20);
        add(mindMap);

        setBackground(new Color(40,44,52));
        setBorder(BorderFactory.createLineBorder (new Color(40,44,52), 1));
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setBounds(0,0,150, y/20);
            }
        });

        scroll.add(this);
    }
    public void setParent(ProgramFrame frame){ parent = frame;}
    public void setHead(JSONNode head){ mindMap.setHead(head); }
    public void printHead(){ mindMap.printHead();}
    public int getData(){return 1;}
}

class MindMap extends JPanel{
    JSONNode head = null;
    Graphics2D g2d = null;

    public MindMap(int x, int y, int width, int height) {
        setLayout(null);
        setBounds(x,y, width, height);
        setBackground(new Color(33,37,43) );
        setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        setBorder(BorderFactory.createLineBorder (new Color(33,37,43) , 1));
        setForeground(Color.WHITE);
        setVisible(true);
    }
    public void setHead(JSONNode head){ this.head = head; }
    public void printHead(){
        g2d = (Graphics2D)getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        dfs(null, head,0, 0);
    }
    private void dfs(JSONNode parent, JSONNode now, int level, int idx){
        if( now == null ) return;

        draw(now.getData(),200,200);
        drawLine(100,100,200,200);
        for( int i = 0; i < now.getChildren().size(); i++){
            dfs(now, now.getChildren().get(i),level+1, i);
        }
    }
    private void draw(String str, int x, int y){
        Font font = g2d.getFont();
        FontRenderContext context = g2d.getFontRenderContext();
        int textWidth = (int) font.getStringBounds(str, context).getWidth();
        LineMetrics ln = font.getLineMetrics(str, context);
        int textHeight = (int) (ln.getAscent() + ln.getDescent());

        int width = textWidth + 20;
        int height = textHeight + 10;

        int x1 = x + (width - textWidth)/2;
        int y1 = (int)(y + (height + textHeight)/2 - ln.getDescent());


        g2d.setColor(Color.BLUE);
        g2d.fillRoundRect(x,y,width,height, 10,10);
        g2d.setColor(Color.WHITE);
        g2d.drawString(str,x1, y1);

    }
    private void drawLine(double x1,double y1, double x2, double y2){
        double cx = x1, cy = y1;
        if( x1 <= x2 ) {
            if ((y2 - y1) / (x2 - x1) >= 0){
                cx = x2; cy = y1;
            }
            else{
                cx = x1; cy = y2;
            }
        }
        else{
            if ((y2 - y1) / (x2 - x1) >= 0) {
                cx = x1; cy = y2;
            }
            else{
                cx = x2; cy = y1;
            }
        }

        QuadCurve2D quadCurve = new QuadCurve2D.Double(x1,y1, cx, cy, x2, y2);
        g2d.draw(quadCurve);

        Shape arrowHead = createArrowHead(quadCurve, 10,10,cx,cy);
        g2d.fill(arrowHead);
    }
    private static Shape createArrowHead(QuadCurve2D line, double length, double width, double cx, double cy)
    {
//        Point2D p0 = line.getP1();
        Point2D p1 = line.getP2();
//        double x0 = p0.getX();
//        double y0 = p0.getY();
        double x0 = cx;
        double y0 = cy;
        double x1 = p1.getX();
        double y1 = p1.getY();
        double dx = x1 - x0;
        double dy = y1 - y0;

        double invLength = 1.0 / Math.sqrt(dx*dx+dy*dy);
        double dirX = dx * invLength;
        double dirY = dy * invLength;
        double ax = x1 - length * dirX;
        double ay = y1 - length * dirY;
        double offsetX = width * -dirY * 0.5;
        double offsetY = width * dirX * 0.5;
        double c0x = ax + offsetX;
        double c0y = ay + offsetY;
        double c1x = ax - offsetX;
        double c1y = ay - offsetY;

        Path2D arrowHead = new Path2D.Double();
        arrowHead.moveTo(x1, y1);
        arrowHead.lineTo(c0x, c0y);
        arrowHead.lineTo(c1x, c1y);
        arrowHead.closePath();
        return arrowHead;
    }
}