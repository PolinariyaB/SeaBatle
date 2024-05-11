package org.example;

import javax.swing.*;
import java.awt.*;

public class Field {
    public Cell[][] a = new Cell[10][10];
    JFrame frames;
    public Field(JFrame frame){
        this.frames = frame;
    }
    public Field(JFrame frame, Cell[][] arr){
        this.frames = frame;
        this.a = arr;
    }
    public void SetStartField(JPanel gridPanel){
        //размещение пустого поля(бумажки)
        for (int row = 0; row < 10; row++) {
            a[row] = new Cell[10];
            for (int col = 0; col < 10; col++) {
                Cell cell = new Cell(row, col, frames, this.a);
                cell.panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                gridPanel.add(cell.panel);
                a[row][col] = cell;
            }
        }
        countArr();
    }

    public void countArr(){
        //подсчет говна
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (row != 0) {
                    if (col != 0) {
                        a[row][col].arr[5] = a[row - 1][col - 1];
                    }
                    if (col != 9){
                        a[row][col].arr[0] = a[row - 1][col + 1];
                    }
                    a[row][col].arr[3] = a[row - 1][col];
                }
                if (row != 9) {
                    if (col != 0)
                        a[row][col].arr[7] = a[row + 1][col - 1];
                    a[row][col].arr[4] = a[row + 1][col];
                    if (col != 9)
                        a[row][col].arr[2] = a[row + 1][col + 1];
                }
                if (col != 0){
                    a[row][col].arr[6] = a[row][col - 1];
                }
                if (col != 9) {
                    a[row][col].arr[1] = a[row][col + 1];
                }
            }
        }
    }
    public void placeOldField(JPanel gridPanel){
        //размещение поля, которое уже было раскрашено
        //в нашем случае это поле пользователя, чтобы он его видел
        for (Cell[] cells: a){
            for (Cell cell:cells){
                cell.panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                gridPanel.add(cell.panel);
                cell.click = true;
                cell.active = false;
            }
        }
    }
    public void makeFieldNotRed(){
        //убираем красные границы
        for (Cell[] cells: a){
            for (Cell cell:cells){
                if (cell.red){
                    cell.red = false;
                    cell.paintCell(Color.WHITE, cell.panel);
                }
            }
        }
    }
}
