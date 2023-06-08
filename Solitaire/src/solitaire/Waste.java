package solitaire;

import java.io.Serializable;

/**
 *
 * @author Trey Baker [21155292]
 */
public class Waste extends CardPile implements Serializable
{
    public Waste() //Super Constructor
    {
        super();
    }
    
    @Override
    public String toString() //ToString Method for the Waste Pile
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
