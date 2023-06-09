/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolitaireGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOpen extends JFrame {
    private JTextField nameField;
    
    public GameOpen() {
        super("User Name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());
        
        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        JButton playButton = new JButton("Play");
        JButton quitButton = new JButton("Quit");
        
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the program
            }
        });
        
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                if (userName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your name!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Hello, " + userName + "!");
                }
            }
        });
        
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);
        
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameOpen();
            }
        });
    }
}

