package solitaire;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Waste extends CardPile 
{
    public Waste()
    {
        super();
    }
    
    @Override
    public String toString()
    {
       String out = "";
        for (Card card: cards)
        {
            out += card.toString();
            out += ",";
        }
        
        return out;
    }      
    
}
