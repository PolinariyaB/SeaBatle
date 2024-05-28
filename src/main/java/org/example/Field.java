package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field {
    public Cell[][] fieldGame = new Cell[10][10];
    JFrame frameBind;
    JPanel gridPanel;
    private boolean finishPlacingShips = false;
    private int countPlaceCells = 0;
    LogicCell logicCell = new LogicCell();
    public Field(JFrame frame){
        this.frameBind = frame;
        gridPanel  = new JPanel(new GridLayout(10, 10));
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseExecute(e);
            }
        });
    }
    public Field(JFrame frame, Cell[][] arr){
        this.frameBind = frame;
        this.fieldGame = arr;
        gridPanel = new JPanel(new GridLayout(10, 10));
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseExecute(e);
            }

        });
    }
    public void mouseExecute(MouseEvent e){
        for (Cell[] component : fieldGame) {
            for (Cell comp : component) {
                if (comp.getPanelCell().getBounds().contains(e.getPoint())) {
                    System.out.println(e.getPoint());
                    if (!finishPlacingShips) {
                        placeShips(comp);
                        break;
                    }else {
                        logicCell.playerMotion(comp);
                    }

                }
            }
        }
    }
    public void placeShips(Cell componentCell){
        if (!componentCell.getClickRobotKey() && countPlaceCells < 4) {
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.paintRedOne(componentCell);
        } else if (!componentCell.getClickRobotKey() && !componentCell.getActiveKey()) {
            if (((countPlaceCells % 2 == 0 && countPlaceCells < 10) ||
                    (countPlaceCells % 3 == 1 && countPlaceCells >= 10 && countPlaceCells < 16) ||
                    (countPlaceCells % 4 == 0 && countPlaceCells >= 16 && countPlaceCells < 20))) {
                countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
               logicCell.makeInactive(componentCell);
            }
        } else if (!componentCell.getClickRobotKey())
            nextSteps(componentCell);
        if (countPlaceCells == 4)
            JOptionPane.showMessageDialog(null, "Выберите 3 двухпалубных корабля");
        if (countPlaceCells == 10)
            JOptionPane.showMessageDialog(null, "Выберите 2 трехпалубных корабля");
        if (countPlaceCells == 16)
            JOptionPane.showMessageDialog(null, "Выберите 1 четырехпалубных корабля");
        if (countPlaceCells == 20) {
            Game window = new Game();
            window.displayStartScreen(frameBind, fieldGame);
            finishPlacingShips = true;
            System.out.println(1);
        }
    }

    public void nextSteps(Cell componentCell){
        if (countPlaceCells <= 10) {
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.paintRedTwo(componentCell);
        }
        else if (countPlaceCells <= 16 && countPlaceCells % 3 == 2){
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.makeInactiveThird(componentCell);
        }
        else if (countPlaceCells <= 16 && countPlaceCells % 3 == 1){
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.paintRedThird(componentCell);
        }
        else if (countPlaceCells <= 20 && countPlaceCells % 4 == 1){
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.makeInactiveThird(componentCell);
        }
        else if (countPlaceCells <= 20 && countPlaceCells % 4 == 2){
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.madeFourActive(componentCell);
        }
        else if (countPlaceCells <= 20 && countPlaceCells % 4 == 3){
            countPlaceCells = logicCell.paintGray(componentCell, countPlaceCells);
            logicCell.paintFour(componentCell);
        }
    }

    public JPanel setStartField(){
        for (int row = 0; row < 10; row++) {
            fieldGame[row] = new Cell[10];
            for (int col = 0; col < 10; col++) {
                Cell cell = new Cell(row, col);
                cell.setPanelCell();
                gridPanel.add(cell.getPanelCell());
                fieldGame[row][col] = cell;
            }
        }
        countArr();
        return gridPanel;
    }


    public void countArr(){
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Cell cell = fieldGame[row][col];
                if (row != 0) {
                    if (col != 0)
                        cell.setAdjacentCell(5, fieldGame[row - 1][col - 1]);
                    if (col != 9)
                        cell.setAdjacentCell(0, fieldGame[row - 1][col + 1]);
                    cell.setAdjacentCell(3, fieldGame[row - 1][col]);
                }
                if (row != 9) {
                    if (col != 0)
                        cell.setAdjacentCell(7, fieldGame[row + 1][col - 1]);
                    cell.setAdjacentCell(4, fieldGame[row + 1][col]);
                    if (col != 9)
                        cell.setAdjacentCell(2, fieldGame[row + 1][col + 1]);
                }
                if (col != 0)
                    cell.setAdjacentCell(6, fieldGame[row][col - 1]);
                if (col != 9)
                    cell.setAdjacentCell(1, fieldGame[row][col + 1]);
            }
        }
    }
    public JPanel placeOldField(){
        for (Cell[] cells: fieldGame){
            for (Cell cell:cells){
                JPanel panel = cell.setPanelCell();
                gridPanel.add(panel);
                cell.setClickRobotKey(true);
                cell.setActiveUserKey(false);
            }
        }
        return gridPanel;
    }
    public void makeFieldNotRed(){
        for (Cell[] cells: fieldGame){
            for (Cell cell:cells){
                if (cell.getRedKey()){
                    cell.setRedKey(true);
                    cell.paintCell(Color.WHITE);
                }
            }
        }
    }
}
