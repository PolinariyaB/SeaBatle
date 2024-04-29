package org.example;

import javax.swing.*;
import java.awt.*;

public class Field {
    public Cell[][] a = new Cell[10][10];
    public void SetStartField(JPanel gridPanel){
        for (int row = 0; row < 10; row++) {
            a[row] = new Cell[10];
            for (int col = 0; col < 10; col++) {
                Cell cell = new Cell(row, col);
                cell.panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                gridPanel.add(cell.panel);
                a[row][col] = cell;

            }
        }
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
}
