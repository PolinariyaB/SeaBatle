package org.example;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JPanel implements MouseListener {
    private int row;
    private int col;
    private boolean hasShip;
    private boolean isShot;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasShip = false;
        this.isShot = false;
        setPreferredSize(new Dimension(30, 30));
        addMouseListener(this);
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public boolean isShot() {
        return isShot;
    }

    public void markAsHit() {//попал
        isShot = true;
        repaint();
    }

    public void markAsMiss() {//промазал
        isShot = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isShot) {
            if (hasShip) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.GRAY);
            }
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
        if (!isShot) {
            // Проверка, что ячейка еще не была атакована
            // Добавьте здесь логику обработки попадания или промаха
            // Например:
            if (hasShip) {
                markAsHit();
            } else {
                markAsMiss();
            }
            // Выводим номера строки и столбца ячейки в консоль
            System.out.println("Нажата ячейка: " + row + ", " + col);
        }
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