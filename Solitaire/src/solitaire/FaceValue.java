package solitaire;

/**
 *
 * @author Ethan Smith [21153581]
 */
public enum FaceValue {
    
    ACE('A'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('X'),
    JACK('J'),
    QUEEN('Q'),
    KING('K');
    
    public final char icon;
    
    FaceValue(char icon) {
        
        this.icon = icon;
        
    }
    
    
    public static boolean compare(char face) {
        
        for (FaceValue i : FaceValue.values()) {
            
            if (Character.toLowerCase(face) == Character.toLowerCase(i.icon)) {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
    
}
