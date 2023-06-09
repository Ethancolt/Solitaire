
package SolitaireGUI;


import java.awt.Color;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Play 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
        
        
        GameOpen openMenu = new GameOpen();
        
        if (openMenu.isGameReady())
        {
        MainForm solitaire;
        
        GamePanel game = new GamePanel();
        game.setSize(ConstantValues.gameWidth, ConstantValues.gameHeight);
        game.setBackground(Color.yellow);
        
        // Add components and properties to main form
        solitaire = new MainForm();
        solitaire.pack();
        solitaire.setVisible(true);
        solitaire.setResizable(false);
        solitaire.setSize(ConstantValues.gameWidth, ConstantValues.gameHeight);
        solitaire.add(game);
        }
        
    }
    
}
