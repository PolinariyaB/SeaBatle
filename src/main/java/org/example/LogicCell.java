package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class LogicCell {
    public int paintGray(Cell componentCell, int countPlaceCells){
        componentCell.paintCell(Color.GRAY);
        componentCell.setClickRobotKey(true);
        componentCell.setShip(true);
        countPlaceCells++;
        return countPlaceCells;
    }
    public void paintRedOne(Cell componentCell){
        Cell[] arrCells = componentCell.getAdjacentCell();
        for (Cell pan: arrCells){
            if (pan != null && !pan.getClickRobotKey()) {
                pan.paintCell(Color.RED);
                pan.setClickRobotKey(true);
                pan.setRedKey(true);
            }
        }
    }
    public void makeInactive(Cell componentCell){
        Cell[] adjacentCells = componentCell.getAdjacentCell();
        if (adjacentCells[1] != null)
            adjacentCells[1].setActiveKey(true);
        if (adjacentCells[3] != null)
            adjacentCells[3].setActiveKey(true);
        if (adjacentCells[4] != null)
            adjacentCells[4].setActiveKey(true);
        if (adjacentCells[6] != null)
            adjacentCells[6].setActiveKey(true);
    }
    public void paintRedTwo(Cell componentCell){
        Cell temp = componentCell.finderAdjacentCell();
        List<Cell> list2 = new ArrayList<>();
        if (temp != null) {
            list2.addAll(Arrays.asList(temp.getAdjacentCell()));
        }
        componentCell.setAdjacent(temp);
        assert temp != null;
        List<Cell> list1 = new ArrayList<>(Arrays.asList(componentCell.getAdjacentCell()));
        list1.addAll(list2);
        for (Cell c:list1){
            if (c!=null && !c.getClickRobotKey()) {
                c.setClickRobotKey(true);
                c.paintCell(Color.RED);
                c.setRedKey(true);
            }
        }
    }
    public void makeInactiveThird(Cell componentCell){
        Cell temp = componentCell.finderAdjacentCell();
        componentCell.setAdjacent(temp);
        temp.setAdjacent(componentCell);
        Cell[] arr = componentCell.getAdjacentCell();
        Cell[] tempArr = temp.getAdjacentCell();
        if (componentCell.col == temp.col){
            if (componentCell.row < temp.row){
                arr[3].setActiveKey(true);
                tempArr[4].setActiveKey(true);
            }else{
                arr[4].setActiveKey(true);
                tempArr[3].setActiveKey(true);
            }
            tempArr[6].setActiveKey(false);
            tempArr[1].setActiveKey(false);
        }
        if (componentCell.row == temp.row){
            if (componentCell.col < temp.col){
                arr[6].setActiveKey(true);
                tempArr[1].setActiveKey(true);
            }else{
                arr[1].setActiveKey(true);
                tempArr[6].setActiveKey(true);
            }
            tempArr[3].setActiveKey(false);
            tempArr[4].setActiveKey(false);
        }
    }
    public void paintRedThird(Cell componentCell){
        Cell temp = componentCell.finderAdjacentCell();
        Cell[] tempArr = temp.getAdjacentCell();
        List<Cell> list2 = new ArrayList<>(Arrays.asList(tempArr));
        Cell temp1 = temp.getAdjacent();
        componentCell.setAdjacent(temp);
        Cell[] componentArr = componentCell.getAdjacentCell();
        List<Cell> list1 = new ArrayList<>(Arrays.asList(componentArr));
        list1.addAll(list2);
        List<Cell> list3 = new ArrayList<>();
        if (temp1 != null) {
            list3.addAll(Arrays.asList(temp1.getAdjacentCell()));
        }
        list1.addAll(list3);
        for (Cell c:list1){
            if (c!=null && !c.getClickRobotKey()) {
                c.setClickRobotKey(true);
                c.paintCell(Color.RED);
                c.setRedKey(true);
            }
        }
    }
    public void madeFourActive(Cell componentCell){
        Cell temp = componentCell.finderAdjacentCell();
        componentCell.setAdjacent(temp);
        Cell[] arr = componentCell.getAdjacentCell();
        if (componentCell.col == temp.col){
            if (componentCell.row < temp.row){
                arr[3].setActiveKey(true);
            }else{
                arr[4].setActiveKey(true);
            }
        }
        if (componentCell.row == temp.row){
            if (componentCell.col < temp.col){
                arr[6].setActiveKey(true);
            }else{
                arr[1].setActiveKey(true);
            }
        }
    }
    public void paintFour(Cell componentCell){
        Cell temp1 = componentCell.finderAdjacentCell();
        componentCell.setAdjacent(temp1);
        Cell temp2 = temp1.getAdjacent();
        Cell temp3 = temp2.getAdjacent();
        List<Cell> list3 = new ArrayList<>();
        if (temp3 != null) {
            list3.addAll(Arrays.asList(temp3.getAdjacentCell()));
        }
        list3.addAll(Arrays.asList(componentCell.getAdjacentCell()));
        for (Cell c:list3){
            if (c!=null && !c.getClickRobotKey()) {
                c.setClickRobotKey(true);
                c.paintCell(Color.RED);
                c.setRedKey(true);
            }
        }
    }
    public void playerMotion(Cell componentCell){
        if (!componentCell.getClickRobotKey()) {
            if (componentCell.getShip()) {
                componentCell.paintImage("src/main/resources/wrong.png");
               componentCell.setRedKey(true);
            } else {
                componentCell.paintImage("src/main/resources/dot.png");
                startRobotTimer();
            }
            componentCell.setClickRobotKey(true);
        }
    }
    public void startRobotTimer() {
        Timer timer = new Timer(1000, e -> robotMotion());
        timer.setRepeats(false);
        timer.start();
    }
    public void robotMotion(){

    }
}
