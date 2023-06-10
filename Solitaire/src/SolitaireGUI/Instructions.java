/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolitaireGUI;

/**
 *
 * @author Itstr
 */
public class Instructions 
{
    public static String printGameRules()
    {
        return "Solitaire Game Rules:\n\n"
                        + "Setup:\nThe game begins with seven tableau piles of cards, with the first pile containing one card, the second pile containing two cards, "
                        + "and so on, up to the seventh pile containing seven cards. The top card of each tableau pile is face-up, while the rest are face-down. The remaining cards form the stock pile.\n\n\n"
                        + "Foundation Piles:\nThe four foundation piles (4 piles at the top) are initially empty. The objective is to build each pile in ascending order, starting with an Ace and then adding "
                        + "cards of the same suit in increasing rank (2, 3, 4, etc.) until a King is placed on top.\n\n\n"
                        + "Tableau Piles:\nCards in the tableau (7 piles at the bottom) piles can be built down in alternating colors (e.g., a black 7 can be placed on a red 8). For example, a red 6 can "
                        + "be placed on a black 7, and so on. Only the top card of each tableau pile is available for play."
                        + "\n\n\n"
                        + "Moving Cards:\nMove cards by dragging them and dropping them to the desired loctation with your mouse. You can move cards from the tableau piles to the foundation piles if they "
                        + "follow the ascending order and suit matching rules. Additionally, you can build sequences of descending cards on the tableau piles. For example, you can move a black Queen from the tableau onto a red King.\n\n\n"
                        + "Stock and Waste Piles:\nYou can draw cards from the stock pile (Top left pile with cards) one at a time and place them face-up in the waste pile. Once the stock pile is empty, "
                        + "you can turn the waste pile over to create a new stock pile, effectively allowing you to go through the deck again.\n\n\n"
                        + "Building on the Ace:\nWhen an Ace becomes available, you can move it to one of the foundation piles. Subsequently, you can build on that foundation pile by adding cards of the same suit in ascending order.\n\n\n"
                        + "Moving Groups of Cards:\nIn some variations of Klondike Solitaire, you can move a group of cards together if they are in descending order and of alternating colors. "
                        + "For example, you can move a black King and its sequence of cards onto a red Queen.\n\n\n"
                        + "Winning the Game:\nThe game is won when all cards are successfully moved to the foundation piles in ascending order, with each pile containing a complete suit from Ace to King.\n\n\n"
                        + "Losing the Game:\nThe game is lost when no more moves are possible, and the tableau and waste piles are empty. In such cases, you may choose to start a new game.";
    }
    
    
}
