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

public static void displayTables(Table t1, Table t2, Table t3, Table t4, Table t5, Table t6, Table t7) 
{
    int maxSize = getMaxSize(t1, t2, t3, t4, t5, t6, t7);
    
    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", 
            "Table 1", "Table 2", "Table 3",
            "Table 4", "Table 5", "Table 6",
            "Table 7");
    
    for (int i = maxSize - 1; i >= 0; i--) 
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

    private static int getMaxSize(Table... tables) 
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

    private static String getCard(Table table, int index) 
    {
        if (index < table.getSize()) 
        {
            if(table.getCardByIndex(index).faceUp == true)
            {
                return table.getCardByIndex(index).toString();
            }
            return "[??]";
        } 
        else 
        {
            return "";
        }
    }
    
    public static void displayStock(Stock stock) //Displays the Stock pile in the Game
    {
        System.out.println("Stock:  " + "[" + stock.cards.size() + "]"); 
    }
    
    public static void displayWaste(Waste waste) // Displays the Waste pile in the game
    {
        System.out.println("\nWaste:\n" + waste);
    }
    
    public static void displayFoundations(Foundation f1, Foundation f2, Foundation f3, Foundation f4) // Displays the Foundation piles in the game
    {
        System.out.println("\nFoundation0:\n" + f1
            + "\nFoundation1:\n" + f2
            + "\nFoundation2:\n" + f3
            + "\nFoundation3:\n" + f4);
    }
    

    
}
