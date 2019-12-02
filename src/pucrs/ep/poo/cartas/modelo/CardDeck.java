package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;
import pucrs.ep.poo.cartas.modelo.Card;
import pucrs.ep.poo.cartas.modelo.CardsInitializer;

import java.util.*;


// Esta classe tem de ser um container de cartas observavel ...
public class CardDeck extends Observable {
    public static final int INITIALCARDS = 5;
    public static final int MAXIMUMSIZE = 11;
    private List<Card> deck;
    private Card selected;
    private Card boughtCard;
    CardsInitializer initializer;

    public CardDeck() {
        initializer = new CardsInitializer();
        deck = new ArrayList<>();
        selected = null;
        boughtCard = null;

        for (int i = 0; i < INITIALCARDS; i++) {
            deck.add(initializer.returnARandomCard());
        }

    }

    public boolean add(Card aCard) {
        if (this.deck.size() < MAXIMUMSIZE) {
            this.deck.add(aCard);
            return true;
        }
        return false;
    }

    public List<Card> getCards() {
        return (deck);
    }

    public int getNumberOfCards() {
        return (deck.size());
    }

    public void removeSel() {
        if (selected == null) {
            return;
        }
        deck.remove(selected);
        GameEvent gameEvent = new GameEvent(GameEvent.Target.DECK, GameEvent.Action.REMOVESEL, "");
        setChanged();
        notifyObservers(gameEvent);
        selected = null;
    }

    public void setSelectedCard(Card card) {
        selected = card;
    }

    public Card getSelectedCard() {
        return (selected);
    }

    public boolean killCard(Card aCard) {
        if (aCard == null) return false;

        this.deck.remove(aCard);
        return true;
    }

    public void createOpponentHand(int size) {
        this.deck = new ArrayList<>();
        Card imgbackCard = new Card("imgBck");
        for (int i = 0; i < size; i++) {
            deck.add(imgbackCard);
        }
    }

    public void buyACard() {
        Card aCard = null;
        if (this.deck.size() < MAXIMUMSIZE) {
            aCard = initializer.returnARandomCard();
            this.deck.add(aCard);
            boughtCard = aCard;
            GameEvent gameEvent = new GameEvent(GameEvent.Target.DECK, GameEvent.Action.ADDINGTODECK, "");
            setChanged();
            notifyObservers(gameEvent);
        }
        return;
    }

    public Card getTheBoughtCard() {
        return boughtCard;
    }
}

