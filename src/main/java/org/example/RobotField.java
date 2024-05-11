package org.example;

import javax.swing.*;

public class RobotField {
    JFrame frames;
    public RobotField(JFrame frame){
        this.frames = frame;
    }
    public Cell[][] example(Field field, Cell[][] userField){
        //шаблон поля робота
        field.a[0][1].isShip = true;
        field.a[0][2].isShip = true;
        field.a[1][4].isShip = true;
        field.a[1][5].isShip = true;
        field.a[1][6].isShip = true;
        field.a[1][9].isShip = true;
        field.a[3][2].isShip = true;
        field.a[3][4].isShip = true;
        field.a[3][9].isShip = true;
        field.a[4][2].isShip = true;
        field.a[4][7].isShip = true;
        field.a[4][9].isShip = true;
        field.a[5][7].isShip = true;
        field.a[5][9].isShip = true;
        field.a[6][9].isShip = true;
        field.a[7][3].isShip = true;
        field.a[7][4].isShip = true;
        field.a[7][5].isShip = true;
        field.a[8][0].isShip = true;
        field.a[8][8].isShip = true;
        field.countArr();
        for (Cell[] cells: field.a){
            for (Cell cell:cells){
                cell.fieldGame = field.a;
                cell.fieldUser = userField;
            }
        }
        return field.a;
    }
}
