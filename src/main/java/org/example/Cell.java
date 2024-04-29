package org.example;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JPanel implements MouseListener {
    private boolean isShip;
    private boolean partOfShip;
    private Color color;
    private int row;
    private int col;
    private boolean hasShip;
    private boolean isShot;
    private boolean click;
    private Ship ship;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isShip = false;
        this.partOfShip=false;
        this.isShot = false;
        this.click = false;
        this.color = Color.WHITE;
        setPreferredSize(new Dimension(30, 30));
        addMouseListener(this);
    }


    public boolean isShip() {
        return isShip;
    }
    public boolean partOfShip() {
        return partOfShip;
    }

    public boolean isShot() {
        return isShot;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void markAsHit() {//попал
        isShot = true;
        repaint();
    }
    public void madeAClick() {
        click = true;
        repaint();
    }
    public void paintCell(Color color) {
        setBackground(color);
        repaint();
    }


    public void markAsMiss() {//промазал
        isShot = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (click) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight()); // Заливаем цветом весь прямоугольник ячейки

        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isShip & !partOfShip) {
            Ship newShip = new Ship();
            this.setShip(newShip);
            madeAClick();

            // Окрашиваем текущую кнопку в серый цвет
            paintCell(Color.GRAY);

            // Окрашиваем соседние кнопки в красный цвет
            // Предположим, что у вас есть доступ к массиву клеток cells,
            // и вы хотите окрасить клетку с координатами (row+1, col) в красный цвет

            Cell[][] cell1 = new Cell[3][3];


            cell1[row+1][col] = new Cell(row+1,col);  // Создаем объект Cell и устанавливаем цвет в красный для клетки с координатами row+1, col
            cell1[row+1][col].setColor(Color.RED);   // Устанавливаем цвет объекта Cell в красный
            System.out.println(row+1);
            System.out.println(col);
        }
        // Выводим номера строки и столбца ячейки в консоль
        System.out.println("Нажата ячейка: " + row + ", " + col);
    }
    //1


    public void setShip(Ship ship) {
        this.ship = ship;
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