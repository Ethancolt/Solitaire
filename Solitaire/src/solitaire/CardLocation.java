package solitaire;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class CardLocation {
    
    public CardPile pile;
    public int index;
    
    public CardLocation(CardPile pile, int index) {
        
        this.pile = pile;
        this.index = index;
        
    }
    
}
