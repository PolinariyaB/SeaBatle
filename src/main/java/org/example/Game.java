package org.example;

import javax.swing.*;
import java.awt.*;

public class Game {
    JFrame frame;
    public static Cell[][] temp;
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
        Field fieldUser = new Field(frame, this.fieldGame);
        JPanel userPanel = fieldUser.placeOldField();
        fieldUser.makeFieldNotRed();
        temp = fieldUser.fieldGame;
        userPanel.setEnabled(false);

        Field fieldRobot = new Field(frame);
        JPanel robotPanel = fieldRobot.setStartField();
        RobotField robotField = new RobotField(frame);
        fieldRobot.fieldGame = robotField.example(fieldRobot, fieldUser.fieldGame);
        frame.add(userPanel);
        frame.add(robotPanel);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "Ваш ход");

    }
}
