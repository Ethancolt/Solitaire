package solitaire;

import java.util.ArrayList;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class Play {
    
    Foundation f1 = new Foundation();
    Foundation f2 = new Foundation();
    Foundation f3 = new Foundation();
    Foundation f4 = new Foundation();
    Stock stock = new Stock();
    Waste waste = new Waste();
    Table t1 = new Table();
    Table t2 = new Table();
    Table t3 = new Table();
    Table t4 = new Table();
    Table t5 = new Table();
    Table t6 = new Table();
    Table t7 = new Table();
    ArrayList<Card> newDeck = new ArrayList<>();

    public static void main(String[] args) {
        
        Play game = new Play();
        
        game.generate();

    }
    
    public void generate() {
        
        for (Suit s : Suit.values()) {
            
            for (FaceValue fv : FaceValue.values()) {
                
                newDeck.add(new Card(s,fv));
                
            }
            
        }
        
        System.out.println(newDeck);
        
    }

}
