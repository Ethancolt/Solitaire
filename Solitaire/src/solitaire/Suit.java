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
    
    public static boolean compare(char suit) {
        
        for (Suit i : Suit.values()) {
            
            if (Character.toLowerCase(suit) == Character.toLowerCase(i.icon)) {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }

}
