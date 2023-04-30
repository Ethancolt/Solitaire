/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solitaire;

/**
 *
 * @author Trey Baker [21155292]
 */
public class DisplayCards 
{

    public static void displayTables(Table t1, Table t2, Table t3, Table t4, Table t5, Table t6, Table t7) //Displays the Card Tables
    {
        int maxSize = getMaxSize(t1, t2, t3, t4, t5, t6, t7);
    
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", 
            "Table 1", "Table 2", "Table 3",
            "Table 4", "Table 5", "Table 6",
            "Table 7");
    
        for (int i = 0; i < maxSize; i++) 
        {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", 
                getCard(t1, i), 
                getCard(t2, i), 
                getCard(t3, i),
                getCard(t4, i),
                getCard(t5, i), 
                getCard(t6, i), 
                getCard(t7, i));
        }
    }

    private static int getMaxSize(Table... tables) //Gets the max size of the tables, needed for the display
    {
        int maxSize = 0;
        for (Table table : tables) 
        {
            int size = table.getSize();
            if (size > maxSize) 
            {
                maxSize = size;
            }
        }
        return maxSize;
    }

    private static String getCard(Table table, int index) //Gets a specific card for the tables, used in the displayTables method
    {
        if (index < table.getSize()) 
        {
            if(table.getCardByIndex(table.getSize() - 1 - index).faceUp == true)
            {
                return table.getCardByIndex(table.getSize() - 1 - index).toString();
            }
            return "[??]";
        } 
        else 
        {
            return "";
        }
    }
    
    public static void displayTopRow(Stock stock, Waste waste, Foundation f1, Foundation f2, Foundation f3, Foundation f4)  // Displays the Stock, Waste, and Foundation piles
    {
        // Print headers
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s", "Stock", "Waste", "Foundation0", "Foundation1", "Foundation2", "Foundation3"));
    
        // Print stock card
    
        String stockCard = "";
        if (!stock.cards.isEmpty()) 
        {
            stockCard = "[" + stock.cards.size() + "]";
        }
    
        // Print waste card
        String wasteCard = "";
    
        if (!waste.cards.isEmpty()) 
        {
            wasteCard = waste.cards.peek().toString();
        }
        // Print foundation cards
        String f1Card = "";
        if (!f1.cards.isEmpty()) 
        {
            f1Card = f1.cards.peek().toString();
        }
        String f2Card = "";
    
        if (!f2.cards.isEmpty()) 
        {
            f2Card = f2.cards.peek().toString();
        }
        String f3Card = "";
    
        if (!f3.cards.isEmpty()) 
        {
            f3Card = f3.cards.peek().toString();
        }
        String f4Card = "";
    
        if (!f4.cards.isEmpty()) 
        {
            f4Card = f4.cards.peek().toString();
        }
    
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s", stockCard, wasteCard, f1Card, f2Card, f3Card, f4Card));

    }
}

    

