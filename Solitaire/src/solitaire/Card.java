package solitaire;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class Card {
    
    Suit suit;
    FaceValue value;
    
    public Card(Suit suit, FaceValue value) {
        
        this.suit = suit;
        this.value = value;
        
    }
    
    @Override
    public String toString() //Returns a string, representing the value of the card.
    {
        return "[" + value.icon + suit.icon + "]";
    }
    
    public static void main(String[] args) //Main method only used to test functionality of Card class and methods.
    {
        Card card = new Card(Suit.CLUBS,FaceValue.THREE);
        
        card.toString();
    }
    
}
