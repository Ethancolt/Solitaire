/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolitaireGUI;

/**
 *
 * @author Itstr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWin extends JPanel {
    private JFrame newWindow;

    public GameWin() {
        setLayout(new BorderLayout());
        askForName();
    }

    private void askForName() {
        String name = JOptionPane.showInputDialog("You have won! Please enter your name:");
        if (name != null && !name.isEmpty()) {
            showNewWindow(name);
        } else {
            JOptionPane.showMessageDialog(GameWin.this, "Please enter a valid name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            askForName(); // Ask for name again if it was not entered
        }
    }

    private void showNewWindow(String name) {
        newWindow = new JFrame("Congratulations!");
        newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newWindow.setSize(400, 300);

        newWindow.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Game Win");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);

                GameWin gameWin = new GameWin();
                frame.getContentPane().add(gameWin, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
