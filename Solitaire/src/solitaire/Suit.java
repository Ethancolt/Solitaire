package solitaire;

import java.io.Serializable;

/**
 *
 * @author Ethan Smith [21153581]
 */
public enum Suit implements Serializable {
    
    DIAMONDS('♦', 'R'), 
    HEARTS('♥', 'R'), 
    SPADES('♠', 'B'), 
    CLUBS('♣', 'B');
    
    public final char icon;
    public final char color;
    
    Suit(char icon, char color) {
        
        this.icon = icon;
        this.color = color;
        
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
