package solitaire;

/**
 *
 * @author Trey Baker [21155292]
 * 
 */
import java.io.Serializable;
import java.util.Stack;
public abstract class CardPile implements Serializable
{

    protected Stack<Card> cards;
    
    public CardPile() //CardPile constructor
    {
        cards = new Stack<Card>();
    }
    
    public void addCard(Card card) // Adds a card to a table pile
    {
      cards.push(card);
    }
    
    public Card removeCard() // Removes the top card in the cards Stack.
    {
        return cards.pop();
    }
    
    public Card getTopCard() //Returns the top card in the cards Stack.
    {
        return cards.peek();
    }
    
    public void displayCards() //Displays all Cards in the cards Stack
    {
        for (Card card: cards)
        {
            System.out.println(card.toString());
        }
    }
    
    public int getSize() //Gets the size of the cards stack and returns the size.
    {
        return cards.size();
    }
    
    public Card getCardByIndex(int index) // Returns the card at a specified index.
    {
        int opposite = cards.size() - 1 - index;
                
        if (index < 0 || index >= cards.size()) 
        {
            return null;
        }
        return cards.get(opposite);
    }
    
    public static void main(String[] args) //Main Method to Test Functionality.
    
    {
        CardPile test = new Table();
        
        Card aceSpades = new Card(Suit.SPADES, FaceValue.ACE);
        Card twoDiamonds = new Card(Suit.DIAMONDS,FaceValue.TWO);
        Card threeClubs = new Card (Suit.CLUBS, FaceValue.THREE);
        
        test.addCard(aceSpades);
        
        System.out.println(test.getTopCard());
        
        test.addCard(twoDiamonds);
        
        System.out.println(test.getTopCard());
        
        test.addCard(threeClubs);
        
        System.out.println(test.getTopCard());
        
        System.out.println();
        
        test.removeCard();
        
        System.out.println(test.getTopCard());
        
        test.displayCards();
        
    }
    
    
    
}
