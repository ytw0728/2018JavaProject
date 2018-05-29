package Panels;

import Components.DarkLabel;
import Configs.Colors.ColorSwitch;
import Configs.Colors.NodeColor;
import Configs.Fonts.FontSwitch;
import DataStructures.JSONNode;
import Main.ProgramFrame;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.*;

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
        label.setBackground(ColorSwitch.init(ColorSwitch.DEEPDARK) );
        label.setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DEEPDARK), 1));
        add(label);

        mindMap = new MindMap(0, y/20,x,y-y/20);
        add(mindMap);

        setBackground(ColorSwitch.init(ColorSwitch.DARK));
        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK), 1));
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
        setBackground(ColorSwitch.init(ColorSwitch.DARK));
        setFont(FontSwitch.init(FontSwitch.NORMAL));
        setBorder(BorderFactory.createLineBorder (ColorSwitch.init(ColorSwitch.DARK) , 1));
        setForeground(Color.WHITE);
        setVisible(true);
        this.addMouseListener(new MindMapMouseListener());
        this.addMouseMotionListener(new MindMapMouseMotionListener());
    }
    public void setHead(JSONNode head){ this.head = head; }
    public void printHead(){
        g2d = (Graphics2D)getGraphics();
        g2d.setFont(FontSwitch.init(FontSwitch.BOLD));
        g2d.setColor(ColorSwitch.init(ColorSwitch.DEEPDARK));
        g2d.fillRect(0,0, getWidth(), getHeight());
        g2d.setColor(ColorSwitch.init(ColorSwitch.LIGHTFONT));
        dfsNodes(head,0, 0);
    }

    private boolean clicked = false;
    private JSONNode target = null;
    private boolean dragProcessing = false;
    private class MindMapMouseMotionListener implements  MouseMotionListener {
        public void mouseMoved(MouseEvent e){

        }
        public void mouseDragged(MouseEvent e){
            if( clicked && !dragProcessing){
                dragProcessing = true;
                int x = e.getX(); int y = e.getY();
                int lastX = target.getX(); int lastY = target.getY();
                x -= target.getWidth()/2;
                y -= target.getHeight()/2;
                target.setX(x);
                target.setY(y);
                target.setArrowStart(target.getArrowStartX() - lastX + x, target.getArrowStartY() - lastY + y);
                target.setContentX(target.getContentX() - lastX + x);
                target.setContentY(target.getContentY() - lastY + y);

                dragProcessing = false;
//                repaint(head); // 깜빡깜빡문제 해결하기
            }
        }
    }

    private class MindMapMouseListener implements MouseListener{
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            target = null;
            if( (target = JSONNode.findInXY(head,x, y)) != null ){
                System.out.println(target.getData());
                clicked = true;
                target.setSelection(true);
                drawNodes(head);

            }
        }

        public void mouseClicked(MouseEvent e){

        }

        public void mouseReleased(MouseEvent e) {
            if( clicked ){
                clicked = false;
                target.setSelection(false);
                target = null;
                repaint(head);
            }
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }
    private void repaint(JSONNode now){
        g2d.setColor(ColorSwitch.init(ColorSwitch.DEEPDARK));
        g2d.fillRect(0,0, getWidth(), getHeight());
        drawNodes(now);
    }
    private void drawNodes(JSONNode now){
        if( now == null ) return;

        draw(now);
        for( int i = 0; i < now.getChildren().size(); i++){
            drawNodes(now.getChildren().get(i));
        }
    }
    private void draw(JSONNode now){
        if( now.getSelection() ) g2d.setColor(new NodeColor());
        else g2d.setColor(NodeColor.init(now.getLevel()));
        g2d.fillRoundRect(now.getX(),now.getY(),now.getWidth(),now.getHeight(), 10,10);


        if( now.getLevel() != 0 ) {
            JSONNode parent = now.getParent();
            int parX = parent.getX(), parY = parent.getY(), parHeight = parent.getHeight(), parWidth = parent.getWidth();
            switch (now.getIdx()) {
                case 0: // 2사분면;
                    now.setArrowStart(now.getX() + now.getWidth() / 2, now.getY() + now.getHeight());// 하
                    now.setArrowEnd(parX, parY + parHeight / 2); // 좌
                    break;
                case 1: // 1사분면
                    now.setArrowStart(now.getX(), now.getY() + now.getHeight() / 2); // 좌
                    now.setArrowEnd(parX + parWidth / 2, parY); // 상
                    break;
                case 2: // 4사분면
                    now.setArrowStart(now.getX() + now.getWidth() / 2, now.getY()); // 상
                    now.setArrowEnd(parX + parWidth, parY + parHeight / 2); // 우
                    break;
                case 3: // 3사분면
                    now.setArrowStart(now.getX() + now.getWidth(), now.getY() + now.getHeight() / 2); // 우
                    now.setArrowEnd(parX + parWidth / 2, parY + parHeight); // 하
                    break;
            }

            drawLine(now.getArrowStartX(), now.getArrowStartY(), now.getArrowEndX(), now.getArrowEndY());
        }
        if( now.getSelection() ) g2d.setColor(Color.WHITE);
        else g2d.setColor(Color.BLACK);
        g2d.drawString(now.getData(),now.getContentX(), now.getContentY());
    }
    private void dfsNodes(JSONNode now, int level, int idx){
        if( now == null ) return;

        draw(now,level,idx);
        for( int i = 0; i < now.getChildren().size(); i++){
            dfsNodes(now.getChildren().get(i),level+1, i);
        }
    }
    private void draw(JSONNode now, int level, int idx){
        Font font = g2d.getFont();
        FontRenderContext context = g2d.getFontRenderContext();
        if( now.getX() == -1 ) {
            now.setLevel(level);
            now.setIdx(idx);
            now.setContentWidth((int) font.getStringBounds(now.getData(), context).getWidth());
            LineMetrics ln = font.getLineMetrics(now.getData(), context);
            now.setContentHeight((int) (ln.getAscent() + ln.getDescent()));

            now.setWidth(now.getContentWidth()+ 20);
            now.setHeight(now.getContentHeight()+ 10);
            if( level == 0 ){
                now.setX(getWidth()/2-now.getWidth()/2);
                now.setY(getHeight()/2-now.getHeight()/2);
            }
            else{
                JSONNode parent = now.getParent();
                int parX= parent.getX();
                int parY= parent.getY();
                int parWidth = parent.getWidth();
                int parHeight = parent.getHeight();
                int margin = 150;
                int tmpLevel = level;
                while(tmpLevel-- > 0){
                    margin /= 3;
                    margin *= 2;
                }
                switch(idx){
                    case 0: // 2사분면
                        now.setX(parX - margin - now.getWidth()/2);
                        now.setY(parY - margin - now.getHeight()/2);
                        now.setArrowStart(now.getX() + now.getWidth()/2, now.getY() + now.getHeight());// 하
                        now.setArrowEnd(parX, parY + parHeight/2); // 좌
                        break;
                    case 1: // 1사분면
                        now.setX(parX + parWidth  + margin- now.getWidth()/2);
                        now.setY(parY - margin- now.getHeight()/2);
                        now.setArrowStart(now.getX(), now.getY() + now.getHeight()/2 ); // 좌
                        now.setArrowEnd(parX + parWidth/2, parY); // 상
                        break;
                    case 2: // 4사분면
                        now.setX(parX + parWidth + margin- now.getWidth()/2);
                        now.setY(parY + parHeight + margin- now.getHeight()/2);
                        now.setArrowStart(now.getX() + now.getWidth() / 2, now.getY()); // 상
                        now.setArrowEnd(parX+parWidth , parY + parHeight/2); // 우
                        break;
                    case 3: // 3사분면
                        now.setX(parX - margin- now.getWidth()/2);
                        now.setY(parY + parHeight + margin- now.getHeight()/2);
                        now.setArrowStart(now.getX() + now.getWidth(), now.getY()+now.getHeight()/2); // 우
                        now.setArrowEnd(parX+parWidth/2,parY + parHeight); // 하
                        break;
                }

            }
            now.setContentX(now.getX() + (now.getWidth() - now.getContentWidth()) / 2);
            now.setContentY((int) (now.getY() + (now.getHeight() + now.getContentHeight()) / 2 - ln.getDescent()));
        }
        g2d.setColor(NodeColor.init(level));
        g2d.fillRoundRect(now.getX(),now.getY(),now.getWidth(),now.getHeight(), 10,10);
        if( level != 0 ) {
            drawLine(now.getArrowStartX(), now.getArrowStartY(), now.getArrowEndX(), now.getArrowEndY());
        }
        g2d.setColor(Color.BLACK);

        g2d.drawString(now.getData(),now.getContentX(), now.getContentY());
    }
    private void drawLine(double x1,double y1, double x2, double y2){
        double cx = x1, cy = y1;
        if( x1 <= x2 ) {
            if ((y2 - y1) / (x2 - x1) >= 0){
                cx = x1; cy = y2;
            }
            else{
                cx = x2; cy = y1;
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
        Point2D p1 = line.getP2();
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