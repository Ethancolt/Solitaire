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
    
    public void PrintCard() //Prints the card
    {
        System.out.println("┌────┐");
        System.out.println("|"+value.icon+suit.icon+" |");
        System.out.println("└────┘"); 
    }
    
    public static void main(String[] args) //Main method to test functionality of Card class and methods.
    {
        Card card = new Card(Suit.CLUBS,FaceValue.THREE);
        
        card.PrintCard();
    }
    
}
