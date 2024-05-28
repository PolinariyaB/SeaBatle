package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Cell{

    private boolean clickRobotKey = false;
    private boolean activeUserKey = false;
    private boolean redKey = false;
    private boolean isShip = false;
    private final JPanel panelCell;
    private final Cell[] adjacentCells = new Cell[8];
    public int row;
    public int col;
    private Cell adjacentCell = null;

    public boolean getShip(){
        return isShip;
    }
    public Cell getAdjacent(){
        return adjacentCell;
    }

    public void setAdjacent(Cell adj){
        adjacentCell = adj;
    }

    public void setActiveKey(boolean isActive){
        activeUserKey = isActive;
    }

    public boolean getActiveKey(){
        return activeUserKey;
    }

    public Cell(int rowCell, int colCell) {
        panelCell = new JPanel();
        this.row = rowCell;
        this.col = colCell;
        panelCell.setPreferredSize(new Dimension(30, 30));
    }

    public void setAdjacentCell(int index, Cell adjacentCell){
        adjacentCells[index] = adjacentCell;
    }
    public Cell[] getAdjacentCell(){
        return adjacentCells;
    }

    public JPanel setPanelCell(){
        panelCell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        return panelCell;
    }

    public void setActiveUserKey(boolean isActive){
        activeUserKey = isActive;
    }

    public void setClickRobotKey(boolean isRobotKey){
        clickRobotKey = isRobotKey;
    }

    public boolean getRedKey(){
        return redKey;
    }

    public void setRedKey(boolean isRedKey){
        redKey = isRedKey;
    }

    public JPanel getPanelCell(){
        return panelCell;
    }
    public void setShip(boolean isShipCell){
        isShip = isShipCell;
    }

    public boolean getClickRobotKey(){
        return clickRobotKey;
    }


    public void paintCell(Color color) {
        panelCell.setBackground(color);
        panelCell.repaint();
    }

    public void paintImage(String path){
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(30, 30, Image.SCALE_FAST)));
        panelCell.add(picLabel);
        panelCell.validate();
    }


    public Cell finderAdjacentCell(){
        Cell temp = null;
        for (Cell pan: adjacentCells){
            if (pan != null && pan.clickRobotKey && !pan.redKey) {
                temp = pan;
                break;
            }
        }
        return temp;
    }

}
