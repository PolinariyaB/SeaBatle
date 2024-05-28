package org.example;

import javax.swing.*;

public class RobotField {
    JFrame frames;
    public RobotField(JFrame frame){
        this.frames = frame;
    }
    public Cell[][] example(Field field, Cell[][] userField){
        //шаблон поля робота
        field.fieldGame[0][1].setShip(true);
        field.fieldGame[0][2].setShip(true);
        field.fieldGame[1][4].setShip(true);
        field.fieldGame[1][5].setShip(true);
        field.fieldGame[1][6].setShip(true);
        field.fieldGame[1][9].setShip(true);
        field.fieldGame[3][2].setShip(true);
        field.fieldGame[3][4].setShip(true);
        field.fieldGame[3][9].setShip(true);
        field.fieldGame[4][2].setShip(true);
        field.fieldGame[4][7].setShip(true);
        field.fieldGame[4][9].setShip(true);
        field.fieldGame[5][7].setShip(true);
        field.fieldGame[5][9].setShip(true);
        field.fieldGame[6][9].setShip(true);
        field.fieldGame[7][3].setShip(true);
        field.fieldGame[7][4].setShip(true);
        field.fieldGame[7][5].setShip(true);
        field.fieldGame[8][0].setShip(true);
        field.fieldGame[8][8].setShip(true);
        field.countArr();
        return field.fieldGame;
    }
}
