package solitaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class Play {
    
    Foundation[] foundation = new Foundation[4];
    Stock stock = new Stock();
    Waste waste = new Waste();
    Table[] table = new Table[7];
    ArrayList<Card> newDeck = new ArrayList<>();
    
    public Play() {
        
        for (int i = 0; i < 4; i++) {
            
            foundation[i] = new Foundation();
            
        }
        
        for (int i = 0; i < 7; i++) {
            
            table[i] = new Table();
            
        }
        
    }

    public static void main(String[] args) {
        
        Play game = new Play();
        
        game.generate();
        
        Scanner keyboard = new Scanner(System.in);
        
        while (game.playGame(keyboard));
        
        keyboard.close();

    }
    
    public void generate() {
        
        //Create a deck of cards
        for (Suit s : Suit.values()) {
            
            for (FaceValue fv : FaceValue.values()) {
                
                newDeck.add(new Card(s,fv));
                
            }
            
        }
        
        //Shuffle the deck
        Collections.shuffle(newDeck);
        
        //Lay out cards for the Table
        for (int column = 0; column < 7; column++) {
            
            for (int row = 0; row < column + 1; row++) {
                
                table[column].addCard(newDeck.remove(0));
                
            }
            
        }
        
        //Put the rest of the cards into the Stock
        int size = newDeck.size();
        
        for (int i = 0; i < size; i++) {
            
            stock.addCard(newDeck.remove(0));
            
        }
        
    }
    
    public boolean playGame(Scanner keyboard) {
        
        //pending: @Trey to create a display cards method
        
        System.out.println("Table0: "+table[0]+
                "\nTable1: "+table[1]+
                "\nTable2: "+table[2]+
                "\nTable3: "+table[3]+
                "\nTable4: "+table[4]+
                "\nTable5: "+table[5]+
                "\nTable6: "+table[6]+
                "\nStock: "+stock+
                "\nWaste: "+waste+
                "\nFoundation0: "+foundation[0]+
                "\nFoundation1: "+foundation[1]+
                "\nFoundation2: "+foundation[2]+
                "\nFoundation3: "+foundation[3]);
        
        //ask user what action they would like to take
        keyboard = new Scanner(System.in);
        
        System.out.println("Please enter your next move:");
        
        String[] command = keyboard.nextLine().toLowerCase().split(" ");
        
        //check if command if a 2 part command
        if (command.length > 1) {
            
            //check if the card is valid
            if (isValidCard(command[0])) {
                
                //check if the card is on top, and return the pile
                CardPile currentPile = findPile(command[0]);
                
                if (currentPile.getTopCard() != null) {
                    
                    //checks which command the user is performing, and executes that command.
                    switch (command[1]) {
                        case "t1":
                            
                            table[0].addCard(currentPile.removeCard());
                            
                            break;
                        case "t2":

                            table[1].addCard(currentPile.removeCard());
                            
                            break;
                        case "t3":

                            table[2].addCard(currentPile.removeCard());
                            
                            break;
                        case "t4":

                            table[3].addCard(currentPile.removeCard());
                            
                            break;
                        case "t5":

                            table[4].addCard(currentPile.removeCard());
                            
                            break;
                        case "t6":

                            table[5].addCard(currentPile.removeCard());
                            
                            break;
                        case "t7":

                            table[6].addCard(currentPile.removeCard());
                            
                            break;
                        case "f":

                            switch (currentPile.getTopCard().suit.icon) {
                                case '♦':
                                    
                                    if (foundation[0].getTopCard() != null) {
                                        
                                        // if there are cards in the foundation pile, check if the card being placed is 1 higher.
                                        if (foundation[0].getTopCard().value.ordinal() + 1 == currentPile.getTopCard().value.ordinal()) {
                                            
                                            foundation[0].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    } else {
                                        
                                        // if the foundation pile is empty, check if the card is an ace.
                                        if (currentPile.getTopCard().value == FaceValue.ACE) {
                                            
                                            foundation[0].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    }
                                    
                                    break;
                                case '♥':
                                    
                                    if (foundation[1].getTopCard() != null) {
                                        
                                        // if there are cards in the foundation pile, check if the card being placed is 1 higher.
                                        if (foundation[1].getTopCard().value.ordinal() + 1 == currentPile.getTopCard().value.ordinal()) {
                                            
                                            foundation[1].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    } else {
                                        
                                        // if the foundation pile is empty, check if the card is an ace.
                                        if (currentPile.getTopCard().value == FaceValue.ACE) {
                                            
                                            foundation[1].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    }
                                    
                                    break;
                                case '♠':
                                    
                                    if (foundation[2].getTopCard() != null) {
                                        
                                        // if there are cards in the foundation pile, check if the card being placed is 1 higher.
                                        if (foundation[2].getTopCard().value.ordinal() + 1 == currentPile.getTopCard().value.ordinal()) {
                                            
                                            foundation[2].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    } else {
                                        
                                        // if the foundation pile is empty, check if the card is an ace.
                                        if (currentPile.getTopCard().value == FaceValue.ACE) {
                                            
                                            foundation[2].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    }
                                    
                                    break;
                                case '♣':
                                    
                                    if (foundation[3].getTopCard() != null) {
                                        
                                        // if there are cards in the foundation pile, check if the card being placed is 1 higher.
                                        if (foundation[3].getTopCard().value.ordinal() + 1 == currentPile.getTopCard().value.ordinal()) {
                                            
                                            foundation[3].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    } else {
                                        
                                        // if the foundation pile is empty, check if the card is an ace.
                                        if (currentPile.getTopCard().value == FaceValue.ACE) {
                                            
                                            foundation[3].addCard(currentPile.removeCard());
                                            
                                        }
                                        
                                    }
                                    
                                    break;
                                default:
                                    
                                    System.out.println("Error, please try again!");
                                    
                                    break;
                            }
                            
                            break;

                        default:
                            
                            System.out.println("Card pile not found.");
                            
                            break;

                    }
                    
                } else {
                    
                    System.out.println("Card is not on top!");
                    
                }
                
                
            } else {
                
                System.out.println("Card is incorrect, please try again!");
                
            }
            
            
        } else {
            
            //check what single part command the user is using
            if (command[0] == "s") {
                
                waste.addCard(stock.removeCard());
                
            } else if (command[0] == "x") {
                
                return false;
                
            } else {
                
                System.out.println("Command not recognised, please try again!");
                
            }
            
        }
        
        
        //return true to continue the game
        return true;
        
    }
    
    public boolean isValidCard(String card) {
        
        char[] cardValue = card.toCharArray();
        
        switch (Character.toLowerCase(cardValue[1])) {
            case 'd':
                
                cardValue[1] = '♦';
                
                break;
            case 'h':
                
                cardValue[1] = '♥';
                
                break;
            case 's':
                
                cardValue[1] = '♠';
                
                break;
            case 'c':
                
                cardValue[1] = '♣';
                
                break;
            default:
                
                System.out.println("Invalid Suit.");
                
                break;
        }
        
        return FaceValue.compare(cardValue[0]) && Suit.compare(cardValue[1]);
        
    }
    
    /**
     * Returns the CardPile if the card is found on top of that pile.
     **/
    public CardPile findPile(String card) {
        
        char[] cardValue = card.toCharArray();
        
        switch (Character.toLowerCase(cardValue[1])) {
            case 'd':
                
                cardValue[1] = '♦';
                
                break;
            case 'h':
                
                cardValue[1] = '♥';
                
                break;
            case 's':
                
                cardValue[1] = '♠';
                
                break;
            case 'c':
                
                cardValue[1] = '♣';
                
                break;
            default:
                
                System.out.println("Invalid Suit.");
                
                break;
        }
        
        Card top = null;
        
        for (Table t : table) {
            
            //pending: @Trey to create getSize() method in Table
            if (t.getSize() != 0) {
                
                top = t.getTopCard();
            
                if (top.value.icon == cardValue[0] && top.suit.icon == cardValue[1]) {

                    return t;

                }
                
            }
            
        }
        
        //pending: @Trey to create getSize() method in Waste
        if (waste.getSize() != 0) {
            
            top = waste.getTopCard();
        
            if (top.value.icon == cardValue[0] && top.suit.icon == cardValue[1]) {

                    return waste;

            }
            
        }
        
        return null;
        
    }

}
