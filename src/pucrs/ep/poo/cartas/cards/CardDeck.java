package pucrs.ep.poo.cartas.cards;

import pucrs.ep.poo.cartas.gui.GameEvent;

import java.util.*;


// Esta classe tem de ser um container de cartas observavel ...
public class CardDeck extends Observable{
    public static final int NCARDS = 5;
    private List<Card> deck;
    private Card selected;
    
    public CardDeck(){
        CardsInitializer initializer = new CardsInitializer();
       deck = new ArrayList<>();
       selected = null;

        for(int i = 0; i<NCARDS; i++) {
            deck.add(initializer.returnARandomCard());
        }

    }
        
    public List<Card> getCards(){
        return(deck);
    }

    public int getNumberOfCards(){
        return(deck.size());
    }
    
    public void removeSel(){
        if (selected == null){
            return;
        }
        deck.remove(selected);
        selected = null;
        GameEvent gameEvent = new GameEvent(GameEvent.Target.DECK,GameEvent.Action.REMOVESEL,"");
        setChanged();
        notifyObservers(gameEvent);
    }    
        
    public void setSelectedCard(Card card){
        selected = card;
    }
    
    public Card getSelectedCard(){
        return(selected);
    }
}

