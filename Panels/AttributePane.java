package Panels;

import Components.BlueButton;
import Components.DarkLabel;
import Components.WhiteTextField;
import Configs.Colors.ColorSwitch;
import Configs.Colors.NodeColor;
import Configs.Common;
import DataStructures.JSONNode;
import Main.ProgramFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AttributePane extends JPanel{
    ProgramFrame parent = null;
    private Attribute attr;
    private JScrollPane scroll;
    public AttributePane(int x, int y, String str) {
        setLayout(null);
        setBounds(0, 0, x, y);
        setBackground(ColorSwitch.init(ColorSwitch.BRIGHT));

        DarkLabel label = new DarkLabel(str);
        label.setSize(getWidth(), getHeight()/20);
        label.setBackground(ColorSwitch.init(ColorSwitch.DARK));
        label.setBorder(new EmptyBorder(0,0,0,0));
        add(label);

        attr = new Attribute(0,0, x, y - y/20);
        scroll = new JScrollPane(attr);
        scroll.setLayout(new ScrollPaneLayout() );
        scroll.getViewport().setBounds(0,y/20 ,x,y - 3*y/20);
        scroll.setBorder(new EmptyBorder(0,0,0,0));
        scroll.getViewport().setBackground(ColorSwitch.init(ColorSwitch.BRIGHT));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension( 13, Integer.MAX_VALUE));
        scroll.getVerticalScrollBar().setUI(Common.DefaultScrollBarUI());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVisible(false);
        add(scroll);

        BlueButton applicationBtn = new BlueButton("º¯°æÇÏ±â");
        applicationBtn.setBounds(0,y - 2*y/20, x, 2*y/20);
        add(applicationBtn);

        setBorder(new EmptyBorder(0,0,0,0));
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                label.setBounds(0,0,size.width, label.getHeight());
                scroll.setBounds(0,label.getHeight(), size.width, size.height - label.getHeight() * 3);
                scroll.getViewport().setBounds(0,0 ,x,Integer.MAX_VALUE);
                attr.setBounds(0,0, size.width, size.height - label.getHeight() * 3 > 50 + 90 * 7 ? size.height - label.getHeight() * 3  : 50 + 90 * 7);
                applicationBtn.setBounds(0,size.height - label.getHeight()*2,size.width, 2*label.getHeight());
            }
        });
        applicationBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                attr.applyAttr();
            }
        });
    }
    public void setParent(ProgramFrame frame){ parent = frame; attr.setFrame(this.parent); }
    public JFrame getParent(){return parent;}
    public void showAP(){ scroll.setVisible(true);}
    public void hideAP(){ scroll.setVisible(false); }
    public void setText(){ attr.setText(); }
    public void setEditTarget(JSONNode target){
        attr.setEditTarget(target);
    }

    public void clear(){ attr.clear(); scroll.setVisible(false);}
}

class Attribute extends JPanel{
    private JSONNode editTarget;
    private DarkLabel[] labelArr;
    private WhiteTextField[] whitefield;
    private ProgramFrame frame = null;
    public void clear(){
        editTarget = null;
        for(WhiteTextField f : whitefield ){
            f.setText("");
        }
    }
    public Attribute(int x, int y, int width, int height) {
        setLayout(null);
        setBounds(x,y,width, 50 + 90 * 7 > height ? 50 + 90 * 7 : height );

        setPreferredSize(new Dimension(width, 50 + 90 * 7 > height ? 50 + 90 * 7 : height ));
        setBackground(ColorSwitch.init(ColorSwitch.BRIGHT));
        setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 10));
        setBorder(new EmptyBorder(0,0,0,0));
        setForeground(Color.WHITE);

        // by KH

        labelArr = new DarkLabel[7];
        labelArr[0]= new DarkLabel("TEXT");
        labelArr[1] = new DarkLabel("X");
        labelArr[2] = new DarkLabel("Y");
        labelArr[3] = new DarkLabel("WIDTH");
        labelArr[4] = new DarkLabel("HEIGHT");
        labelArr[5] = new DarkLabel("COLOR");
        labelArr[6] = new DarkLabel("TEXT COLOR");

        whitefield = new WhiteTextField[7];
        for (int i = 0; i < 7; i++) {
            labelArr[i].setSize(getWidth() / 3, getHeight() / 20);
            labelArr[i].setBackground( ColorSwitch.init(ColorSwitch.BRIGHT));
            labelArr[i].setForeground(ColorSwitch.init(ColorSwitch.BRIGHTFONT));
            labelArr[i].setLocation(30,  50 + i * 90);
            add(labelArr[i]);

            whitefield[i] = new WhiteTextField(getHeight()/20);
            if( i == 0 ){
                whitefield[i].setEditable(false);
                whitefield[i].setEnabled(false);
            }
            whitefield[i].setSize(getWidth() / 3, getHeight() / 20);
            whitefield[i].setBackground(ColorSwitch.init(ColorSwitch.BRIGHTTEST));
            whitefield[i].setForeground(ColorSwitch.init(ColorSwitch.BRIGHTFONT));
            whitefield[i].setCaretColor(ColorSwitch.init(ColorSwitch.BRIGHTFONT));
            whitefield[i].setLocation(160, 50 + 90 * i);
            add(whitefield[i]);
        }

        setVisible(true);
    }
    public void setText(){
        if( editTarget == null ) return;
        whitefield[0].setText(editTarget.getData()); // text
        whitefield[1].setText(String.valueOf(editTarget.getX()) ); // x
        whitefield[2].setText(String.valueOf(editTarget.getY())); // y
        whitefield[3].setText(String.valueOf(editTarget.getWidth())); // width
        whitefield[4].setText(String.valueOf(editTarget.getHeight())); // height
        if( editTarget.getColor().trim().equals("") ) {
            String hexColour = Integer.toHexString(NodeColor.init(editTarget.getLevel()).getRGB() & 0xffffff);
            if (hexColour.length() < 6) {
                hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
            }
            StringBuilder sb = new StringBuilder(hexColour);
            sb.insert(0,'#');
            whitefield[5].setText(sb.toString()); // color
        }
        else whitefield[5].setText(editTarget.getColor()); // color

        if( editTarget.getColor().trim().equals("") ) {
            String hexColour = Integer.toHexString(Color.BLACK.getRGB() & 0xffffff);
            if (hexColour.length() < 6) {
                hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
            }
            StringBuilder sb = new StringBuilder(hexColour);
            sb.insert(0,'#');
            whitefield[6].setText(sb.toString()); // color
        }
        else whitefield[6].setText(editTarget.getTextColor()); // textColor
    }
    public void setEditTarget(JSONNode target){
        this.editTarget = target;
        setText();
    }

    public void applyAttr(){
        String text, color, textColor; // text
        if( editTarget != null ){
            boolean isChanged = false;
//            if( !(text = whitefield[0].getText().trim()).equals("")){
//                editTarget.setData(text);
//                isChanged =true;
//            }
            if( !(color = whitefield[5].getText().trim()).equals("")){
                editTarget.setColor(color);
                isChanged =true;
            }
            if( !(textColor = whitefield[6].getText().trim()).equals("")){
                editTarget.setTextColor(textColor);
                isChanged =true;
            }
            for( int i = 1; i <= 4; i++) {
                if (!whitefield[i].getText().trim().equals("")) {
                    try {
                        int num = Integer.parseInt(whitefield[i].getText().trim()); // x
                        switch (i) {
                            case 1:
                                editTarget.setX(num);
                                break;
                            case 2:
                                editTarget.setY(num);
                                break;
                            case 3:
                                editTarget.setWidth(num);
                                break;
                            case 4:
                                editTarget.setHeight(num);
                                break;
                            default:
                        }
                        isChanged = true;
                    } catch (Exception e) {
                        System.out.println(e.getStackTrace());
                    }
                }
            }
            frame.setModified(isChanged);
        }
    }

    public void setFrame(ProgramFrame frame){ this.frame = frame;}
}




