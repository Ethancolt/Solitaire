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

    public static void displayTables (Table t1, Table t2, Table t3, Table t4, Table t5,Table t6, Table t7) //Displays the Table piles in the game
    {
            System.out.println("Table0:\n" + t1
            + "\nTable1:\n" + t2
            + "\nTable2:\n" + t3
            + "\nTable3:\n" + t4
            + "\nTable4:\n" + t5
            + "\nTable5:\n" + t6
            + "\nTable6:\n" + t7);
    }
    
    public static void displayStock(Stock stock) //Displays the Stock pile in the Game
    {
        System.out.println("\nStock:\n" + stock);
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
