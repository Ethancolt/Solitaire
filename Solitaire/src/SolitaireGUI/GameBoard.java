
package SolitaireGUI;


import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

/**
 *
 * @author Trey Baker [21155292]
 */
public class GameBoard{
    
    private int x_clicket_in_dragged_card;
    private int y_clicket_in_dragged_card;
    private final CardPile CardsDragged;
    private CardPile groupOwnerOfDraggedCards;

    private CardPile provider1;
    private CardPile provider2;
    
    private CardPile foundation1;
    private CardPile foundation2;
    private CardPile foundation3;
    private CardPile foundation4;
    
    private CardPile table1;
    private CardPile table2;
    private CardPile table3;
    private CardPile table4;
    private CardPile table5;
    private CardPile table6;
    private CardPile table7;
    
    private boolean isGameWon;
    
  
    
    public GameBoard()
    {
        CardsDragged = new CardPile(0, 0, 0, 0, 13, "vertical");

        provider1 = new CardPile(100, 20, 0, -1, 0, "onTop");
        provider2 = new CardPile(300, 20, 0, -2, 1, "horizontal");
        
        foundation1 = new CardPile(700, 20, 0, -3, 1, "onTop");
        foundation2 = new CardPile(900, 20, 0, -4, 1, "onTop");
        foundation3 = new CardPile(1100, 20, 0, -5, 1, "onTop");
        foundation4 = new CardPile(1300, 20, 0, -6, 1, "onTop");
        
        table1 = new CardPile(100, 400, 1, 1, 13, "vertical");
        table2 = new CardPile(300, 400, 2, 2, 13, "vertical");
        table3 = new CardPile(500, 400, 3, 3, 13, "vertical");
        table4 = new CardPile(700, 400, 4, 4, 13, "vertical");
        table5 = new CardPile(900, 400, 5, 5, 13, "vertical");
        table6 = new CardPile(1100, 400, 6, 6, 13, "vertical");
        table7 = new CardPile(1300, 400, 7, 7, 13, "vertical");
        
    }
    
    public void drawBoard(Graphics g, ImageObserver observer)
    {
        g.setColor(ConstantValues.groupsColor);

        g.fillRect(provider1.getX_position(), provider1.getY_position(), provider1.getX_size(), provider1.getY_size());
        g.fillRect(provider2.getX_position(), provider2.getY_position(), provider2.getX_size(), provider2.getY_size());
        
        g.fillRect(foundation1.getX_position(), foundation1.getY_position(), foundation1.getX_size(), foundation1.getY_size());
        g.fillRect(foundation2.getX_position(), foundation2.getY_position(), foundation2.getX_size(), foundation2.getY_size());
        g.fillRect(foundation3.getX_position(), foundation3.getY_position(), foundation3.getX_size(), foundation3.getY_size());
        g.fillRect(foundation4.getX_position(), foundation4.getY_position(), foundation4.getX_size(), foundation4.getY_size());
        
        g.fillRect(table1.getX_position(), table1.getY_position(), table1.getX_size(), table1.getY_size());
        g.fillRect(table2.getX_position(), table2.getY_position(), table2.getX_size(), table2.getY_size());
        g.fillRect(table3.getX_position(), table3.getY_position(), table3.getX_size(), table3.getY_size());
        g.fillRect(table4.getX_position(), table4.getY_position(), table4.getX_size(), table4.getY_size());        
        g.fillRect(table5.getX_position(), table5.getY_position(), table5.getX_size(), table5.getY_size());
        g.fillRect(table6.getX_position(), table6.getY_position(), table6.getX_size(), table6.getY_size());
        g.fillRect(table7.getX_position(), table7.getY_position(), table7.getX_size(), table7.getY_size());
        
        this.drawCards(provider1.getCards(), g, observer);
        this.drawCards(provider2.getCards(), g, observer);
        
        this.drawCards(foundation1.getCards(), g, observer);
        this.drawCards(foundation2.getCards(), g, observer);
        this.drawCards(foundation3.getCards(), g, observer);
        this.drawCards(foundation4.getCards(), g, observer);
        
        this.drawCards(table1.getCards(), g, observer);
        this.drawCards(table2.getCards(), g, observer);
        this.drawCards(table3.getCards(), g, observer);
        this.drawCards(table4.getCards(), g, observer);
        this.drawCards(table5.getCards(), g, observer);
        this.drawCards(table6.getCards(), g, observer);
        this.drawCards(table7.getCards(), g, observer);
        
     
        this.drawDraggedCards(CardsDragged.getCards(), g, observer);
    }
    
    private void drawCards(LinkedList<Card> cards, Graphics g, ImageObserver observer)
    {
        Card card;
        for (int i = 0; i < cards.size(); i++) 
        {
            card = cards.get(i);
      
            if(card.isVisible())
            {
                g.drawImage(card.getImage(), card.getImageXLocation(), card.getImageYLocation(), 
                        card.getImageXSize(), card.getImageYSize(), observer);
            }else{
          
                g.drawImage(Deck.HIDDEN_SIDE, card.getImageXLocation(), card.getImageYLocation(), 
                        card.getImageXSize(), card.getImageYSize(), observer);
            }
        }
    }

    private void drawDraggedCards(LinkedList<Card> cards, Graphics g, ImageObserver observer)
    {
        Card card;
        Card initial = !cards.isEmpty() ? cards.get(0) : new Card();

        g.setColor(ConstantValues.selectedCardColor);
        for (int i = 0; i < cards.size(); i++) 
        {
            card = cards.get(i);

            g.drawImage(card.getImage(), initial.getImageXLocation(), initial.getImageYLocation() + i * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST, 
                    card.getImageXSize(), card.getImageYSize(), observer);
            g.fillRect(initial.getImageXLocation(), initial.getImageYLocation() + i * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST, card.getImageXSize(), card.getImageYSize());
        }
    }
    

    public void addCards(LinkedList<Card> cards){
        int currentStack = 1;
        Card currentCard;

        while(!cards.isEmpty()) 
        {
            switch(currentStack)
            {
                case 1:
                    currentStack = setInitialCardData(cards, table1, currentStack);
                    break;
                case 2:
                    currentStack = setInitialCardData(cards, table2, currentStack);
                    break;
                case 3:
                    currentStack = setInitialCardData(cards, table3, currentStack);
                    break;
                case 4:
                    currentStack = setInitialCardData(cards, table4, currentStack);
                    break;
                case 5:
                    currentStack = setInitialCardData(cards, table5, currentStack);
                    break;
                case 6:
                    currentStack = setInitialCardData(cards, table6, currentStack);
                    break;
                case 7:
                    currentStack = setInitialCardData(cards, table7, currentStack);
                    break;
                case 8:
                    currentCard = cards.remove();
                    currentCard.setIsVisible(false);
                    currentCard.setImageXLocation(provider1.getX_position());
                    currentCard.setImageYLocation(provider1.getY_position());
                    provider1.addCard(currentCard);
                    break;
            }
        }
    }
    
    /**
     * 
     * @param cards
     * @param cardGroup
     * @param currentStack
     * @return 
     */
    private int setInitialCardData(LinkedList<Card> cards, CardPile cardGroup, int currentStack)
    {
        Card currentCard;

        currentCard = cards.remove();
        currentCard.setImageXLocation(cardGroup.getX_position());
        currentCard.setImageYLocation(cardGroup.getY_position() + cardGroup.getCards().size() * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST);
        if(cardGroup.getCards().size() + 1 == cardGroup.getLimit()){

            currentCard.setIsVisible(true);
            cardGroup.addCard(currentCard);
            currentStack++;
        }
        
        else
        {

            currentCard.setIsVisible(false);
            cardGroup.addCard(currentCard);
        }
        return currentStack;
    }
    
    public void clickProvider(int x, int y)
    {
    	

        if(this.isXYInsideXYRegion(x, y, this.provider1.getX_position(), this.provider1.getY_position()))
        {
            Card card = null;
            
            if(provider1.getCards().isEmpty() && provider2.getCards().isEmpty())
            {
                return;
            }


            while(!provider2.getCards().isEmpty())
            {
                card = provider2.getCards().removeFirst();
                card.setImageXLocation(provider1.getX_position());
                card.setImageYLocation(provider1.getY_position());
                card.setIsVisible(false);

                provider1.getCards().addLast(card);
            }
            System.out.println(card != null? x-card.getImageXLocation(): -1);
            System.out.println(card != null? y-card.getImageYLocation(): -1);


            for (int i = 0; i < 3; i++) {
                if(!provider1.getCards().isEmpty()){
                    
                    card = provider1.getCards().remove();
                    card.setImageXLocation(provider2.getX_position() + i * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST);
                    card.setImageYLocation(provider2.getY_position());
                    card.setIsVisible(true);

                    provider2.addCard(card);
                }
            }
        }
    }
    
    public void draggImages(int x, int y)
    {

        if(!CardsDragged.getCards().isEmpty()){
            Card card = CardsDragged.getCards().get(0);

            card.setImageXLocation(x - x_clicket_in_dragged_card);
            card.setImageYLocation(y - y_clicket_in_dragged_card);
        }
        else
        {
            this.searchImageInList(x, y, foundation1);
            this.searchImageInList(x, y, foundation2);
            this.searchImageInList(x, y, foundation3);
            this.searchImageInList(x, y, foundation4);
            
            this.searchImageInList(x, y, table1);
            this.searchImageInList(x, y, table2);
            this.searchImageInList(x, y, table3);
            this.searchImageInList(x, y, table4);
            this.searchImageInList(x, y, table5);
            this.searchImageInList(x, y, table6);
            this.searchImageInList(x, y, table7);
            
            this.searchImageInList(x, y, provider2);
        }
    }

    private void searchImageInList(int x, int y, CardPile cards){
        Card card;

        for (int i = cards.getCards().size() -1; i > -1; i--) {
            card = cards.getCards().get(i);


            if(this.isXYInsideXYRegion(x, y, card.getImageXLocation(), card.getImageYLocation())){
                

                if (!card.isVisible() || cards.getCards().size() - i > cards.getDraggedCardsAllowed()) {
                    return;
                }

                this.x_clicket_in_dragged_card = x - card.getImageXLocation();
                this.y_clicket_in_dragged_card = y - card.getImageYLocation();
                

                int cardsSize = cards.getCards().size();
                for (int j = i; j < cardsSize; j++) {
                    card = cards.getCards().remove(i);
                    card.setIsSelected(true);
                    card.setImageXLocation(x - this.x_clicket_in_dragged_card);
                    card.setImageYLocation(y - this.y_clicket_in_dragged_card + CardsDragged.getCards().size() * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST);
                    CardsDragged.getCards().add(card);
                }
                

                this.groupOwnerOfDraggedCards = cards;

                return;
            }
        }
    }
    
    public void releaseCards(int x, int y)
    {

        if(!CardsDragged.getCards().isEmpty())
        {
            if(this.isXYInsideXYRegion(x, y, 
                    this.table1.getX_position(), 
                    this.table1.getY_position() + (this.table1.getCards().size() -1)  * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {

                conditionsForGameGroups(table1, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, 
                    this.table2.getX_position(),
                    this.table2.getY_position() + (this.table2.getCards().size() -1)  * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {

                conditionsForGameGroups(table2, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, 
                    this.table3.getX_position(),
                    this.table3.getY_position() + (this.table3.getCards().size() -1)  * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {
                
                conditionsForGameGroups(table3, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, 
                    this.table4.getX_position(),
                    this.table4.getY_position() + (this.table4.getCards().size() -1)  * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {
                
                conditionsForGameGroups(table4, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, 
                    this.table5.getX_position(), 
                    this.table5.getY_position() + (this.table5.getCards().size() -1) * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {
                
               conditionsForGameGroups(table5, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, 
                    this.table6.getX_position(), 
                    this.table6.getY_position() + (this.table6.getCards().size() -1) * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {
                
                conditionsForGameGroups(table6, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, 
                    this.table7.getX_position(), 
                    this.table7.getY_position() + (this.table7.getCards().size() -1) * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST))
            {
                
                conditionsForGameGroups(table7, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, this.foundation1.getX_position(), this.foundation1.getY_position()))
            {
                
                 foundationComplete(foundation1, CardsDragged);
             }
            else if(this.isXYInsideXYRegion(x, y, this.foundation2.getX_position(), this.foundation2.getY_position()))
            {
                 
                 foundationComplete(foundation2, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, this.foundation3.getX_position(), this.foundation3.getY_position()))
            {
                 
                 foundationComplete(foundation3, CardsDragged);
            }
            else if(this.isXYInsideXYRegion(x, y, this.foundation4.getX_position(), this.foundation4.getY_position()))
            {
                 
                 foundationComplete(foundation4, CardsDragged);
            }
            else
            {
                this.moveAllCardsToAnotherList(CardsDragged, groupOwnerOfDraggedCards);

            }
        }
    }

    private void foundationComplete(CardPile group, CardPile draggedCards){
        
        if(draggedCards.getCards().size() == 1){

            if (group.getId() == groupOwnerOfDraggedCards.getId()) {
                moveAllCardsToAnotherList(draggedCards, groupOwnerOfDraggedCards);
                return;
            }
            Card draggedCard = draggedCards.getCards().getFirst();

            if(!group.getCards().isEmpty() && 
                    group.getCards().getLast().getNumber()+1 == draggedCard.getNumber() && 
                    group.getCards().getLast().getGroup() == draggedCard.getGroup()){

                group.addCard(draggedCard);
                draggedCard.setIsSelected(false);
                draggedCards.getCards().remove();

                this.setNewCoordinates(draggedCard, group);
                if(!groupOwnerOfDraggedCards.getCards().isEmpty() && !groupOwnerOfDraggedCards.getCards().getFirst().isVisible()){
                    groupOwnerOfDraggedCards.getCards().getLast().setIsVisible(true);
                }
            }else if(group.getCards().isEmpty() && draggedCard.getNumber() == 1){

                group.addCard(draggedCard);
                draggedCard.setIsSelected(false);
                draggedCards.getCards().remove();
                this.setNewCoordinates(draggedCard, group);

                if(!groupOwnerOfDraggedCards.getCards().isEmpty() && !groupOwnerOfDraggedCards.getCards().getFirst().isVisible()){
                    groupOwnerOfDraggedCards.getCards().getLast().setIsVisible(true);
                }
            }else{
                this.setNewCoordinates(draggedCard, groupOwnerOfDraggedCards);
                groupOwnerOfDraggedCards.addCard(draggedCard);
                draggedCard.setIsSelected(false);
                draggedCards.getCards().remove();

            }
        }else{
            this.moveAllCardsToAnotherList(draggedCards, groupOwnerOfDraggedCards);
        }
    }
    
    private void conditionsForGameGroups(CardPile group, CardPile draggedCards)
    {
            Card draggedCard = draggedCards.getCards().getFirst();
            
            if (group.getId() == groupOwnerOfDraggedCards.getId()) {
                moveAllCardsToAnotherList(draggedCards, groupOwnerOfDraggedCards);
                return;
            }
            if(!group.getCards().isEmpty() && 
                    group.getCards().getLast().getNumber()-1 == draggedCard.getNumber() && 
                    group.getCards().getLast().isRedColor() != draggedCards.getCards().getFirst().isRedColor()){

                this.moveAllCardsToAnotherList(draggedCards, group);
   
                if(!groupOwnerOfDraggedCards.getCards().isEmpty() && !groupOwnerOfDraggedCards.getCards().getFirst().isVisible()){
                    groupOwnerOfDraggedCards.getCards().getLast().setIsVisible(true);
                }
            }else if(group.getCards().isEmpty() && draggedCard.getNumber() == 13){
                this.moveAllCardsToAnotherList(draggedCards, group);

                if(!groupOwnerOfDraggedCards.getCards().isEmpty() && !groupOwnerOfDraggedCards.getCards().getFirst().isVisible()){
                    groupOwnerOfDraggedCards.getCards().getLast().setIsVisible(true);
                }
            }else{
                moveAllCardsToAnotherList(draggedCards, groupOwnerOfDraggedCards);
            }
    }

    private boolean isXYInsideXYRegion(int mouse_x, int mouse_y, int region_x, int region_y)
    {
        if(mouse_x >= region_x && mouse_x <= region_x + ConstantValues.cardWidth
            && mouse_y >= region_y  && mouse_y <= region_y + ConstantValues.CARD_Y_SIZE){
            
            return true;
        }
        return false;
    }

    private void moveAllCardsToAnotherList(CardPile source, CardPile target)
    {
        Card card;
        while(!source.getCards().isEmpty())
        {
            card = source.getCards().remove();
            card.setIsSelected(false);
            this.setNewCoordinates(card, target);
            target.addCard(card);
        }
        
    }
    private void setNewCoordinates(Card card, CardPile groupOwner)
    {
        switch(groupOwner.getOrderType()){
            case "onTop":
                card.setImageXLocation(groupOwner.getX_position());
                card.setImageYLocation(groupOwner.getY_position());
                break;
            case "vertical":
                card.setImageXLocation(groupOwner.getX_position());
                card.setImageYLocation(groupOwner.getY_position() + groupOwner.getCards().size() * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST);
                break;
            case "horizontal":
                card.setImageXLocation(groupOwner.getX_position() + groupOwner.getCards().size() * ConstantValues.SPACE_BETWEEN_CARDS_IN_LIST);
                card.setImageYLocation(groupOwner.getY_position());
                break;
        }
    }
    
    public CardPile[] getCardPiles() {
        
        CardPile[] piles = new CardPile[13];
        
        piles[0] = table1;
        piles[1] = table2;
        piles[2] = table3;
        piles[3] = table4;
        piles[4] = table5;
        piles[5] = table6;
        piles[6] = table7;
        
        piles[7] = foundation1;
        piles[8] = foundation2;
        piles[9] = foundation3;
        piles[10] = foundation4;
        
        piles[11] = provider1;
        piles[12] = provider2;
        
        return piles;
        
    }
    
    public void setCardPiles(CardPile[] piles) {
        
        table1 = piles[0];
        table2 = piles[1];
        table3 = piles[2];
        table4 = piles[3];
        table5 = piles[4];
        table6 = piles[5];
        table7 = piles[6];
        
        foundation1 = piles[7];
        foundation2 = piles[8];
        foundation3 = piles[9];
        foundation4 = piles[10];
        
        provider1 = piles[11];
        provider2 = piles[12];
        
    }

}
