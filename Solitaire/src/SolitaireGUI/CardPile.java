
package SolitaireGUI;


import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Trey Baker [21155292]
 */
public class CardPile implements Serializable {
    private LinkedList<Card> cards = new LinkedList<>();

    private int x_position;
    private int y_position;
    
    private int x_size = ConstantValues.cardWidth;
    private int y_size = ConstantValues.CARD_Y_SIZE;
    
    private String color;
    
    private int limit;
    private final int id;
    private int draggedCardsAllowed;
    private String orderType;

    public CardPile(int x_position, int y_position, int limit, int id, int draggedCardsAllowed, String ordertType){
        this.x_position = x_position;
        this.y_position = y_position;
        
        this.limit = limit;
        this.id = id;
        this.draggedCardsAllowed = draggedCardsAllowed;
        this.orderType = ordertType;
    }
        
    public void addCards(LinkedList<Card> cards ){
        this.cards.addAll(cards);
    }
    
    public void addCard(Card card){
        this.cards.add(card);
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    public int getX_position() {
        return x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public int getX_size() {
        return x_size;
    }

    public int getY_size() {
        return y_size;
    }

    public String getColor() {
        return color;
    }

    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public void setX_size(int x_size) {
        this.x_size = x_size;
    }

    public void setY_size(int y_size) {
        this.y_size = y_size;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public int getLimit(){
        return this.limit;
    }
    
    public int getDraggedCardsAllowed(){
        return this.draggedCardsAllowed;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getOrderType(){
        return this.orderType;
    }
}
