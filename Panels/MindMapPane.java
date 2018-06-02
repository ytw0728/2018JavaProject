package Panels;

import Components.DarkLabel;
import Components.NodeLabel;
import Configs.Colors.ColorSwitch;
import Configs.Colors.NodeColor;
import Configs.Fonts.FontSwitch;
import Configs.Numerics.Settings;
import DataStructures.JSONNode;
import Main.ProgramFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.*;

public class MindMapPane extends JPanel {
    ProgramFrame parent = null;
    private MindMap mindMap = null;
    private JScrollPane scroll = null;
    private DarkLabel label = null;

    public MindMapPane(int x, int y, String str) {
        setLayout(null);
        setBounds(0,0, x,y);

        label = new DarkLabel(str);
        label.setBounds(0,0,150, y/20);
        label.setBackground(ColorSwitch.init(ColorSwitch.DEEPDARK) );
        label.setBorder(new EmptyBorder(0,0,0,0));

        add(label);

        mindMap = new MindMap(0, 0, x,y-y/20);

        scroll = new JScrollPane(mindMap);
        scroll.setBounds(0,y/20, x, (y-y/20));
        scroll.getViewport().setBounds(0,0, x, (y-y/20));
        scroll.setBorder(new EmptyBorder(0,0,0,0));
        scroll.setViewportBorder(null);
        scroll.getViewport().setViewSize(new Dimension(x * 3, ( y -y/20 )* 3));

        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 0));
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
        scroll.setVisible(true);
        add(scroll);


        setBackground(ColorSwitch.init(ColorSwitch.DARK));
        setBorder(new EmptyBorder(0,0,0,0));
        setVisible(true);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mindMap.dispatchEvent(e);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { mindMap.dispatchEvent(e); }
            @Override
            public void mouseReleased(MouseEvent e) { mindMap.dispatchEvent(e); }
        });
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                label.setBounds(0,0,label.getWidth(), label.getHeight());
                scroll.setBounds(0,label.getHeight(), size.width, size.height - label.getHeight());
                scroll.getViewport().setViewSize(new Dimension(x * 3, ( y - y/20 )* 3));
                mindMap.setBounds(0,0, size.width, size.height - label.getHeight());

                Rectangle scrollBounds = scroll.getViewport().getViewRect();
                Dimension scrollSize = new Dimension(x * 3, ( y - y/20 )* 3);
                int scrollX = (scrollSize.width - scrollBounds.width) / 2;
                int scrollY = (scrollSize.height - scrollBounds.height) / 2;
                scroll.getHorizontalScrollBar().setValue(scrollX);
                scroll.getVerticalScrollBar().setValue(scrollY);

            }
        });
    }
    public void setParent(ProgramFrame frame){
        parent = frame;
        mindMap.setFrame(this.parent);
    }
    public void setHead(JSONNode head){
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 13));
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(13, Integer.MAX_VALUE));
        revalidate();
        mindMap.setHead(head);
        parent.setRootHead(head);
    }
    public void printHead(){
        Rectangle scrollBounds = scroll.getViewport().getViewRect();
        Dimension scrollSize = scroll.getViewport().getViewSize();
        int scrollX = (scrollSize.width - scrollBounds.width) / 2;
        int scrollY = (scrollSize.height - scrollBounds.height) / 2;
        scroll.getHorizontalScrollBar().setValue(scrollX);
        scroll.getVerticalScrollBar().setValue(scrollY);
        mindMap.printHead();
    }
    public void clear(){
        mindMap.clear();
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 0));
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
        revalidate();
    }
    public void setTarget(JSONNode target){ mindMap.setTarget(target); }
    public void setCursorPointer(boolean t){ mindMap.setCursorPointer(t); }
}

class MindMap extends JPanel{
    private JSONNode head = null;
    private Graphics2D g2d;
    private Graphics g;
    private ProgramFrame frame = null;
    private AttributePane AB;
    private JSONNode target = null;
    private boolean targetIsset = false;
    private boolean initialized = false;
    private boolean modified = false;

    private boolean cursorPointer = false;
    public void clear(){
        timer.stop();
        removeAll();
        head = null;
        setTarget(null);
        initialized = false;
        targetIsset = false;
        g.setColor(ColorSwitch.init(ColorSwitch.DARK));
        g.fillRect(0,0,getWidth(),getHeight());

        g = null;
        g2d = null;
        revalidate();
    }
    public MindMap(int x, int y, int width, int height) {
        g = null; g2d = null; head = null; initialized = false;
        setFocusable(true);
        setLayout(null);
        setBounds(x,y, width * 3, height * 3);
        setPreferredSize(new Dimension(width * 3, height * 3));

        setBackground(ColorSwitch.init(ColorSwitch.DARK));
        setBorder(new EmptyBorder(0,0,0,0));
        setDoubleBuffered(true);
        setForeground(Color.WHITE);
        setVisible(true);

        this.addMouseListener(new MindMapMouseListener());
        this.addMouseMotionListener(new MindMapMouseMotionListener());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if( e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE ){

                    if( target != null ) target.setSelection(false);
                    target = null;
                    AB.setEditTarget(null);
                    AB.hideAP();
                }
            }
        });
    }
    public void setFrame(ProgramFrame frame){
        this.frame = frame;
        AB = (AttributePane)frame.getComponentsMap().get("AB");
    }
    public void setHead(JSONNode head){ initialized = false; this.head = head; }
    public void printHead(){
        timer.stop();
        initialized = false;
        requestFocusInWindow();
        if( g == null || g2d == null){
            repaint();
            return;
        }
        initAllNodes(head,0,0);
        initialized = true;
        repaint();
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.g = g;
        this.g2d = (Graphics2D) g;
        if( !initialized || head == null || targetIsset ) return;
        frame.setModified(modified);
        modified = false;
        if( cursorPointer ) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        removeAll();

        g.setColor(ColorSwitch.init(ColorSwitch.DEEPDARK));
        g.fillRect(0,0,getWidth(), getHeight());
        drawNodes(head);
    }

    private void drawNodes(JSONNode now){
        if( now == null ) return;
        if( now.getChanged() ) reInitNode(now);
        draw(now);
        for( int i = 0; i < now.getChildren().size(); i++){
            drawNodes(now.getChildren().get(i));
        }
    }

    private void draw(JSONNode now){
        if( now.getSelection() ) g2d.setColor(new NodeColor());
        else if( !now.getColor().trim().equals("")) g2d.setColor( Color.decode(now.getColor().trim()) );
        else g2d.setColor(NodeColor.init(now.getLevel()));
        NodeLabel label = now.getLabel();

        if( now.getSelection() ) label.setBackground(new NodeColor());
        else if( !now.getColor().trim().equals("")) label.setBackground( Color.decode(now.getColor().trim()) );
        else label.setBackground(NodeColor.init(now.getLevel()));

        label.setLocation(now.getX(), now.getY());
        label.setSize(now.getWidth(), now.getHeight());

        if( now.getLevel() != 0 ) {
            JSONNode parent = now.getParent();
            int parX = parent.getX(), parY = parent.getY(), parHeight = parent.getHeight(), parWidth = parent.getWidth();
            int x = now.getX(); int y = now.getY();

            if( parX <= x ) {
                if( parY <= y) {
                    // 1사분면
                    now.setArrowStart(now.getX() + now.getWidth() / 2, now.getY()); // 상
                    now.setArrowEnd(parX + parWidth, parY + parHeight / 2); // 우
                }
                else {
                    // 4사분면
                    now.setArrowStart(now.getX(), now.getY() + now.getHeight() / 2); // 좌
                    now.setArrowEnd(parX + parWidth / 2, parY); // 상
                }
            }
            else {
                if(parY <= y ) {
                    // 2사분면
                    now.setArrowStart(now.getX() + now.getWidth(), now.getY() + now.getHeight() / 2); // 우
                    now.setArrowEnd(parX + parWidth / 2, parY + parHeight); // 하
                }
                else {
                    // 3사분면;
                    now.setArrowStart(now.getX() + now.getWidth() / 2, now.getY() + now.getHeight());// 하
                    now.setArrowEnd(parX, parY + parHeight / 2); // 좌
                }
            }

            drawLine(now.getArrowStartX(), now.getArrowStartY(), now.getArrowEndX(), now.getArrowEndY());
        }
        if( now.getSelection() ) label.setForeground(Color.WHITE);
        else if( !now.getTextColor().trim().equals("")) label.setForeground(Color.decode(now.getTextColor().trim()));
        else label.setForeground(Color.BLACK);


        add(label);
    }

    private void reInitNode(JSONNode now) {
        modified = true;
        Font font = g2d.getFont();
        FontRenderContext context = g2d.getFontRenderContext();
        now.setContentWidth((int) font.getStringBounds(now.getData(), context).getWidth());
        LineMetrics ln = font.getLineMetrics(now.getData(), context);
        now.setContentHeight((int) (ln.getAscent() + ln.getDescent()));

//        if( now.getDataChanged() ) {
//        if (!now.getWidthChanged()) now.setWidth(now.getContentWidth() + 20);
//        if (!now.getHeightChanged()) now.setHeight(now.getContentHeight() + 10);
//    }

        now.setChanged(false);
//        now.setDataChanged(false);
        if( now.getSelection() ) AB.setText();
    }
    private void initAllNodes(JSONNode now, int level, int idx){
        g2d.setFont(FontSwitch.init(FontSwitch.BOLD));
        if( now == null ) return;
        initNode(now,level,idx);
        draw(now);
        for( int i = 0; i < now.getChildren().size(); i++){
            initAllNodes(now.getChildren().get(i),level+1, i);
        }
    }
    private void initNode(JSONNode now, int level, int idx){
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
                parX += parWidth /2;
                parY += parHeight /2;
//                int margin = 150;
//                int margin = 200;
                int margin = 300;
                int tmpLevel = level;
                while(tmpLevel-- > 0){
                    margin /= 3;
                    margin *= 2;
                }
                int childrenLoop = now.getIdx() / Settings.LAYOUTNUM;
                int angle = 360 / Settings.LAYOUTNUM;
                angle = ( angle * idx ) % 360;
                int spread = 10;
                int x = parX + (int)(margin * Math.cos(Math.toRadians(angle))) - now.getWidth()/2 + spread * childrenLoop;
                int y = parY + (int)(margin * Math.sin(Math.toRadians(angle))) - now.getHeight()/2 + spread * childrenLoop;

                now.setX(x);
                now.setY(y);
            }
        }
        NodeLabel label = new NodeLabel(now.getData(), now, 10);
        label.setBounds(now.getX(), now.getY(), now.getWidth(), now.getHeight());
        now.setLabel(label);

        now.setChanged(false);
//        now.setDataChanged(false);
        now.setWidthChanged(false);
        now.setHeightChanged(false);
    }
    private void drawLine(double x1,double y1, double x2, double y2){
        double cx = x1, cy = y1;
        if( x1 <= x2 ) {
            if ((y2 - y1) / (x2 - x1) >= 0){ cx = x1; cy = y2; }
            else{ cx = x2; cy = y1; }
        }
        else{
            if ((y2 - y1) / (x2 - x1) >= 0) { cx = x1; cy = y2; }
            else{ cx = x2; cy = y1; }
        }

        QuadCurve2D quadCurve = new QuadCurve2D.Double(x1,y1, cx, cy, x2, y2);
        g2d.draw(quadCurve);

        Shape arrowHead = createArrowHead(quadCurve, 10,10,cx,cy);
        g2d.fill(arrowHead);

    }
    private static Shape createArrowHead(QuadCurve2D line, double length, double width, double cx, double cy) {
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

    public void setTarget(JSONNode target){
        if(this.target != null ) this.target.setSelection(false);
        this.target = target;
        AB.showAP();
        AB.setEditTarget(target);
        if( target != null )target.setSelection(true);
        targetIsset = target != null;
    }

    Timer timer = new Timer(50, new ActionListener() {
        public void actionPerformed (ActionEvent e) {repaint();}
    });
    private class MindMapMouseMotionListener extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e){ }
        public void mouseDragged(MouseEvent e){
            if(target != null ){
                int x = e.getXOnScreen() - getLocationOnScreen().x;
                int y = e.getYOnScreen() - getLocationOnScreen().y;
                x -= target.getWidth()/2;
                y -= target.getHeight()/2;
                target.setX(x);
                target.setY(y);
                cursorPointer = true;
            }
        }
    }

    private class MindMapMouseListener extends MouseAdapter{
        public void mousePressed(MouseEvent e) {
            if( !targetIsset && target != null ){
                target.setSelection(false);
                target = null;
                AB.hideAP();
                AB.setEditTarget(null);
            }
            targetIsset =false;
        }
        public void mouseReleased(MouseEvent e){

        }
    }

    public void setCursorPointer(boolean t ){ this.cursorPointer = t; }

}