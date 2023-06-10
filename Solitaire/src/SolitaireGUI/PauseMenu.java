/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolitaireGUI;

/**
 *
 * @author Trey Baker [21155292]
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JFrame {
    private GamePanel gamePanel;

    public PauseMenu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        setTitle("Pause Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the PauseMenu frame, not the entire application
        setSize(300, 200);
        setLayout(new FlowLayout());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                gamePanel.resetGame();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quit the entire application
            }
        });

        JButton saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle save and quit button action
                System.out.println("Save and Quit button clicked");
                System.exit(0); // Quit the entire application
            }
        });

        add(resetButton);
        add(quitButton);
        add(saveAndQuitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PauseMenu(null); // Pass the GamePanel instance to the PauseMenu constructor
            }
        });
    }
}
