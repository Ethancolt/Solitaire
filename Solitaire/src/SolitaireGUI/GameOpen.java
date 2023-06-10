package SolitaireGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameOpen extends JFrame {

    private JTextField nameField;
    private JComboBox<String> userComboBox;
    private ArrayList<String> userNames;
    public boolean isGameReady = false;
    public GameBoard board = new GameBoard();

    public GameOpen() {
        super("Solitaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        JButton playButton = new JButton("New Game");
        JButton continueButton = new JButton("Continue");
        JButton rulesButton = new JButton("Rules");
        JButton quitButton = new JButton("Quit");

        continueButton.setEnabled(false);

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

                    int userID = ConstantValues.db.getUserID(userName);

                    isGameReady = true;
                    closeWindow();

                }
            }
        });

        continueButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int userID = ConstantValues.db.getUserID(nameField.getText());

                ConstantValues.db.loadGame(board, userID);

                isGameReady = true;
                closeWindow();

            }

        });

        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame rulesFrame = new JFrame("Game Rules");
                rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rulesFrame.setSize(2050, 630);
                JTextArea rulesTextArea = new JTextArea(Instructions.printGameRules());
                rulesTextArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(rulesTextArea);
                rulesFrame.add(scrollPane);
                rulesFrame.setVisible(true);
            }
        });

        userNames = ConstantValues.db.getUsers();

        userComboBox = new JComboBox<>();

        userComboBox.addItem("Create New User");

        for (String userName : userNames) {

            userComboBox.addItem(userName);

        }

        userComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String selectedUser = (String) userComboBox.getSelectedItem();

                if (selectedUser.equals("Create New User")) {

                    nameField.setEnabled(true);

                } else {

                    nameField.setEnabled(false);
                    nameField.setText(selectedUser);

                    int userID = ConstantValues.db.getUserID(selectedUser);

                    continueButton.setEnabled(ConstantValues.db.canBeContinued(userID));
                }

            }

        });

        JPanel userPanel = new JPanel();
        userPanel.add(userComboBox);

        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(continueButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(quitButton);

        add(userPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public boolean isGameReady() {
        return isGameReady;
    }

    private void closeWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setVisible(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameOpen();
            }
        });
    }
}
