/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolitaireGUI;

/**
 *
 * @author Trey Baker
 */
import javax.swing.*;

import java.util.ArrayList;

public class GameWin {
    private static ArrayList<String> highScores = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Congratulations");
        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Congratulations, you have won! Please enter your name:");
        JTextField textField = new JTextField(15);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            String name = textField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                highScores.add(name);
                StringBuilder message = new StringBuilder("Congratulations, " + name + "!\n\nHigh Scores:\n");
                for (int i = 0; i < highScores.size(); i++) {
                    message.append(i + 1).append(". ").append(highScores.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(frame, message.toString());
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(submitButton);
        frame.add(panel);

        frame.setVisible(true);
    }
}

