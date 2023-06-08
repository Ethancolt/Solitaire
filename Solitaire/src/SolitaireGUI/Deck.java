
package SolitaireGUI;


import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Deck {
    private LinkedList<Card> mainStack = new LinkedList<>();
    public static final BufferedImage HIDDEN_SIDE = Utils.readImage("back-1.png");
    public static final BufferedImage NEW_GAME = Utils.readImage("new_game.png");

    public Deck(){
        newGame();
    }
    
    public LinkedList<Card> getCards(){
        return mainStack;
    }
    
    public void barajear(){
        
    }
    
    public void resetGame(){
        
    }
    
    public void newGame(){
        mainStack.clear();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 14; j++) {
                mainStack.add(new Card(i+"-"+j+".png", 0, 0, (i%2 != 0), j, i));
            }
        }
        Collections.shuffle(mainStack);
    }
    
    public void animation(){
        
    }
    
}
