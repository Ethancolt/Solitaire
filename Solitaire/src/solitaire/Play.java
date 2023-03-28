package solitaire;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class Play {
    
    Foundation[] foundation = new Foundation[4];
    Stock stock = new Stock();
    Waste waste = new Waste();
    Table[] table = new Table[7];
    ArrayList<Card> newDeck = new ArrayList<>();

    public static void main(String[] args) {
        
        Play game = new Play();
        
        game.generate();

    }
    
    public void generate() {
        
        //Create a deck of cards
        for (Suit s : Suit.values()) {
            
            for (FaceValue fv : FaceValue.values()) {
                
                newDeck.add(new Card(s,fv));
                
            }
            
        }
        
        //Shuffle the deck
        Collections.shuffle(newDeck);
        
        //Lay out cards for the Table
        for (int column = 0; column < 7; column++) {
            
            for (int row = 0; row < column; row++) {
                
                table[column].addCard(newDeck.remove(0));
                
            }
            
        }
        
        //Put the rest of the cards into the Stock
        for (Card card : newDeck) {
            
            stock.addCard(newDeck.remove(0));
            
        }
        
    }

}
