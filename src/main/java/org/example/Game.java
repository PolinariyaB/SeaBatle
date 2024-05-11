package org.example;

import javax.swing.*;
import java.awt.*;

public class Game {

    JFrame frame;
    Cell[][] fieldGame;
    public void displayStartScreen(JFrame oldFrame, Cell[][] field) {
        oldFrame.dispose();
        frame = new JFrame("Игра");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel contentPane = new MyPanel("src/main/resources/menu.jpg");
        frame.setSize(1180, 700);
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        this.fieldGame = field;
        setFields();
    }
    public void setFields(){
        JPanel gridPanelUser = new JPanel(new GridLayout(10, 10));
        Field fieldUser = new Field(frame, this.fieldGame);
        fieldUser.placeOldField(gridPanelUser);
        fieldUser.makeFieldNotRed();
        gridPanelUser.setEnabled(false);

        JPanel gridPanelRobot = new JPanel(new GridLayout(10, 10));
        Field fieldRobot = new Field(frame);
        fieldRobot.SetStartField(gridPanelRobot);

        frame.add(gridPanelUser);
        frame.add(gridPanelRobot);
        frame.setVisible(true);
    }
}
