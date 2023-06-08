package solitaire;

import java.io.Serializable;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Stock extends CardPile implements Serializable
{
    public Stock()
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
