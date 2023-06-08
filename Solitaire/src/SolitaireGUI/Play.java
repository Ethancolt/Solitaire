
package SolitaireGUI;


import java.awt.Color;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Play {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainForm solitaire;
        
        // Define canvas components
        GamePanel canvas = new GamePanel();
        canvas.setSize(ConstantValues.gameWidth, ConstantValues.gameHeight);
        canvas.setBackground(Color.yellow);
        
        // Add components and properties to main form
        solitaire = new MainForm();
        solitaire.pack();
        solitaire.setVisible(true);
        solitaire.setResizable(false);
        solitaire.setSize(ConstantValues.gameWidth, ConstantValues.gameHeight);
        solitaire.add(canvas);
    }
    
}
