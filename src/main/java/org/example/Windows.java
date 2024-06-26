package org.example;

import javax.swing.*;
import java.awt.*;

public class Windows { //сериализация
    String name = "";

    public void displayStartScreen() {
        JFrame frame = makeWindow("Привет", "src/main/resources/firstScreen.jpg");
        createStartButton(frame);
        frame.setVisible(true);
    }

    private GridBagConstraints gbcReturn(int top, int left, int bottom, int right) {
        //координаты для кнопок
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }

    private JButton createPictureButton(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(350, 120, Image.SCALE_DEFAULT);
        icon = new ImageIcon(newImage);
        JButton button = new JButton(icon);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }

    private void createStartButton(JFrame frame) {
        JButton button = createPictureButton("src/main/resources/startButton.jpg");
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = gbcReturn(400, 150, 150, 150);
        button.addActionListener(e -> windowName(frame));
        panel.add(button, gbc);
        frame.add(panel);
    }

    private void windowName(JFrame oldFrame) {
        JFrame frame = makeWindow("Введите имя", "src/main/resources/enterName.jpg");
        oldFrame.dispose();
        JTextField nameField = new JTextField(15);
        nameField.addActionListener(e -> {
            name = nameField.getText();
            System.out.println("Имя: " + name);
            menu(frame);
        });
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = gbcReturn(400, 150, 150, 150);
        panel.add(nameField, gbc);
        frame.add(panel);
        frame.setVisible(true);
    }
    private void menu(JFrame oldFrame) {
        JFrame frame = makeWindow("менюшка", "src/main/resources/menu.jpg");
        oldFrame.dispose();
        createGameButton(frame);
        frame.setVisible(true);
    }
    private void createGameButton(JFrame frame) {
        JButton button = createPictureButton("src/main/resources/gameButton.jpeg");
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = gbcReturn(400, 150, 150, 150);
        button.addActionListener(e -> playerField(frame));
        panel.add(button, gbc);
        frame.add(panel);
    }

    private static void playerField(JFrame oldFrame) {
        //начало игры
        JFrame frame = makeWindow("бумажка", "src/main/resources/paper.jpeg");
        oldFrame.dispose();

        JPanel topPanel = new JPanel(); // Пустая панель для отступа сверху
        frame.add(topPanel);

        Field fieldUser = new Field(frame);
        JPanel gridPanelUser = fieldUser.setStartField();

        frame.add(gridPanelUser);
        frame.setVisible(true);

        firstHint();
    }

    private static void firstHint() {
        JOptionPane.showMessageDialog(null, "Выберите 4 однопалубных корабля");
    }

    public static JFrame makeWindow(String text, String path) {
        JFrame frame = new JFrame(text);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel contentPane = new MyPanel(path);
        frame.setSize(1180, 700);
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        return frame;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Windows guiTest = new Windows();
            guiTest.displayStartScreen();
        });
    }
}