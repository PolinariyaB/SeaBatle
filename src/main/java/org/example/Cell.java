package org.example;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Cell implements MouseListener {

    public boolean click; //штука для того, чтобы чел не смог сам нажать на свое поле +
    //чтобы на чужом не жали дважды
    public boolean active; //нужна для робота, чтобы он жал на клетки пользователя, которые
    //еще не нажаты
    public boolean red;//красная или нет
    public boolean isShip;//корабль или часть корабля
    static public boolean finishPlace;//закончили расстановку кораблей или нет
    public JPanel panel;
    public Cell[] arr = new Cell[8]; //смежные клетки
    static private int count;
    public int row;
    public int col;
    public Cell adj;
    public JFrame frame;
    public Cell[][] fieldGame; //поле робота
    public Cell[][] fieldUser; //поле пользователя


    public Cell(int row1, int col1, JFrame frames, Cell[][] field) {
        panel = new JPanel();
        this.row = row1;
        this.col = col1;
        this.click = false;
        this.active = false;
        this.red = false;
        finishPlace = false;
        panel.setPreferredSize(new Dimension(30, 30));
        panel.addMouseListener(this);
        this.adj = null;
        this.frame = frames;
        this.fieldGame = field;
        this.isShip = false;
        this.fieldUser = null;
    }


    public void paintCell(Color color, JPanel panel1) {
        //раскраска в любой цвет
        panel1.setBackground(color);
        panel1.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!finishPlace) {
            //если расставляем корабли
            placeShips();
        }else {
            if (!click) {
                if (isShip) {
                    paintCell(Color.RED, panel);
                    red = true;
                } else {
                    paintCell(Color.BLACK, panel);
                }
                click = true;

                robotMotion();
                //JOptionPane.showMessageDialog(null, "Ваш ход");
            }
        }
    }

    public void robotMotion(){
        //удар робота
        Random random = new Random();
        int randomRow = random.nextInt(10);
        int randomCol = random.nextInt(10);
        Cell tempCell = fieldUser[randomRow][randomCol];
        while(tempCell.active){
            randomRow = random.nextInt(10);
            randomCol = random.nextInt(10);
            tempCell = fieldUser[randomRow][randomCol];
        }
        if (tempCell.isShip){
            paintCell(Color.RED, tempCell.panel);
            red = true;
        }else{
            paintCell(Color.BLACK, tempCell.panel);
        }
        tempCell.active = true;
    }

    public void placeShips(){
        //размещение кораблей
        if (!click && count < 4) { //единичные корабли
            paintGray();
            paintRedOne();
        } else if (!click && !active) {//первый щаг у составного корабля
            if (((count % 2 == 0 && count < 10) || (count % 3 == 1 && count >= 10 && count < 16) || (count % 4 == 0 && count >= 16 && count < 20))) {//первый шаг двойного
                paintGray();
                makeInactive();
            }
        } else if (!click)
            nextSteps();
        if (count == 4)
            JOptionPane.showMessageDialog(null, "Выберите 3 двухпалубных корабля");
        if (count == 10)
            JOptionPane.showMessageDialog(null, "Выберите 2 трехпалубных корабля");
        if (count == 16)
            JOptionPane.showMessageDialog(null, "Выберите 1 четырехпалубных корабля");
        if (count == 20) {
            Game window = new Game();
            window.displayStartScreen(frame, fieldGame);
            finishPlace = true;
            System.out.println(1);
        }
    }
    public void nextSteps(){
        if (count <= 10) {//2 шаг двойного
            paintGray();
            paintRedTwo();
        }
        else if (count <= 16 && count % 3 == 2){//2 шаг тройного
            paintGray();
            MakeInactiveThird();
        }
        else if (count <= 16 && count % 3 == 0){//3 шаг тройного
            paintGray();
            paintRedThird();
        }
        else if (count <= 20 && count % 4 == 1){//2 шаг четверного
            paintGray();
            MakeInactiveThird();
        }
        else if (count <= 20 && count % 4 == 2){//3 шаг четверного
            paintGray();
            madeFourActive();
        }
        else if (count <= 20 && count % 4 == 3){//4 шаг четверного
            paintGray();
            paintFour();
        }
    }

    public void paintFour(){
        Cell temp1 = finderAdjacentCell();
        adj = temp1;
        Cell temp2 = temp1.adj;
        Cell temp3 = temp2.adj;
        List<Cell> list3 = new ArrayList<>();
        if (temp3 != null) {
            list3.addAll(Arrays.asList(temp3.arr));
        }
        list3.addAll(Arrays.asList(arr));
        for (Cell c:list3){
            if (c!=null && !c.click) {
                c.click = true;
                c.paintCell(Color.RED, c.panel);
                c.red = true;
            }
        }
    }
    public void madeFourActive(){
        Cell temp = finderAdjacentCell();
        adj = temp;
        if (col == temp.col){
            if (row < temp.row){
                arr[3].active = true;
            }else{
                arr[4].active = true;
            }
        }
        if (row == temp.row){
            if (col < temp.col){
                arr[6].active = true;
            }else{
                arr[1].active = true;
            }
        }
    }
    public void paintGray(){
        paintCell(Color.GRAY, panel);
        click = true;
        isShip = true;
        count++;
    }

    public Cell finderAdjacentCell(){
        Cell temp = null;
        for (Cell pan: arr){
            if (pan != null && pan.click && !pan.red) {
                temp = pan;
                break;
            }
        }
        return temp;
    }

    public void paintRedThird(){
        Cell temp = finderAdjacentCell();
        List<Cell> list2 = new ArrayList<>();
        if (temp != null) {
            list2.addAll(Arrays.asList(temp.arr));
        }
        assert temp != null;
        Cell temp1 = temp.adj;
        adj = temp;
        List<Cell> list1 = new ArrayList<>(Arrays.asList(arr));
        list1.addAll(list2);
        List<Cell> list3 = new ArrayList<>();
        if (temp1 != null) {
            list3.addAll(Arrays.asList(temp1.arr));
        }
        list1.addAll(list3);
        for (Cell c:list1){
            if (c!=null && !c.click) {
                c.click = true;
                c.paintCell(Color.RED, c.panel);
                c.red = true;
            }
        }
    }
    public void paintRedOne(){
        for (Cell pan: arr){
            if (pan != null && !pan.click) {
                paintCell(Color.RED, pan.panel);
                pan.click = true;
                pan.red = true;
            }
        }
    }
    public void MakeInactiveThird(){
        Cell temp = finderAdjacentCell();
        adj = temp;
        temp.adj = this;
        if (col == temp.col){
            if (row < temp.row){
                arr[3].active = true;
                temp.arr[4].active = true;
            }else{
                arr[4].active = true;
                temp.arr[3].active = true;
            }
            temp.arr[6].active = false;
            temp.arr[1].active = false;
        }
        if (row == temp.row){
            if (col < temp.col){
                arr[6].active = true;
                temp.arr[1].active = true;
            }else{
                arr[1].active = true;
                temp.arr[6].active = true;
            }
            temp.arr[3].active = false;
            temp.arr[4].active = false;
        }
    }

    public void paintRedTwo(){
        Cell temp = finderAdjacentCell();
        List<Cell> list2 = new ArrayList<>();
        if (temp != null) {
            list2.addAll(Arrays.asList(temp.arr));
        }
        adj = temp;
        List<Cell> list1 = new ArrayList<>(Arrays.asList(arr));
        list1.addAll(list2);
        for (Cell c:list1){
            if (c!=null && !c.click) {
                c.click = true;
                c.paintCell(Color.RED, c.panel);
                c.red = true;
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