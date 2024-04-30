package org.example;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell implements MouseListener {

    private boolean click;
    private boolean active;
    private boolean aaa;
    public JPanel panel;

    public Cell[] arr = new Cell[8];
    static private int count;
    public int row;
    public int col;

    public Cell(int row1, int col1) {
        panel = new JPanel();
        this.row = row1;
        this.col = col1;
        this.click = false;
        this.active = false;
        this.aaa = false;
        panel.setPreferredSize(new Dimension(30, 30));
        panel.addMouseListener(this);
    }


    public void paintCell(Color color, JPanel panel1) {
        panel1.setBackground(color);
        panel1.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!click && count < 4) {
            click = true;
            paintCell(Color.GRAY, panel);
            paintRedOne();
            count++;
        }
        else if(!click && !active){
            if (count % 2 == 0){
                click = true;
                paintCell(Color.GRAY, panel);
                makeInactive();
                count++;
            }
        }
        else if(!click){
            paintCell(Color.GRAY, panel);
            click = true;
            count++;
            paintRedTwo();
        }
        if (count == 4){
            JOptionPane.showMessageDialog(null, "Выберите 3 двухпалубных корабля");
        }
    }

    public void paintRedTwo(){
        Cell temp = null;
        for (Cell pan: arr){
            if (pan != null && pan.click) {
                temp = pan;
            }
        }
        List<Cell> list2 = new ArrayList<>();
        if (temp != null) {
            list2.addAll(Arrays.asList(temp.arr));
        }
        List<Cell> list1 = new ArrayList<>(Arrays.asList(arr));
        list1.addAll(list2);
        for (Cell c:list1){
            if (c!=null && !c.click)
                c.paintCell(Color.RED, c.panel);
        }
    }

    public void paintRedOne(){
        for (Cell pan: arr){
            if (pan != null) {
                paintCell(Color.RED, pan.panel);
                pan.click = true;
            }
        }
    }
    public void makeInactive(){
        if (arr[1] != null)
            arr[1].active = true;
        if (arr[3] != null)
            arr[3].active = true;
        if (arr[4] != null)
            arr[4].active = true;
        if (arr[6] != null)
            arr[6].active = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}