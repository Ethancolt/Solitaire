package solitaire;

/**
 *
 * @author Ethan Smith [21153581]
 */
public enum Suit {
    
    DIAMONDS('♦'), 
    HEARTS('♥'), 
    SPADES('♠'), 
    CLUBS('♣');
    
    public final char icon;
    
    Suit(char icon) {
        
        this.icon = icon;
        
    }

}