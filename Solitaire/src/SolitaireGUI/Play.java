package SolitaireGUI;

import java.awt.Color;
import javax.swing.SwingWorker;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Play {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GameOpen openMenu = new GameOpen();

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!openMenu.isGameReady()) {
                    Thread.sleep(100); // Wait until the game is ready
                }
                return null;
            }

            @Override
            protected void done() {
                // Create and show the game panel
                GamePanel game = new GamePanel(openMenu.board);
                game.setSize(ConstantValues.gameWidth, ConstantValues.gameHeight);
                game.setBackground(Color.yellow);

                MainForm solitaire = new MainForm();
                solitaire.pack();
                solitaire.setResizable(false);
                solitaire.setSize(ConstantValues.gameWidth, ConstantValues.gameHeight);
                solitaire.add(game);
                solitaire.setVisible(true);
            }
        };

        worker.execute();

    }

}
