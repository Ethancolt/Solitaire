package solitaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Ethan Smith [21153581] and Trey Baker [21155292]
 */
public class Play {

    Foundation[] foundation = new Foundation[4];
    Stock stock = new Stock();
    Waste waste = new Waste();
    Table[] table = new Table[7];
    ArrayList<Card> newDeck = new ArrayList<>();
    boolean complete = false;
    int moves = 0;

    public Play() {

        for (int i = 0; i < 4; i++) {

            foundation[i] = new Foundation();

        }

        for (int i = 0; i < 7; i++) {

            table[i] = new Table();

        }

    }

    public static void main(String[] args)
    {

        Play game = new Play();

        game.generate();

        Scanner keyboard = new Scanner(System.in);

        while (game.playGame(keyboard));
        
        if (game.complete) 
        {
            
            String playerName;
            int finalScore = 1000 - game.moves;
            
            System.out.println("Congratulations you have won!!");
            System.out.println("Your final score was: "+ finalScore);
            System.out.println("Please enter your name to save your score!");
            playerName = keyboard.nextLine();
            
            SaveGame.saveHighscore(playerName, finalScore);
            SaveGame.displayHighscores();
            
        }
        
        else
        {
            String playerName;
            int numOfCards = game.foundation[0].getSize() 
                    + game.foundation[1].getSize() 
                    + game.foundation[2].getSize() 
                    + game.foundation[3].getSize();
            int finalScore = (int) ((numOfCards / 52f) * 500);
            
            System.out.println("You have quit the game! your score will be lower");
            System.out.println("Your final score was: "+ finalScore);
            System.out.println("Please enter your name to save your score!");
            playerName = keyboard.nextLine();
            
            SaveGame.saveHighscore(playerName, finalScore);
            SaveGame.displayHighscores();
        }
        

        keyboard.close();

    }

    public void generate() {

        //Create a deck of cards
        for (Suit s : Suit.values()) {

            for (FaceValue fv : FaceValue.values()) {

                newDeck.add(new Card(s, fv));

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
        
        //Turn the top cards face up
        for (Table t : table) {
            
            t.getTopCard().faceUp = true;
            
        }

    }

    public boolean playGame(Scanner keyboard) {

         
        
        DisplayCards.displayTables(table[0], table[1], table[2], table[3], table[4], table[5], table[6]);
        DisplayCards.displayStock(stock);
        DisplayCards.displayWaste(waste);
        DisplayCards.displayFoundations(foundation[0], foundation[1], foundation[2], foundation[3]);
        
        //ask user what action they would like to take
        keyboard = new Scanner(System.in);

        System.out.println("Please enter your next move:");

        String[] command = keyboard.nextLine().toLowerCase().split(" ");

        //check if command if a 2 part command
        if (command.length > 1) {

            //check if the card is valid
            if (isValidCard(command[0])) {

                //find the location of the card if it is face up
                CardLocation location = findPile(command[0]);

                if (location != null) {

                    //checks which command the user is performing, and executes that command.
                    CardPile nextPile = null;

                    if (!command[1].equals("f")) {

                        switch (command[1]) {
                            case "t1":

                                nextPile = table[0];

                                break;
                            case "t2":

                                nextPile = table[1];

                                break;
                            case "t3":

                                nextPile = table[2];

                                break;
                            case "t4":

                                nextPile = table[3];

                                break;
                            case "t5":

                                nextPile = table[4];

                                break;
                            case "t6":

                                nextPile = table[5];

                                break;
                            case "t7":

                                nextPile = table[6];

                                break;

                            default:

                                nextPile = null;

                                break;

                        }

                        if (nextPile == null) {
                            
                            System.out.println("Incorrect pile name!");
                            
                        } else if (nextPile.getSize() != 0) {

                            if (nextPile.getTopCard().value.ordinal() - 1 == location.pile.getCardByIndex(location.index).value.ordinal()
                                    && nextPile.getTopCard().suit.color != location.pile.getCardByIndex(location.index).suit.color) {

                                Table temp = new Table();
                                
                                for (int i = 0; i < location.index + 1; i++) {
                                    
                                    temp.addCard(location.pile.removeCard());
                                    
                                }
                                
                                location.pile.getTopCard().faceUp = true;
                                
                                for (int i = 0; i < location.index + 1; i++) {
                                    
                                    nextPile.addCard(temp.removeCard());
                                    
                                }
                                
                                moves++;
                                

                            } else {

                                System.out.println("Cannot place card here.");

                            }

                        } else {

                            if (location.pile.getTopCard().value == FaceValue.KING) {

                                Table temp = new Table();
                                
                                for (int i = 0; i < location.index + 1; i++) {
                                    
                                    temp.addCard(location.pile.removeCard());
                                    
                                }
                                
                                location.pile.getTopCard().faceUp = true;
                                
                                for (int i = 0; i < location.index + 1; i++) {
                                    
                                    nextPile.addCard(temp.removeCard());
                                    
                                }
                                
                                moves++;

                            } else {

                                System.out.println("You can only place a King here!");

                            }

                        }

                    } else if (location.index == 0) {

                        //if the user enters the command f
                        switch (location.pile.getTopCard().suit.icon) {
                            case '♦':

                                nextPile = foundation[0];

                                break;
                            case '♥':

                                nextPile = foundation[1];

                                break;
                            case '♠':

                                nextPile = foundation[2];

                                break;
                            case '♣':

                                nextPile = foundation[3];

                                break;
                            default:

                                System.out.println("Error, please try again!");

                                break;
                        }

                        if (nextPile.getSize() != 0) {

                            // if there are cards in the foundation pile, check if the card being placed is 1 higher.
                            if (nextPile.getTopCard().value.ordinal() + 1 == location.pile.getTopCard().value.ordinal()) {

                                nextPile.addCard(location.pile.removeCard());
                                
                                location.pile.getTopCard().faceUp = true;
                                
                                moves++;

                            }

                        } else {

                            // if the foundation pile is empty, check if the card is an ace.
                            if (location.pile.getTopCard().value == FaceValue.ACE) {

                                nextPile.addCard(location.pile.removeCard());
                                
                                location.pile.getTopCard().faceUp = true;
                                
                                moves++;

                            }

                        }

                    } else {
                        
                        System.out.println("Only one card can be moved here at a time!");
                        
                    }

                } else {

                    System.out.println("Card is not on top!");

                }

            } else {

                System.out.println("Card is incorrect, please try again!");

            }

        } else {

            //check what single part command the user is using
            if ("s".equals(command[0])) {

                waste.addCard(stock.removeCard());
                
                moves++;

            } else if ("x".equals(command[0])) {

                return false;

            } else {

                System.out.println("Command not recognised, please try again!");

            }

        }

        //return true to continue the game, unless the game has been completed
        if (table[0].getSize() == 0
                && table[1].getSize() == 0
                && table[2].getSize() == 0
                && table[3].getSize() == 0
                && table[4].getSize() == 0
                && table[5].getSize() == 0
                && table[6].getSize() == 0
                && stock.getSize() == 0
                && waste.getSize() == 0) {
            
            complete = true;
            
            return false;

        } else {

            return true;

        }

    }

    public boolean isValidCard(String card) {

        char[] cardValue = card.toCharArray();
        
        if (cardValue.length != 2) {
            
            return false;
            
        }

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
     *
     */
    public CardLocation findPile(String card) {

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

        Card currentCard = null;

        for (Table t : table) {

            for (int i = 0; i < t.getSize(); i++) {

                currentCard = t.getCardByIndex(i);

                if (currentCard.faceUp && Character.toLowerCase(currentCard.value.icon) == cardValue[0] && Character.toLowerCase(currentCard.suit.icon) == cardValue[1]) {

                    return new CardLocation(t, i);

                }

            }

        }

        if (waste.getSize() != 0) {

            currentCard = waste.getTopCard();

            if (Character.toLowerCase(currentCard.value.icon) == cardValue[0] && Character.toLowerCase(currentCard.suit.icon) == cardValue[1]) {

                waste.getTopCard().faceUp = true;
                
                return new CardLocation(waste, 0);

            }

        }

        return null;

    }

}
