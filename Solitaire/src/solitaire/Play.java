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

    public static void main(String[] args) {
        
        Play game = new Play();
        
        game.generate();
        
        while (game.playGame());

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
            
            for (int row = 0; row < column; row++) {
                
                table[column].addCard(newDeck.remove(0));
                
            }
            
        }
        
        //Put the rest of the cards into the Stock
        for (Card card : newDeck) {
            
            stock.addCard(newDeck.remove(0));
            
        }
        
    }
    
    public boolean playGame() {
        
        //todo: display cards
        
        
        
        //ask user what action they would like to take
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Please enter your next move:");
        
        String[] command = keyboard.nextLine().toLowerCase().split(" ");
        
        //check if command if a 2 part command
        if (command.length > 1) {
            
            //check if the card is valid
            if (isValidCard(command[0])) {
                
                //check if the card is on top, and return it
                Card currentCard = findCard(command[0]);
                
                if (currentCard != null) {
                    
                    //todo: perform commands
                    switch (command[1]) {
                        case "t1":

                            break;
                        case "t2":

                            break;
                        case "t3":

                            break;
                        case "t4":

                            break;
                        case "t5":

                            break;
                        case "t6":

                            break;
                        case "t7":

                            break;
                        case "f":

                            break;

                        default:
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
        
        
        keyboard.close();
        
        //return true to continue the game
        return true;
        
    }
    
    public boolean isValidCard(String card) {
        
        char[] cardValue = card.toCharArray();
        
        return FaceValue.compare(cardValue[0]) && Suit.compare(cardValue[1]);
        
    }
    
    public Card findCard(String card) {
        
        char[] cardValue = card.toCharArray();
        
        //todo: check if card is on top, and return the card
        
        return null;
        
    }

}
