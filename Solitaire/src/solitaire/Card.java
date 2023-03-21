package solitaire;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class Card {
    
    Suit suit;
    FaceValue value;
    
    public Card() {
        
        this.suit = Suit.SPADES;
        this.value = FaceValue.ACE;
        
    }
    
    public void PrintCard() //Prints the card
    {
        System.out.println("┌────┐");
        System.out.println("|"+value.icon+suit.icon+" |");
        System.out.println("└────┘"); 
    }
    
    public static void main(String[] args)
    {
        Card card = new Card();
        
        card.PrintCard();
    }
    
}
